package cn.lollipop.designpattern.adapter;

import java.util.Map;

public class OuterUserInfo extends OuterUser implements IUserInfo {
    private final Map<String, String> baseInfo = super.getUserBaseInfo();
    private final Map<String, String> homeInfo = super.getUserHomeInfo();
    private final Map<String, String> officeInfo = super.getUserOfficeInfo();

    @Override
    public String getUsername() {
        System.out.println(baseInfo.get("username"));
        return homeInfo.get("username");
    }

    @Override
    public String getHomeAddress() {
        System.out.println(homeInfo.get("homeAddress"));
        return homeInfo.get("homeAddress");
    }

    @Override
    public String getMobileNumber() {
        System.out.println(baseInfo.get("mobileNumber"));
        return homeInfo.get("mobileNumber");
    }

    @Override
    public String getOfficeTelNumber() {
        System.out.println(officeInfo.get("officeTelNumber"));
        return homeInfo.get("officeTelNumber");
    }

    @Override
    public String getJobPosition() {
        System.out.println(officeInfo.get("jobPosition"));
        return homeInfo.get("jobPosition");
    }

    @Override
    public String getHomeTelNumber() {
        System.out.println(homeInfo.get("homeTelNumber"));
        return homeInfo.get("homeTelNumber");
    }
}
