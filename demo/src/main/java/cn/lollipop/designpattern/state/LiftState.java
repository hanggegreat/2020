package cn.lollipop.designpattern.state;

/**
 * @author lollipop
 * @date 2020/11/27 14:27:12
 */
public abstract class LiftState {
    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public abstract void open();

    public abstract void close();

    public abstract void run();

    public abstract void stop();
}
