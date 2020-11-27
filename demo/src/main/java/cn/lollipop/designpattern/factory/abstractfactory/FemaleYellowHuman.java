package cn.lollipop.designpattern.factory.abstractfactory;

/**
 * @author lollipop
 * @date 2020/11/27 11:16:37
 */
public class FemaleYellowHuman extends AbstractWhiteHuman {
    @Override
    public void getSex() {
        System.out.println("我是女黄人");
    }
}
