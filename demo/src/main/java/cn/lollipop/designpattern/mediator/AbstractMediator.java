package cn.lollipop.designpattern.mediator;

/**
 * @author lollipop
 * @date 2020/11/27 13:33:22
 */
public abstract class AbstractMediator {
    protected Purchase purchase;
    protected Sale sale;
    protected Stock stock;

    public AbstractMediator() {
        purchase = new Purchase(this);
        sale = new Sale(this);
        stock = new Stock(this);
    }

    public abstract void execute(String str, Object... objects);
}