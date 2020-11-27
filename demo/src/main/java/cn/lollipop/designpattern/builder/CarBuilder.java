package cn.lollipop.designpattern.builder;

import java.util.ArrayList;

/**
 * 汽车构造器
 *
 * @author zhangyuanhang
 * @date 2020/11/27 11:14:40
 */
public abstract class CarBuilder {
    /**
     * 设置构造顺序
     *
     * @param sequence 顺序
     * @return this
     */
    public abstract CarBuilder setSequence(ArrayList<String> sequence);

    /**
     * 构造
     *
     * @return Car
     */
    public abstract Car build();
}