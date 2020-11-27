package cn.lollipop.designpattern.adapter;

/**
 * 适配器模式：将一个类的接口变换成客户端所期待的另一种接口，从而使原本因接口不匹配而无法在一起工作的两个类能够在一起工作。
 * 优点：
 * <p>
 * 可以让两个没有任何关系的类运行在一起。
 * 增加了类的透明性。
 * 使用场景：
 * <p>
 * 系统扩展时，需要使用一个已有或者建立一个新的类，但是这个类不符合系统的接口。
 * 最佳实践：
 * <p>
 * java.util.Arrays#asList()
 *
 * @author lollipop
 * @date 2020/11/27 15:11:59
 */
public class Client {
    public static void main(String[] args) {
        IUserInfo youngGirl = new OuterUserInfo();
        for (int i = 0; i < 101; i++) {
            youngGirl.getMobileNumber();
        }
    }
}
