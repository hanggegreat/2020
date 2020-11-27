package cn.lollipop.designpattern.state;

/**
 * @author lollipop
 * @date 2020/11/27 14:27:31
 */
public class OpeningState extends LiftState {

    @Override
    public void open() {
        System.out.println("电梯门开。。。");
    }

    @Override
    public void close() {
        super.context.setLiftState(Context.CLOSING_STATE);
        super.context.close();
    }

    @Override
    public void run() {
        System.out.println("门开着还想跑？？？");
    }

    @Override
    public void stop() {
        System.out.println("已经停了。。。。");
    }
}