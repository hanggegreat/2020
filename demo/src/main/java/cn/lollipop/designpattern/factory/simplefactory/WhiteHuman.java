package cn.lollipop.designpattern.factory.simplefactory;

/**
 * @author lollipop
 * @date 2020/11/27 11:00:34
 */
public class WhiteHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("我是白皮肤人");
    }

    @Override
    public void talk() {
        System.out.println("我说白皮肤话");
    }
}
