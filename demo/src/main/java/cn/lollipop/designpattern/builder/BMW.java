package cn.lollipop.designpattern.builder;

/**
 * 宝马
 *
 * @author zhangyuanhang
 * @date 2020/11/27 11:14:40
 */
public class BMW extends Car {

    @Override
    protected void start() {
        System.out.println("宝马启动");
    }

    @Override
    protected void stop() {
        System.out.println("宝马熄火");
    }

    @Override
    protected void alarm() {
        System.out.println("宝马鸣笛");
    }

    @Override
    protected void engineBoom() {
        System.out.println("宝马引擎响");
    }
}