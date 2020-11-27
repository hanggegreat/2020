package cn.lollipop.designpattern.builder;

import java.util.ArrayList;

/**
 * 构造者模式：
 * 优点：
 * <p>
 * 客户端不必知道产品内部组成的细节。
 * 建造者独立，易于扩展。
 * 缺点：
 * <p>
 * 产生多余的Builder对象、Director对象
 * 使用场景：
 * <p>
 * 相同的方法，不同的执行顺序，产生不同的时间结果时。
 * 多个零件或部件，都可以装配到一个对象中，但是产生的结果不同。
 * 产品类非常复杂时
 * 最佳实践：
 * <p>
 * JDK中的StringBuilder。
 *
 * @author lollipop
 * @date 2020/11/27 11:32:04
 */
public class Director {
    private final CarBuilder benzBuilder = new BenzBuilder();
    private final CarBuilder bmwBuilder = new BMWBuilder();
    private final ArrayList<String> sequence = new ArrayList<>();

    public Car getABenz() {
        sequence.clear();
        sequence.add("start");
        sequence.add("stop");
        return benzBuilder.setSequence(sequence).build();
    }

    public Car getBBenz() {
        sequence.clear();
        sequence.add("engine boom");
        sequence.add("start");
        sequence.add("stop");
        return benzBuilder.setSequence(sequence).build();
    }

    public Car getCBMW() {
        sequence.clear();
        sequence.add("alarm");
        sequence.add("start");
        sequence.add("stop");
        return bmwBuilder.setSequence(sequence).build();
    }

    public Car getDBMW() {
        sequence.clear();
        sequence.add("start");
        sequence.add("engine boom");
        sequence.add("stop");
        return bmwBuilder.setSequence(sequence).build();
    }
}