package cn.lollipop.designpattern.bridge;

/**
 * 桥接模式：将抽象和现实解耦，使得两者可以独立地变化。
 * 优点：
 * <p>
 * 抽象和实现分离。
 * 易于扩展。
 * 使用场景：
 * <p>
 * 不希望使用继承的场景。
 *
 * @author lollipop
 * @date 2020/11/27 15:16:36
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("房地产公司");
        HouseCorp houseCorp = new HouseCorp(new House());
        houseCorp.makeMoney();
        System.out.println("山寨公司");
        ShanZhaiCorp shanZhaiCorp = new ShanZhaiCorp(new IPod());
        shanZhaiCorp.makeMoney();
    }
}
