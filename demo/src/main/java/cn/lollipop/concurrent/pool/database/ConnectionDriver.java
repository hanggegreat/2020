package cn.lollipop.concurrent.pool.database;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * @author lollipop
 * @date 2020/11/26 16:50:08
 */
public class ConnectionDriver {
    static class ConnectionHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("commit")) {
                TimeUnit.SECONDS.sleep(100);
            }

            return null;
        }
    }

    public static final Connection createConnection() {
        return (Connection) Proxy.newProxyInstance(ConnectionHandler.class.getClassLoader(), new Class<?>[]{Connection.class}, new ConnectionHandler());
    }
}
