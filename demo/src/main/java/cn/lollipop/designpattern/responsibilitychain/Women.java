package cn.lollipop.designpattern.responsibilitychain;

/**
 * @author lollipop
 * @date 2020/11/27 14:16:26
 */
public class Women implements IWomen {
    /**
     * 1.未出嫁，2.已出嫁，3.夫死
     */
    private final int type;
    private final String request;

    public Women(int type, String request) {
        this.type = type;
        this.request = request;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public String request() {
        return request;
    }
}