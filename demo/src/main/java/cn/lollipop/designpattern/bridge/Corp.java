package cn.lollipop.designpattern.bridge;

abstract class Corp {
    private final Product product;

    public Corp(Product product) {
        this.product = product;
    }

    public void makeMoney() {
        product.beProduced();
        product.beSold();
    }
}