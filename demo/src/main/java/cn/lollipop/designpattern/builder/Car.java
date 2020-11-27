package cn.lollipop.designpattern.builder;

import java.util.ArrayList;

/**
 * @author zhangyuanhang
 * @date 2020/11/27 11:14:40
 */
public abstract class Car {
    private ArrayList<String> sequence = new ArrayList<>();

    /**
     * 启动
     */
    protected abstract void start();

    /**
     * 停止
     */
    protected abstract void stop();

    /**
     * 鸣笛
     */
    protected abstract void alarm();

    /**
     * 发动
     */
    protected abstract void engineBoom();

    public final void run() {
        for (String action : sequence) {
            try {
                Car.class.getMethod(action).invoke(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public final void setSequence(ArrayList<String> sequence) {
        this.sequence = sequence;
    }
}