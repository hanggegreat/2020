package cn.lollipop.designpattern.proxy;

/**
 * @author lollipop
 * @date 2020/11/27 14:49:33
 */
public class GamePlayer implements IGamePlayer {
    private String name;

    public GamePlayer(String name) {
        this.name = name;
    }

    @Override
    public void login(String user, String password) {
        System.out.println("登录");
    }

    @Override
    public void killBoss() {
        System.out.println("打怪");
    }

    @Override
    public void upgrade() {
        System.out.println("升级");
    }
}