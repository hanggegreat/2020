package cn.lollipop.designpattern.responsibilitychain;

/**
 * @author lollipop
 * @date 2020/11/27 14:15:27
 */
public class Father extends Handler {

    public Father() {
        super(Handler.FATHER_LEVEL_REQUEST);
    }

    @Override
    public void response(IWomen women) {
        System.out.println("女儿向父亲请示");
        System.out.println(women.request());
        System.out.println("父亲同意");
    }
}
