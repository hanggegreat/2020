package cn.lollipop.designpattern.factory.simplefactory;

/**
 * @author lollipop
 * @date 2020/11/27 11:00:34
 */
public class YellowHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("我是黄皮肤人");
    }

    @Override
    public void talk() {
        System.out.println("我说黄皮肤话");
    }
}
