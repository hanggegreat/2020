package cn.lollipop.designpattern.factory.simplefactory;

/**
 * 简单工厂模式：
 * <p>
 * 优点：
 * 良好的封装性。
 * 屏蔽了产品类的实例化过程。
 * 实现了解耦，高层模块只依赖于产品的抽象。
 * </p>
 * <p>
 * 缺点：
 * 可能增加了调用处的复杂度，需要知道传入什么参数。
 * 不符合开不原则，扩展比较困难。
 * </p>
 * <p>
 * 使用场景：
 * 工厂类负责创建的对象较少，客户端只关心传入工厂类的参数，不关心创建对象的逻辑。
 * </p>
 * <p>
 * 最佳实践：
 * Spring IOC容器中，BeanFactory和ApplicationContext就是工厂类，负责管理所有的Bean实例。
 * JDK中的Calendar类为工厂模式，根据传入的location信息，返回对应国家的日历。
 * </p>
 *
 * @author lollipop
 * @date 2020/11/27 11:02:02
 */
public class SimpleFactory {
    public static <T extends Human> T createHuman(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor(clazz).newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}
