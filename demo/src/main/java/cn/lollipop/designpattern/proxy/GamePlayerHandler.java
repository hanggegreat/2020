package cn.lollipop.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lollipop
 * @date 2020/11/27 14:50:07
 */
public class GamePlayerHandler implements InvocationHandler {
    /**
     * 被代理的实例
     */
    private final Object obj;

    public GamePlayerHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(obj, args);
        if ("login".equals(method.getName())) {
            System.out.println("有人在用我的账号登录。。");
        }
        return result;
    }
}
