package cn.lollipop.designpattern.command;

/**
 * 命令模式：将一个请求封装成一个对象，从而可以使用不同的请求把客户端参数化，对请求排队或者记录请求日志，可以提供命令的撤销和恢复功能。
 * 优点：
 * <p>
 * 类间解耦
 * 可扩展
 * 提取公共部分代码，便于维护。
 * 行为由父类控制，子类实现。
 * 缺点：
 * <p>
 * 每增加一个命令，相应的也要增加命令子类，加大了额外的开发量。
 * 使用场景：
 * <p>
 * 只要是命令的地方就可以使用命令模式。
 * 最佳实践：
 * <p>
 * JDK中的Runnable类运用了命令模式。
 *
 * @author zhangyuanhang
 * @date 2020/11/27 11:14:40
 */
public class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void action() {
        command.execute();
    }
}