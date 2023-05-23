package dao;

import java.sql.*;

/**
 * @author Xenqiao
 */
public class DBUtil {

    /**
     * 获取数据库链接
     *
     * @return
     */
    public static Connection getConn() {
        try {
            return MyConnectionPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void closeAll(Connection connection, PreparedStatement ps, ResultSet rs) {

        try {
            if (rs != null) {
                rs.close();
            }

            if (ps != null) {
                ps.close();
            }

            if (connection != null) {
                //将接口返回给连接池
                MyConnectionPool.releaseConnection(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
