package dao;

import dto.MyLoggerDTO;

import java.sql.*;
import java.util.logging.Level;

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
            MyLoggerDTO.getMyLoggerDTO().log(
                    Level.WARNING,
                    "（获取连接池的接口失败）Failed to get connection: " + e.getMessage()
            );
            throw new RuntimeException(e);
        }

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
            MyLoggerDTO.getMyLoggerDTO().log(
                    Level.WARNING,
                    "（关闭连接口 rs 或者 ps 失败）Failed to close connection: " + e.getMessage()
            );
            e.printStackTrace();
        }

    }


}
