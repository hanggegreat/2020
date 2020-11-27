package cn.lollipop.designpattern.singleton;

/**
 * 饿汉式，基于枚举，依靠JVM保证只实例化一次对象
 *
 * @author lollipop
 * @date 2020/11/27 10:50:37
 */
public enum Singleton5 {
    // 实例
    INSTANCE;

    public Singleton5 getInstance() {
        return INSTANCE;
    }
}
