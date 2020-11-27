package cn.lollipop.designpattern.state;

/**
 * 状态模式：状态的变更引起了行为的变更，从外部看起来就好像这个对象对应的类发生了改变一样。
 * 优点：
 * <p>
 * 结构清晰
 * 遵循单一职责原则和开闭原则
 * 封装性好
 * 缺点：
 * <p>
 * 子类太多。
 * 使用场景：
 * <p>
 * 行为随状态改变而改变的场景。
 * 条件、分值判断语句的替代者。
 *
 * @author lollipop
 * @date 2020/11/27 14:28:28
 */
public class Context {
    public static final OpeningState OPENING_STATE = new OpeningState();
    public static final ClosingState CLOSING_STATE = new ClosingState();
    public static final RunningState RUNNING_STATE = new RunningState();
    public static final StoppingState STOPPING_STATE = new StoppingState();

    private LiftState liftState;

    public void setLiftState(LiftState liftState) {
        this.liftState = liftState;
        // 将当前的环境通知到各个实现类中
        liftState.setContext(this);
    }

    public LiftState getLiftState() {
        return liftState;
    }

    public void open() {
        liftState.open();
    }

    public void close() {
        liftState.close();
    }

    public void run() {
        liftState.run();
    }

    public void stop() {
        liftState.stop();
    }
}