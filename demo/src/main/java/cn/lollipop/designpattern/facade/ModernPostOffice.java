package cn.lollipop.designpattern.facade;

/**
 * 门面模式：一个子系统的外部与其内部的通信必须通过一个统一的对象进行。门面模式提供一个高层次的接口，使得子系统更易使用。
 * 优点：
 * <p>
 * 减少系统的相互依赖。
 * 提高了灵活性和安全性。
 * 缺点:
 * <p>
 * 不符合开闭原则。
 * 使用场景：
 * <p>
 * 为一个复杂的模块提供一个供外界访问的接口。
 *
 * @author lollipop
 * @date 2020/11/27 15:06:45
 */
public class ModernPostOffice {
    private final ILetterProcess letterProcess = new LetterProcessImpl();
    private final Police police = new Police();

    public void sendLetter(String context, String address) {
        letterProcess.writeContext(context);
        letterProcess.fillEnvelop(address);
        police.checkLetter(letterProcess);
        letterProcess.letterIntoEnvelope();
        letterProcess.sendLetter();
    }
}