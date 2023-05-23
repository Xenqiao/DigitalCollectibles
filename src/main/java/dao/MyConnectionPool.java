package dao;

import java.util.concurrent.atomic.AtomicInteger;
import java.sql.*;

import java.util.concurrent.BlockingQueue;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 * @author Xenqiao
 * @create 2023/5/23 11:16
 */

public class MyConnectionPool {

    private static final int MAX_POOL_SIZE = 100;

    private static DataSource dataSource;
    private static BlockingQueue<Connection> idleConnections;
    private static AtomicInteger activeConnections = new AtomicInteger(0);
    private static Object lock = new Object();

    static {

//        Properties properties = new Properties();
//        properties.setProperty("serverTimezone", "Asia/Shanghai");
//        properties.setProperty("useSSL", "false");
//        properties.setProperty("autoReconnect", "true");
//        ds.setConnectionProperties(properties);

        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/sys");
        ds.addConnectionProperty("serverTimezone", "Asia/Shanghai");
        ds.setUsername("root");
        ds.setPassword("xjq030328");
        ds.setInitialSize(10);//初始化连接池大小
        ds.setMaxTotal(MAX_POOL_SIZE);
        ds.setMaxIdle(10);//最大数据块连接数量
        dataSource = ds;
        idleConnections = new LinkedBlockingQueue<>(MAX_POOL_SIZE);
        initializeConnections();
    }

    private static void initializeConnections() {
        //数字 10 代表初始化的连接池大小
        for (int i = 0; i < 10; i++) {
            try {
                Connection connection = dataSource.getConnection();
                idleConnections.offer(connection);
            } catch (SQLException e) {
                System.err.println("Failed to initialize connection: " + e.getMessage());
            }
        }
    }


    public static Connection getConnection() throws SQLException {
        Connection connection = idleConnections.poll();
        if (connection == null && activeConnections.get() < MAX_POOL_SIZE) {
            synchronized (lock) {
                if (activeConnections.get() < MAX_POOL_SIZE) {
                    connection = dataSource.getConnection();
                    activeConnections.incrementAndGet();
                }
            }
        }
        if (connection == null) {
            try {
                //最大等待时间为 5秒
                connection = idleConnections.poll(5000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (connection != null) {
            return connection;
        }
        throw new SQLException("连接池已耗尽!!!");
    }

    /** 归还conn接口给连接池 **/
    public static void releaseConnection(Connection connection) throws SQLException {
        if (connection == null) {
            //connection = ((ConnectionProxy) connection).getRealConnection();
            connection = getConnection();
        }
        if ( !connection.isClosed()) {
            boolean addedToIdle = false;
            synchronized (lock) {
                //最大数据块连接数量
                if (idleConnections.size() < 10) {
                    idleConnections.offer(connection);
                    addedToIdle = true;
                }
            }
            if (!addedToIdle) {
                activeConnections.decrementAndGet();
                connection.close();
            }
        }
    }



    /**    当连接数量过多时调整连接池的大小
     * **/
    public static void resizePool(int size) {
        synchronized (lock) {
            int currentSize = activeConnections.get() + idleConnections.size();
            if (size > currentSize) {
                int connectionsToAdd = size - currentSize;
                for (int i = 0; i < connectionsToAdd; i++) {
                    try {
                        Connection connection = dataSource.getConnection();
                        idleConnections.offer(connection);
                    } catch (SQLException e) {
                        System.err.println("Failed to add connection to pool: " + e.getMessage());
                    }
                }
            } else if (size < currentSize) {
                int connectionsToRemove = currentSize - size;
                for (int i = 0; i < connectionsToRemove; i++) {
                    Connection connection = idleConnections.poll();
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            System.err.println("Failed to close connection: " + e.getMessage());
                        }
                    }
                }
            }
        }
    }


}

