package cn.lollipop.designpattern.strategy;

/**
 * @author lollipop
 * @date 2020/11/27 14:22:02
 */
public class BlockEnemy implements IStrategy {

    @Override
    public void operate() {
        System.out.println("孙夫人断后，挡住追兵");
    }
}