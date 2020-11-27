package cn.lollipop.designpattern.responsibilitychain;

/**
 * @author lollipop
 * @date 2020/11/27 14:13:47
 */
public interface IWomen {
    /**
     * 请求类型
     *
     * @return 类型
     */
    int getType();

    /**
     * 获取请求
     *
     * @return 请求
     */
    String request();
}

