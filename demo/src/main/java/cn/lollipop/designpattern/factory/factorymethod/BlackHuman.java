package cn.lollipop.designpattern.factory.factorymethod;

/**
 * @author lollipop
 * @date 2020/11/27 11:00:34
 */
public class BlackHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("我是黑皮肤人");
    }

    @Override
    public void talk() {
        System.out.println("我说黑皮肤话");
    }
}
