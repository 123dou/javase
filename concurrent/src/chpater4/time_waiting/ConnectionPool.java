package chpater4.time_waiting;

import java.sql.Connection;
import java.util.LinkedList;

public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<>();

    /**
     * 初始化连接池
     * @param initialSize
     */
    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i ++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    /**
     * 释放连接池:
     * @param connection
     */
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    /**
     * 在mills时间内无法获得连接会直接返回null
     * @param mills
     * @return
     * @throws Exception
     */
    public Connection fetchConnection(long mills) throws Exception {
        synchronized (pool) {
            //完全超时
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while (pool.isEmpty() && remaining > 0) {
                    pool.wait();
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }

}
