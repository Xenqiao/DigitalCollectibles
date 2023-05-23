package dao;

/**
 * @author Xenqiao
 * @create 2023/5/23 14:33
 */
public class SqlBuilder {
    private String tableName;
    private String[] columns;
    private String[] values;
    private String whereClause;
    private String[] updateColumns;
    private String[] updateValues;

    // 构造INSERT语句
    public SqlBuilder insert(String tableName, String[] columns, String[] values) {
        this.tableName = tableName;
        this.columns = columns;
        this.values = values;
        return this;
    }

    // 构造UPDATE语句
    public SqlBuilder update(String tableName, String[] columns, String[] values, String whereClause) {
        this.tableName = tableName;
        this.columns = columns;
        this.values = values;
        this.whereClause = whereClause;
        return this;
    }

    // 构造DELETE语句
    public SqlBuilder delete(String tableName, String whereClause) {
        this.tableName = tableName;
        this.whereClause = whereClause;
        return this;
    }

    // 构造SELECT语句
    public SqlBuilder select(String[] columns, String tableName, String whereClause) {
        this.columns = columns;
        this.tableName = tableName;
        this.whereClause = whereClause;
        return this;
    }

    // 构造SQL语句并返回
    public String build() {
        StringBuilder sb = new StringBuilder();
        if (columns == null || columns.length == 0) {
            sb.append("SELECT * ");
        } else {
            sb.append("SELECT ");
            for (int i = 0; i < columns.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(columns[i]);
            }
            sb.append(" ");
        }
        sb.append("FROM ").append(tableName).append(" ");
        if (values != null && values.length > 0) {
            sb.append("VALUES (");
            for (int i = 0; i < values.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(values[i]);
            }
            sb.append(") ");
        }
        if (updateColumns != null && updateColumns.length > 0) {
            sb.append("SET ");
            for (int i = 0; i < updateColumns.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(updateColumns[i]).append(" = ").append(updateValues[i]);
            }
            sb.append(" ");
        }
        if (whereClause != null && !whereClause.isEmpty()) {
            sb.append("WHERE ").append(whereClause);
        }
        return sb.toString();
    }

}
