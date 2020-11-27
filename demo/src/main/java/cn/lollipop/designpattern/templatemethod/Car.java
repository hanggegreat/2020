package cn.lollipop.designpattern.templatemethod;

/**
 * 模板方法模式：
 * <p>
 * 定义一个操作中的算法的框架，而将一些步骤延迟到子类中。使得子类可以不改变一个算法的节后即可重定义该算法的某些特定步骤。
 * <p>
 * 优点：
 * <p>
 * 封装不变部分，扩展可变部分。
 * 提取公共部分代码，便于维护。
 * 行为由父类控制，子类实现。
 * 缺点：
 * <p>
 * 子类的执行结果影响了父类。
 * 使用场景：
 * <p>
 * 多个子类由公共的方法，并且逻辑基本相同。
 * 重要、复杂的算法，可以把核心算法设计为模板方法。
 * 重构时，可以把相同提取到父类中。
 * 最佳实践：
 * <p>
 * JDK中的AbstractList类运用了模板方法模式。
 * Spring中JDBCTemplate运用了模板方法模式。
 *
 * @author lollipop
 * @date 2020/11/27 11:38:20
 */
public abstract class Car {
    /**
     * start
     */
    public abstract void start();

    /**
     * stop
     */
    public abstract void stop();

    /**
     * alarm
     */
    public abstract void alarm();

    /**
     * engineBoom
     */
    public abstract void engineBoom();

    public void run() {
        this.start();
        this.engineBoom();
        this.alarm();
        this.stop();
    }
}

