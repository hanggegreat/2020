package cn.lollipop.designpattern.observer;

/**
 * 观察者模式也叫发布订阅模式，它是一个在项目中经常使用的模式，定义如下：
 * <p>
 * 定义对象间一种一对多的依赖关系，使得每当一个对象改变状态，所有依赖于它的对象都会得到通知并被自动更新。
 * <p>
 * 优点：
 * <p>
 * 观察者和被观察者之间是抽象耦合。
 * 建立了一套触发机制。
 * 缺点：
 * <p>
 * 效率低，需要进行一系列调用。
 * 使用场景：
 * <p>
 * 关联行为场景。
 * 事件多级触发场景。
 * 跨系统的消息交换场景。
 *
 * @author lollipop
 * @date 2020/11/27 14:36:07
 */
public class Client {
    public static void main(String[] args) {
        Observer liSi = new LiSi();
        Observer wangSi = new WangSi();
        Observer liuSi = new LiuSi();

        HanFeiZi hanFeiZi = new HanFeiZi();

        hanFeiZi.addObserver(liSi);
        hanFeiZi.addObserver(wangSi);
        hanFeiZi.addObserver(liuSi);

        hanFeiZi.haveBreakfast();
    }
}