package cn.lollipop.designpattern.mediator;

public class Stock extends AbstractColleague {
    private static int COMPUTER_NUM = 100;

    public Stock(AbstractMediator mediator) {
        super(mediator);
    }

    public void decrease(int number) {
        COMPUTER_NUM -= number;
        System.out.println("库存数量为：" + COMPUTER_NUM);
    }

    public void increase(int number) {
        COMPUTER_NUM += number;
        System.out.println("库存数量为：" + COMPUTER_NUM);
    }

    public int getStockNumber() {
        return COMPUTER_NUM;
    }

    public void clearStock() {
        System.out.println("清理库存数量为：" + COMPUTER_NUM);
        super.mediator.execute("stock.clear");
    }
}