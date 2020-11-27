package cn.lollipop.designpattern.bridge;

/**
 * @author lollipop
 * @date 2020/11/27 15:15:19
 */
public class House extends Product {

    @Override
    public void beProduced() {
        System.out.println("生产出的房子是这样的。。。");
    }

    @Override
    public void beSold() {
        System.out.println("生产出的房子卖出去了。。。");
    }
}