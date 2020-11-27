package cn.lollipop.designpattern.decorator;

/**
 * 装饰器模式：动态地给一个对象添加一些额外的职责。相比生成子类更为灵活。
 * 优点：
 * <p>
 * 装饰类和被装饰类可以独立发展，不会互相耦合。
 * 可以替代继承关系。
 * 装饰模式可以动态地扩展一个实现类的功能。
 * 缺点:
 * <p>
 * 多层的装饰比较复杂。
 * 使用场景：
 * <p>
 * 需要扩展一个类的功能。
 * 最佳实践：
 * <p>
 * java.io.BufferedInputStream(InputStream)
 *
 * @author lollipop
 * @date 2020/11/27 14:59:08
 */
public class Father {
    public static void main(String[] args) {
        SchoolReport sr = new FourthGradeSchoolReport();
        sr = new HighScoreDecorator(sr);
        sr = new SortDecorator(sr);

        sr.report();
        sr.sign("老张。。");
    }
}