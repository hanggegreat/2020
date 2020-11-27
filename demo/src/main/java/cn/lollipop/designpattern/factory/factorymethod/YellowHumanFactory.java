package cn.lollipop.designpattern.factory.factorymethod;

/**
 * @author lollipop
 * @date 2020/11/27 11:09:59
 */
public class YellowHumanFactory extends AbstractHumanFactory {
    @Override
    public Human createHuman() {
        return new YellowHuman();
    }
}
