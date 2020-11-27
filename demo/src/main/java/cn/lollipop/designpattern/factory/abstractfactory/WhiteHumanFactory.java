package cn.lollipop.designpattern.factory.abstractfactory;

/**
 * @author lollipop
 * @date 2020/11/27 11:19:25
 */
public class WhiteHumanFactory implements HumanFactory {

    @Override
    public Human createMaleHuman() {
        return new MaleWhiteHuman();
    }

    @Override
    public Human createFemaleHuman() {
        return new FemaleWhiteHuman();
    }
}
