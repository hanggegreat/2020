package cn.lollipop.designpattern.adapter;

import java.util.Map;

interface IOuterUser {
    Map<String, String> getUserBaseInfo();

    Map<String, String> getUserOfficeInfo();

    Map<String, String> getUserHomeInfo();
}