package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xenqiao
 * @create 2023/4/13 13:21
 */
public class MysqlDAO {
    private Connection conn;
    private PreparedStatement ps;

    private void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
        }
    }


    /**  更新数据库的方法
     *   只能够执行预编译语句，不能返回 ResultSet
     **/
    public boolean executeUpdate(String preparedSql, Object[] param) {

        try (Connection conn = DBUtil.getConn()){
            // 得到PreparedStatement对象
            if (conn != null) {
                ps = conn.prepareStatement(preparedSql);
            }
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    // 为预编译sql设置参数
                    ps.setObject(i + 1, param[i]);
                }
            }
            DBUtil.closeAll(conn,ps,null);
            return ps.executeLargeUpdate() == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    /**
     * MySQL数据库的查询方法
     **/
    public List<Map<String, Object>> query(String sql, Object... params) {
        List<Map<String, Object>> result = new ArrayList<>();
        try (Connection conn = DBUtil.getConn()){
            if (conn != null) {
                ps = conn.prepareStatement(sql);
            }
            setParameters(ps, params);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    ResultSetMetaData metaData = rs.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        Object columnValue = rs.getObject(i);
                        row.put(columnName, columnValue);
                    }
                    result.add(row);
                }
                DBUtil.closeAll(conn,ps,rs);
            }
        } catch (SQLException e) {
            System.err.println("Failed to execute query: " + e.getMessage());
        }
        return result;
    }


    /**
     * 实现sql语句的插入方法
     **/
    public int insert(String sql, Object... params) {
        int rowsAffected = 0;
        try {
            conn = DBUtil.getConn();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
            }

            setParameters(ps, params);
            rowsAffected = ps.executeUpdate();
            DBUtil.closeAll(conn,ps,null);
        } catch (SQLException e) {
            System.err.println("Failed to execute insert: " + e.getMessage());
        }
        return rowsAffected;
    }

    /**
     * 更新数据库的方法
     **/
    public int update(String sql, Object... params) {
        int rowsAffected = 0;
        try {
            conn = DBUtil.getConn();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
            }
            setParameters(ps, params);
            rowsAffected = ps.executeUpdate();
            DBUtil.closeAll(conn,ps,null);

        } catch (SQLException e) {
            System.err.println("Failed to execute update: " + e.getMessage());
        }
        return rowsAffected;
    }

    /**
     * 删除数据的方法
     **/
    public int delete(String sql, Object... params) {
        int rowsAffected = 0;
        try {
            conn = DBUtil.getConn();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
            }
            setParameters(ps, params);
            rowsAffected = ps.executeUpdate();
            DBUtil.closeAll(conn,ps,null);

        } catch (SQLException e) {
            System.err.println("Failed to execute delete: " + e.getMessage());
        }
        return rowsAffected;
    }

}
