package cn.lollipop.designpattern.factory.factorymethod;

/**
 * 工厂方法模式：
 * 优点：
 * <p>
 * 良好的封装性，代码结构清晰。
 * 扩展性非常优秀，在需要增加产品类的时候，只需要扩展一个工厂类。
 * 屏蔽了产品类的实例化过程。
 * 实现了解耦，高层模块只依赖于产品的抽象。
 * 缺点：
 * <p>
 * 每增加一个产品，相应的也要增加一个子工厂，加大了额外的开发量。
 * 最佳实践：
 * <p>
 * JDK中的Iterator类运用了工厂方法模式，每种集合类都负责实例化对应的Iterator实例。
 *
 * @author lollipop
 * @date 2020/11/27 11:09:05
 */
public abstract class AbstractHumanFactory {
    /**
     * 创建人类
     *
     * @return 人类
     */
    public abstract Human createHuman();
}
