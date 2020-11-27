package cn.lollipop.designpattern.observer;

public class LiuSi implements Observer {

    @Override
    public void update(String context) {
        System.out.println("刘斯：观察到韩非子活动，自己开始笑。。。");
        System.out.println("刘斯：开始笑。。。" + context);
    }
}