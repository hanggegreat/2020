package cn.lollipop.designpattern.mediator;

/**
 * 中介者模式：
 * <p>
 * 用一个中介对象封装一系列的对象交互，中介者使各对象不需要显示地相互作用，从而使其耦合松散，而且可以独立地改变它们之间的交互。
 * <p>
 * 优点：
 * <p>
 * 降低了类间的耦合。
 * 缺点：
 * <p>
 * 中介者会膨胀的很大，且逻辑复杂。
 * 使用场景：
 * <p>
 * 适用于多个对象之间紧密耦合的情况，紧密耦合的标准是：类图中出现了类似蜘蛛网的结构。
 * 最佳实践：
 * <p>
 * JDK中的Timer类运用了中介者模式。
 *
 * @author lollipop
 * @date 2020/11/27 13:33:22
 */
public class Mediator extends AbstractMediator {
    @Override
    public void execute(String str, Object... objects) {
        if ("purchase.buy".equals(str)) {
            buyComputer((Integer) objects[0]);
        } else if ("sale.sell".equals(str)) {
            sellComputer((Integer) objects[0]);
        } else if ("sale.offSell".equals(str)) {
            offSell();
        } else if ("stock.clear".equals(str)) {
            clearStock();
        }
    }

    private void buyComputer(int number) {
        int saleStatus = super.sale.getSaleStatus();
        if (saleStatus > 80) {
            System.out.println("采购IBM电脑：" + number + "台");
            super.stock.increase(number);
        } else {
            int buyNumber = number / 2;
            System.out.println("采购IBM电脑：" + buyNumber + "台");
            super.stock.increase(buyNumber);
        }
    }

    private void sellComputer(int number) {
        if (super.stock.getStockNumber() < number) {
            super.purchase.buyIBMComputer(number);
        }
        super.stock.decrease(number);
    }

    private void offSell() {
        System.out.println("折价销售IBM电脑：" + super.stock.getStockNumber());
    }

    private void clearStock() {
        super.sale.offSell();
        super.purchase.refuseBuyIBM();
    }
}