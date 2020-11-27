package cn.lollipop.designpattern.state;

/**
 * @author lollipop
 * @date 2020/11/27 14:28:06
 */
public class RunningState extends LiftState {

    @Override
    public void open() {
        System.out.println("跑着还想开门？？？");
    }

    @Override
    public void close() {
        System.out.println("门本来就关着呢。。。");
    }

    @Override
    public void run() {
        System.out.println("开始上下运行。。。");
    }

    @Override
    public void stop() {
        super.context.setLiftState(Context.STOPPING_STATE);
        super.context.stop();
    }
}