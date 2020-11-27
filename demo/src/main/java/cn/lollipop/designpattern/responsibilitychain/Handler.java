package cn.lollipop.designpattern.responsibilitychain;

/**
 * @author lollipop
 * @date 2020/11/27 14:14:44
 */
public abstract class Handler {
    public static final int FATHER_LEVEL_REQUEST = 1;
    public static final int HUSBAND_LEVEL_REQUEST = 2;
    public static final int SON_LEVEL_REQUEST = 3;

    private final int level;
    private Handler nextHandler;

    public Handler(int level) {
        this.level = level;
    }

    public final void handlerMessage(IWomen women) {
        if (women.getType() == level) {
            response(women);
        } else if (nextHandler != null) {
            nextHandler.handlerMessage(women);
        } else {
            System.out.println("没有后续处理人了，按不同意处理");
        }
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * 请求的响应
     *
     * @param women
     */
    public abstract void response(IWomen women);
}