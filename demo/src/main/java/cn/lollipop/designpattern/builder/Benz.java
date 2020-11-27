package cn.lollipop.designpattern.builder;

/**
 * 奔驰
 *
 * @author zhangyuanhang
 * @date 2020/11/27 11:14:40
 */
public class Benz extends Car {

    @Override
    protected void start() {
        System.out.println("奔驰启动");
    }

    @Override
    protected void stop() {
        System.out.println("奔驰熄火");
    }

    @Override
    protected void alarm() {
        System.out.println("奔驰鸣笛");
    }

    @Override
    protected void engineBoom() {
        System.out.println("奔驰引擎响");
    }
}