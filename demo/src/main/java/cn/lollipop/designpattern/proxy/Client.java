package cn.lollipop.designpattern.proxy;

import java.lang.reflect.Proxy;

/**
 * 代理模式：为其他对象提供一种代理以控制这个对象的访问。
 * <p>
 * 优点：
 * <p>
 * 职责清晰
 * 扩展性高
 * 最佳实践：
 * <p>
 * Spring AOP
 *
 * @author lollipop
 * @date 2020/11/27 14:51:18
 */
public class Client {
    public static void main(String[] args) {
        IGamePlayer player = new GamePlayer("张三");
        System.out.println("游戏开始：" + System.currentTimeMillis());
        IGamePlayer proxy = (IGamePlayer) Proxy.newProxyInstance(player.getClass().getClassLoader(), player.getClass().getInterfaces(), new GamePlayerHandler(player));
        proxy.login("zhangSan", "password");
        proxy.killBoss();
        proxy.upgrade();
        System.out.println("游戏结束：" + System.currentTimeMillis());
    }
}
