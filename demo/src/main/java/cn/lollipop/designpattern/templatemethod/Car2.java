package cn.lollipop.designpattern.templatemethod;

/**
 * 具体模板类
 *
 * @author lollipop
 * @date 2020/11/27 11:39:38
 */
public class Car2 extends Car {

    @Override
    public void start() {
        System.out.println("car2 start...");
    }

    @Override
    public void stop() {
        System.out.println("car2 stop...");
    }

    @Override
    public void alarm() {
        System.out.println("car2 alarm...");
    }

    @Override
    public void engineBoom() {
        System.out.println("car2 engineBoom...");
    }
}