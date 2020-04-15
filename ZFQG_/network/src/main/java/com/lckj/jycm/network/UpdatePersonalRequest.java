package com.lckj.jycm.network;

public class UpdatePersonalRequest {

    /**
     * sex : 1
     * sign : 8CC714F301FA66B27FB0D666C550503D
     * tel : 18100000000
     * age : 25
     * headPhoto : http://wx.qlogo.cn/mmhead/Q3auHgzwzM
     * token : 11|E16M0O1YBXHRBMGJJ5RIJA28VZ2VP50G
     */

    private String sex;
    private String tel;
    private String system_type = "updatePersonal";
    private String age;
    private String headPhoto;
    private String token;
    private String code;
    private String userName;
    private String bus_type;

    public UpdatePersonalRequest(String userName, String sex, String tel, String age, String headPhoto, String token, String code, String bus_type) {
        this.sex = sex;
        this.userName = userName;
        this.tel = tel;
        this.bus_type = bus_type;
        this.age = age;
        this.headPhoto = headPhoto;
        this.token = token;
        this.code = code;
    }

    private String getUserName() {
        return userName;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    private String getBus_type() {
        return bus_type;
    }

    private void setBus_type(String bus_type) {
        this.bus_type = bus_type;
    }

    private String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
