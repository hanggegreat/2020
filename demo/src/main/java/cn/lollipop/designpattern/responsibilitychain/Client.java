package cn.lollipop.designpattern.responsibilitychain;

import java.util.Random;

/**
 * 责任链模式：使多个对象都有机会处理请求，从而避免了请求的发送者和接受者之间的耦合关系，将这些对象连成一条链，并沿着这条链传递请求，直至有对象处理它为止。
 * 缺点：
 * <p>
 * 将请求和处理分离开，降低了耦合。
 * 性能较差，需要遍历链表。
 * <p>
 * 最佳实践：
 * <p>
 * javax.servlet.Filter#doFilter()
 *
 * @author lollipop
 * @date 2020/11/27 14:16:53
 */
public class Client {
    public static void main(String[] args) {
        IWomen women = new Women(new Random().nextInt(4), "我要出去逛街");
        Handler father = new Father();
        Handler husband = new Husband();
        Handler son = new Son();

        father.setNextHandler(husband);
        husband.setNextHandler(son);

        father.handlerMessage(women);
    }
}
