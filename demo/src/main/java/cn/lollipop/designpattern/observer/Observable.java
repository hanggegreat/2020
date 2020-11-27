package cn.lollipop.designpattern.observer;

/**
 * @author lollipop
 * @date 2020/11/27 14:34:06
 */
public interface Observable {
    void addObserver(Observer observer);

    void deleteObserver(Observer observer);

    void notifyObservers(String context);
}