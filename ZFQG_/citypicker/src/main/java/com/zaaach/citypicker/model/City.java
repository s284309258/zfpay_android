package com.zaaach.citypicker.model;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author Bro0cL on 2016/1/26.
 */
public class City {
    private String lng;
    private String lat;
    private String name;
    private String Id;
    private String pinyin;
    private String code;

    public City(String name, String Id, String pinyin, String code, String lng, String lat) {
        this.name = name;
        this.Id = Id;
        this.pinyin = pinyin;
        this.code = code;
        this.lng = lng;
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public City(String name, String lng, String lat) {
        this.name = name;
        this.lng = lng;
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public String getLat() {
        return lat;
    }

    /***
     * 获取悬浮栏文本，（#、定位、热门 需要特殊处理）
     * @return
     */
    public String getSection(){
        if (TextUtils.isEmpty(pinyin)) {
            return "#";
        } else {
            String c = pinyin.substring(0, 1);
            Pattern p = Pattern.compile("[a-zA-Z]");
            Matcher m = p.matcher(c);
            if (m.matches()) {
                return c.toUpperCase();
            }
            //在添加定位和热门数据时设置的section就是‘定’、’热‘开头
            else if (TextUtils.equals(c, "定") || TextUtils.equals(c, "热"))
                return pinyin;
            else
                return "#";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
