package cn.lollipop.designpattern.decorator;

/**
 * @author lollipop
 * @date 2020/11/27 14:57:54
 */
public abstract class Decorator extends SchoolReport {
    private final SchoolReport sr;

    public Decorator(SchoolReport sr) {
        this.sr = sr;
    }

    @Override
    public void report() {
        sr.report();
    }

    @Override
    public void sign(String name) {
        sr.sign(name);
    }
}