package cn.lollipop.designpattern.builder;

import java.util.ArrayList;

/**
 * @author lollipop
 * @date 2020/11/27 11:30:31
 */
public class BenzBuilder extends CarBuilder {
    private final Benz benz = new Benz();

    @Override
    public CarBuilder setSequence(ArrayList<String> sequence) {
        benz.setSequence(sequence);
        return this;
    }

    @Override
    public Car build() {
        return benz;
    }
}
