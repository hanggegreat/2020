package cn.lollipop.designpattern.decorator;

public class SortDecorator extends Decorator {

    public SortDecorator(SchoolReport sr) {
        super(sr);
    }

    @Override
    public void report() {
        super.report();
        reportSort();
    }

    private void reportSort() {
        System.out.println("排名38名...");
    }
}
