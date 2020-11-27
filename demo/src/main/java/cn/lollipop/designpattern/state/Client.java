package cn.lollipop.designpattern.state;

/**
 * @author lollipop
 * @date 2020/11/27 14:30:38
 */
public class Client {
    public static void main(String[] args) {
        Context context = new Context();
        context.setLiftState(new ClosingState());
        context.open();
        context.close();
        context.run();
        context.stop();
    }
}