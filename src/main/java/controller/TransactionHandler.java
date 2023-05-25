package controller;

import dao.DBUtil;
import dto.MyLoggerDTO;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.logging.Level;

/**
 * @author Xenqiao
 * @create 2023/5/23 14:58
 */
public class TransactionHandler implements InvocationHandler {

    private Object target;

    public TransactionHandler(Object target) {
        this.target = target;
    }

    /***
     * TransactionHandler是一个实现了InvocationHandler接口的动态代理处理器，
     * 它负责在执行业务逻辑方法前后，进行事务管理操作。
     * 具体来说，它会在执行业务逻辑方法前获取数据库连接，并将连接的自动提交模式设置为false，
     * 以便手动控制事务的提交和回滚。然后它调用业务逻辑方法，如果业务逻辑方法执行成功，
     * 则提交事务，否则回滚事务。最后，在关闭连接之前，它会将连接的自动提交模式恢复为true。
     *
     * @param proxy the proxy instance that the method was invoked on
     *
     * @param method the {@code Method} instance corresponding to
     * the interface method invoked on the proxy instance.  The declaring
     * class of the {@code Method} object will be the interface that
     * the method was declared in, which may be a superinterface of the
     * proxy interface that the proxy class inherits the method through.
     *
     * @param args an array of objects containing the values of the
     * arguments passed in the method invocation on the proxy instance,
     * or {@code null} if interface method takes no arguments.
     * Arguments of primitive types are wrapped in instances of the
     * appropriate primitive wrapper class, such as
     * {@code java.lang.Integer} or {@code java.lang.Boolean}.
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Connection connection = null;
        Object result = null;
        try {
            connection = DBUtil.getConn();
            if (connection != null) {
                connection.setAutoCommit(false);
                // 执行业务逻辑方法
                result = method.invoke(target, args);
                // 提交事务
                connection.commit();
            }

        } catch (Exception e) {
            MyLoggerDTO.getMyLoggerDTO().log(
                    Level.WARNING,
                    "（事务即将回滚）Transaction rollback"
            );
            // 回滚事务
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            // 关闭连接
            if (connection != null) {
                DBUtil.closeAll(connection,null,null);
            }
        }
        return result;
    }

}
