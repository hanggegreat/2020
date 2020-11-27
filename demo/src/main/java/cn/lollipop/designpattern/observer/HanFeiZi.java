package cn.lollipop.designpattern.observer;

import java.util.ArrayList;

/**
 * @author lollipop
 * @date 2020/11/27 14:34:28
 */
public class HanFeiZi implements Observable {
    private final ArrayList<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String context) {
        for (Observer o : observers) {
            o.update(context);
        }
    }

    public void haveBreakfast() {
        System.out.println("韩非子开始吃饭。。。");
        notifyObservers("韩非子开始吃饭了。。。");
    }

    public void haveFun() {
        System.out.println("韩非子开始娱乐。。。");
        notifyObservers("韩非子开始娱乐了。。。");
    }
}