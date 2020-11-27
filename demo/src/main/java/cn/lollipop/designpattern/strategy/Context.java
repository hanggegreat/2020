package cn.lollipop.designpattern.strategy;

/**
 * 策略模式：定义一组算法，将每个算法都封装起来，并且使它们之间可以互换。
 * 优点：
 * <p>
 * 算法可以自由切换
 * 避免了多重条件判断
 * 扩展性好
 * 缺点：
 * <p>
 * 策略类数量增多
 * 所有策略类都需要对外暴露
 * 使用场景：
 * <p>
 * 多个类只有算法或者行为上稍有不同的场景。
 * 最佳实践：
 * <p>
 * java.util.Comparator#compare()
 *
 * @author lollipop
 * @date 2020/11/27 14:22:17
 */
public class Context {
    private final IStrategy strategy;

    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void operate() {
        strategy.operate();
    }
}
