package cn.lollipop.designpattern.factory.abstractfactory;

/**
 * @author lollipop
 * @date 2020/11/27 11:19:25
 */
public class YellowHumanFactory implements HumanFactory {

    @Override
    public Human createMaleHuman() {
        return new MaleYellowHuman();
    }

    @Override
    public Human createFemaleHuman() {
        return new FemaleYellowHuman();
    }
}
