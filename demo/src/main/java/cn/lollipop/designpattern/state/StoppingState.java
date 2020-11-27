package cn.lollipop.designpattern.state;

/**
 * @author lollipop
 * @date 2020/11/27 14:28:17
 */
public class StoppingState extends LiftState {

    @Override
    public void open() {
        super.context.setLiftState(Context.OPENING_STATE);
        super.context.stop();
    }

    @Override
    public void close() {
        System.out.println("门本来就关着呢。。。");
    }

    @Override
    public void run() {
        super.context.setLiftState(Context.RUNNING_STATE);
        super.context.run();
    }

    @Override
    public void stop() {
        System.out.println("电梯停下。。。");
    }
}
