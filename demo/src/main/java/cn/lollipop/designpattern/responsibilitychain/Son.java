package cn.lollipop.designpattern.responsibilitychain;

/**
 * @author lollipop
 * @date 2020/11/27 14:15:55
 */
public class Son extends Handler {

    public Son() {
        super(Handler.SON_LEVEL_REQUEST);
    }

    @Override
    public void response(IWomen women) {
        System.out.println("母亲向儿子请示");
        System.out.println(women.request());
        System.out.println("儿子同意");
    }
}