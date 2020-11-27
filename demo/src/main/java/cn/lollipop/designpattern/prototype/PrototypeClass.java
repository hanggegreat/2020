package cn.lollipop.designpattern.prototype;

import java.util.ArrayList;

/**
 * 原型模式：
 * 优点：
 * <p>
 * 拷贝的是二进制流，性能好。
 * 逃避构造函数的约束。
 * 使用场景：
 * <p>
 * 资源优化场景。
 * 性能和安全性要求较高的场景。
 * 一个对象多个修改者。
 *
 * @author lollipop
 * @date 2020/11/27 11:34:49
 */
public class PrototypeClass {
    private ArrayList<Integer> list = new ArrayList<>();

    @Override
    public PrototypeClass clone() {
        try {
            PrototypeClass res = (PrototypeClass) super.clone();
            res.list = (ArrayList<Integer>) list.clone();
            return res;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
