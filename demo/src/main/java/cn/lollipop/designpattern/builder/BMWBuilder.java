package cn.lollipop.designpattern.builder;

import java.util.ArrayList;

/**
 * @author lollipop
 * @date 2020/11/27 11:30:31
 */
public class BMWBuilder extends CarBuilder {
    private final BMW bmw = new BMW();

    @Override
    public CarBuilder setSequence(ArrayList<String> sequence) {
        bmw.setSequence(sequence);
        return this;
    }

    @Override
    public Car build() {
        return bmw;
    }
}
