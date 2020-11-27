package cn.lollipop.designpattern.singleton;

/**
 * 懒汉式：内部类
 *
 * @author lollipop
 * @date 2020/11/27 10:48:27
 */
public class Singleton4 {
    private Singleton4() {
    }

    private static class InnerClass {
        private static final Singleton4 instance = new Singleton4();
    }

    public Singleton4 getInstance() {
        return InnerClass.instance;
    }
}
