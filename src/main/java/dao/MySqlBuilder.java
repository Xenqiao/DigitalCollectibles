package dao;

/**
 * @author Xenqiao
 * @create 2023/5/23 14:33
 */
public class MySqlBuilder {

    // 构造INSERT语句
    public String insert(String tableName, String[] columns) {
        //sql = " insert into user(userName,password,hash) values(?,?,?) ";
        StringBuilder sb = new StringBuilder();
        if (columns == null || columns.length == 0) {
            return null;
        } else {
            sb.append("insert into ").append(tableName).append(" (");
            for (int i = 0; i < columns.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(columns[i]);
            }
            sb.append( ") values (" );
        }

        for (int i = 0; i < columns.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append("?");
        }
        sb.append( ") " );
        return sb.toString();
    }



    /**
     * @param tableName 表名
     *                  columns 列名
     *                  whereClause 条件
     * 构造UPDATE语句
     */

    public String update(String tableName, String[] columns, String whereClause) {
        //sql = " update user set password=?,hash=? where userName=? ";
        StringBuilder sb = new StringBuilder();
        if (columns == null || columns.length == 0) {
            return null;
        } else {
            sb.append("update ").append(tableName).append(" set ");
            for (int i = 0; i < columns.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(columns[i]).append("=?");
            }
            sb.append(" where ").append(whereClause).append("=? ");
        }
        return sb.toString();
    }

    // 构造DELETE语句
    public String delete(String tableName, String whereClause) {
        //sql = " delete from user where userName=? ";
        if (tableName == null || tableName.isEmpty() || whereClause == null || whereClause.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("delete from ").append(tableName).append(" where ").append(whereClause).append("=? ");
        return sb.toString();
    }

    // 构造SELECT语句
    public String select(String[] columns, String tableName, String whereClause) {
        //sql = " select * from user where userName=? ";
        StringBuilder sb = new StringBuilder();
        if (columns == null || columns.length == 0) {
            sb.append("select * ");
        } else {
            sb.append("select ");
            for (int i = 0; i < columns.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(columns[i]);
            }
            sb.append(" ");
        }
        sb.append("from ").append(tableName).append(" where ").append(whereClause).append("=? ");
        return sb.toString();
    }

}
