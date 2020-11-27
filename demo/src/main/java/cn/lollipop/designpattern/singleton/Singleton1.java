package cn.lollipop.designpattern.singleton;

/**
 * 单例模式使用场景：
 * <p>
 * 要求生成唯一序列号的环境。
 * 共享访问点：如页面上的访问计数器。
 * 创建一个对象需要消耗过多资源：IO、数据库等。
 * </p>
 *
 * 饿汉式
 * 缺点： 类一加载的时候，就实例化，提前占用了系统资源。
 *
 * @author lollipop
 * @date 2020/11/27 10:40:03
 */

public class Singleton1 {
    private static final Singleton1 instance = new Singleton1();

    private Singleton1() {
    }

    public Singleton1 getInstance() {
        return instance;
    }
}


