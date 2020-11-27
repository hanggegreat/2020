package cn.lollipop.designpattern.adapter;

import java.util.HashMap;
import java.util.Map;

class OuterUser implements IOuterUser {

    @Override
    public Map<String, String> getUserBaseInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("username", "混世魔王");
        map.put("mobileNumber", "混世魔王的电话号码");
        return map;
    }

    @Override
    public Map<String, String> getUserHomeInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("homeTelNumber", "混世魔王的家庭电话");
        map.put("homeAddress", "混世魔王的家庭住址");
        return map;
    }

    @Override
    public Map<String, String> getUserOfficeInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("jobPosition", "混世魔王的职位");
        map.put("officeTelNumber", "混世魔王的办公电话");
        return map;
    }
}
