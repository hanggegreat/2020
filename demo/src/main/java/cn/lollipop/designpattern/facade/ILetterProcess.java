package cn.lollipop.designpattern.facade;

public interface ILetterProcess {
    void writeContext(String context);

    void fillEnvelop(String address);

    void letterIntoEnvelope();

    void sendLetter();
}