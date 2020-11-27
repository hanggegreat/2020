package cn.lollipop.designpattern.decorator;

public class HighScoreDecorator extends Decorator {

    public HighScoreDecorator(SchoolReport sr) {
        super(sr);
    }

    public void reportHighestScore() {
        System.out.println("这次语文最高75，数学最高78，自然最高80");
    }

    @Override
    public void report() {
        reportHighestScore();
        super.report();
    }
}
