package cn.lollipop.designpattern.bridge;

/**
 * @author lollipop
 * @date 2020/11/27 15:15:34
 */
public class IPod extends Product {

    @Override
    public void beProduced() {
        System.out.println("生产iPod... ");
    }

    @Override
    public void beSold() {
        System.out.println("iPod畅销...");
    }
}