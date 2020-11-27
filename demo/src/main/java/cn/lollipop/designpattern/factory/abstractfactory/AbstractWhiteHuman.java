package cn.lollipop.designpattern.factory.abstractfactory;

/**
 * @author lollipop
 * @date 2020/11/27 11:14:40
 */
public abstract class AbstractWhiteHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("我是白皮肤人");
    }
}
