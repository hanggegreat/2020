package cn.lollipop.designpattern.singleton;

/**
 * 懒汉式：每次都需要获取锁，开销大
 *
 * @author lollipop
 * @date 2020/11/27 10:46:47
 */
public class Singleton2 {
    private static Singleton2 instance;

    private Singleton2() {}

    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }

        return instance;
    }
}
