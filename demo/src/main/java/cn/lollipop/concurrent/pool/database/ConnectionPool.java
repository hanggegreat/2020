package cn.lollipop.concurrent.pool.database;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lollipop
 * @date 2020/11/26 16:48:09
 */
public class ConnectionPool {
    private final Queue<Connection> pool = new LinkedList<>();

    public ConnectionPool(int initSize) {
        for (int i = 0; i < initSize; i++) {
            pool.add(ConnectionDriver.createConnection());
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.add(connection);
                pool.notifyAll();
            }
        }
    }

    public Connection fetchConnection(long millions) throws InterruptedException {
        synchronized (pool) {
            if (millions <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }

                return pool.poll();
            }

            long future = System.currentTimeMillis() + millions;
            while (!pool.isEmpty() && System.currentTimeMillis() <= future) {
                pool.wait();
            }

            return pool.isEmpty() ? null : pool.poll();
        }
    }
}
