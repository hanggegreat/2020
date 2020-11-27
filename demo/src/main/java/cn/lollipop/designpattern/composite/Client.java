package cn.lollipop.designpattern.composite;

import java.util.ArrayList;

/**
 * 将对象组合成树形结构以表示“部分-整体”的层次结构，使得用户对单个对象和组合对象的使用具有一致性。
 * 优点：
 * <p>
 * 高层模块调用简单。
 * <p>
 * 节点自由增加。
 *
 * @author lollipop
 * @date 2020/11/27 15:02:37
 */
public class Client {
    public static String getTreeInfo(Branch root) {
        ArrayList<Corp> subordinates = root.getSubordinates();
        StringBuilder sb = new StringBuilder();
        for (Corp c : subordinates) {
            sb.append(c.getInfo());
            if (c instanceof Branch) {
                sb.append(getTreeInfo((Branch) c));
            }
        }
        return sb.toString();
    }
}