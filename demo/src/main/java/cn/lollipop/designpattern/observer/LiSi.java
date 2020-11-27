package cn.lollipop.designpattern.observer;

public class LiSi implements Observer {

    @Override
    public void update(String context) {
        System.out.println("李斯：观察到韩非子活动，开始向老板汇报。。。");
        reportToQinShiHuang(context);
    }

    private void reportToQinShiHuang(String context) {
        System.out.println("李斯：报告秦老板，韩非子有活动了。。。" + context);
    }
}