package cn.lollipop.designpattern.strategy;

/**
 * @author lollipop
 * @date 2020/11/27 14:21:27
 */
public class BackDoor implements IStrategy {

    @Override
    public void operate() {
        System.out.println("找乔国老帮忙，让吴国太给孙权施加压力");
    }
}
