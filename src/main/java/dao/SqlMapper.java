package dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xenqiao
 * @create 2023/5/23 14:41
 */
public class SqlMapper<T> {
    private Class<T> clazz;

    public SqlMapper(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * mapResultSet：将ResultSet对象中的数据映射为Java对象列表。
     * 将结果集映射为Java对象列表
     * 使用方法：
     * SqlMapper<Customer> mapper = new SqlMapper<>(Customer.class);
     * List<Customer> customers = mapper.mapResultSet(rs);
     **/
    public List<T> mapResultSet(ResultSet rs) throws SQLException {
        List<T> list = new ArrayList<>();
        ResultSetMetaData metaData = rs.getMetaData();
        int numColumns = metaData.getColumnCount();
        while (rs.next()) {
            T obj;
            try {
                obj = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new SQLException("Failed to create object of class " + clazz.getName(), e);
            }
            for (int i = 1; i <= numColumns; i++) {
                String columnName = metaData.getColumnName(i);
                Object value = rs.getObject(i);
                setProperty(obj, columnName, value);
            }
            list.add(obj);
        }
        return list;
    }

    /** 设置Java对象属性值
     *
     * @param obj
     * @param columnName
     * @param value
     * @throws SQLException
     */
    private void setProperty(T obj, String columnName, Object value) throws SQLException {
        try {
            String methodName = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
            Method method = clazz.getMethod(methodName, value.getClass());
            method.invoke(obj, value);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new SQLException("Failed to set property " + columnName + " for object of class " + clazz.getName(), e);
        }
    }
}
