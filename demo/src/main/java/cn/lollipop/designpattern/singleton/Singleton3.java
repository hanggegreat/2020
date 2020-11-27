package cn.lollipop.designpattern.singleton;

/**
 * 懒汉式：双重检测法
 *
 * @author lollipop
 * @date 2020/11/27 10:42:55
 */
public class Singleton3 {
    private static volatile Singleton3 instance;

    private Singleton3() {
    }

    public static Singleton3 getInstance() {
        if (instance == null) {
            synchronized (instance) {
                if (instance == null) {
                    instance = new Singleton3();
                }
            }
        }

        return instance;
    }
}
