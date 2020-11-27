package cn.lollipop.designpattern.factory.abstractfactory;

/**
 * @author lollipop
 * @date 2020/11/27 11:16:37
 */
public class MaleYellowHuman extends AbstractWhiteHuman {
    @Override
    public void getSex() {
        System.out.println("我是男黄人");
    }
}
