package cn.lollipop.designpattern.observer;

public class WangSi implements Observer {

    @Override
    public void update(String context) {
        System.out.println("王斯：观察到韩非子活动，自己开始哭。。。");
        System.out.println("王斯：开始哭。。。" + context);
    }
}