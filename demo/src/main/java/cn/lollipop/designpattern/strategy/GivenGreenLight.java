package cn.lollipop.designpattern.strategy;

/**
 * @author lollipop
 * @date 2020/11/27 14:21:50
 */
public class GivenGreenLight implements IStrategy {

    @Override
    public void operate() {
        System.out.println("求吴国太开绿灯，放行");
    }
}