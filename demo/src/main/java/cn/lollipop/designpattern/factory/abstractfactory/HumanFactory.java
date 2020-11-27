package cn.lollipop.designpattern.factory.abstractfactory;

/**
 * 抽象工厂模式：
 * 优点：
 * <p>
 * 良好的封装性。
 * 产品族内的约束为非公开状态。
 * 实现了解耦，高层模块只依赖于产品的抽象。
 * 缺点：
 * <p>
 * 产品族扩展非常困难。
 * 使用场景：
 * <p>
 * 一个对象族都具有相同的约束，则可以使用抽象工厂模式。
 *
 * @author lollipop
 * @date 2020/11/27 11:18:01
 */
public interface HumanFactory {
    /**
     * 创建男性人类
     *
     * @return 人类
     */
    Human createMaleHuman();

    /**
     * 创建女性人类
     *
     * @return 人类
     */
    Human createFemaleHuman();
}
