package cn.lollipop.designpattern.responsibilitychain;

/**
 * @author lollipop
 * @date 2020/11/27 14:15:45
 */
public class Husband extends Handler {

    public Husband() {
        super(Handler.HUSBAND_LEVEL_REQUEST);
    }

    @Override
    public void response(IWomen women) {
        System.out.println("妻子向丈夫请示");
        System.out.println(women.request());
        System.out.println("丈夫同意");
    }
}