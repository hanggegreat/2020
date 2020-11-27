package cn.lollipop.designpattern.composite;

/**
 * @author lollipop
 * @date 2020/11/27 15:01:53
 */
public abstract class Corp {
    private final String name;
    private final String position;
    private final int salary;

    public Corp(String name, String position, int salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getInfo() {
        return "name：" + name + "，position：" + position + "，salary：" + salary;
    }
}