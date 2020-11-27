package cn.lollipop.designpattern.proxy;

/**
 * @author lollipop
 * @date 2020/11/27 14:48:35
 */
public interface IGamePlayer {
    /**
     * 登录
     *
     * @param user
     * @param password
     */
    void login(String user, String password);

    /**
     * 打怪
     */
    void killBoss();

    /**
     * 升级
     */
    void upgrade();
}