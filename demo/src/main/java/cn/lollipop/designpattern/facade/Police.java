package cn.lollipop.designpattern.facade;

public class Police {
    public void checkLetter(ILetterProcess letterProcess) {
        System.out.println(letterProcess + "信件已被检查。。。");
    }
}
