package cn.lollipop.designpattern.facade;

public class LetterProcessImpl implements ILetterProcess {

    @Override
    public void writeContext(String context) {
        System.out.println("填写的内容：" + context);
    }

    @Override
    public void fillEnvelop(String address) {
        System.out.println("收信人地址：" + address);
    }

    @Override
    public void letterIntoEnvelope() {
        System.out.println("塞到信封中。。。");
    }

    @Override
    public void sendLetter() {
        System.out.println("邮递信件。。。");
    }
}