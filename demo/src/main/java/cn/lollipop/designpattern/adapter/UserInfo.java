package cn.lollipop.designpattern.adapter;

public class UserInfo implements IUserInfo {

    @Override
    public String getUsername() {
        System.out.println("姓名是。。");
        return null;
    }

    @Override
    public String getHomeAddress() {
        System.out.println("家庭住址是。。。");
        return null;
    }

    @Override
    public String getMobileNumber() {
        System.out.println("手机号码是。。。");
        return null;
    }

    @Override
    public String getOfficeTelNumber() {
        System.out.println("办公室电话号码是。。。");
        return null;
    }

    @Override
    public String getJobPosition() {
        System.out.println("职位是。。。");
        return null;
    }

    @Override
    public String getHomeTelNumber() {
        System.out.println("家庭电话是。。。");
        return null;
    }
}