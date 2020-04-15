package com.lckj.jycm.zfqg_network;

public class AddUserFeedBackRequest {
    /**
     * token : 3|84CIEII7KCEOGFLR1AFS09RKXJIHZDZ0
     * feedback_title : 我要反馈
     * feedback_content : 系统卡顿
     * feedback_img : 111111111
     * contact_way : 15772101525
     * sign : 44DBA578A949919BB2E10B99BBC99A62
     */

    private String token;
    private String feedback_title;
    private String feedback_content;
    private String feedback_img;
    private String contact_way;

    public AddUserFeedBackRequest(String token, String feedback_title, String feedback_content, String feedback_img, String contact_way) {
        this.token = token;
        this.feedback_title = feedback_title;
        this.feedback_content = feedback_content;
        this.feedback_img = feedback_img;
        this.contact_way = contact_way;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFeedback_title() {
        return feedback_title;
    }

    public void setFeedback_title(String feedback_title) {
        this.feedback_title = feedback_title;
    }

    public String getFeedback_content() {
        return feedback_content;
    }

    public void setFeedback_content(String feedback_content) {
        this.feedback_content = feedback_content;
    }

    public String getFeedback_img() {
        return feedback_img;
    }

    public void setFeedback_img(String feedback_img) {
        this.feedback_img = feedback_img;
    }

    public String getContact_way() {
        return contact_way;
    }

    public void setContact_way(String contact_way) {
        this.contact_way = contact_way;
    }
}
