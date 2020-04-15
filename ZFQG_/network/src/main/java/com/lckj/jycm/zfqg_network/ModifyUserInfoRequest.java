package com.lckj.jycm.zfqg_network;

public class ModifyUserInfoRequest {
    /**
     * token : 8|6PGSWKB8YGOGIAFUQPOU68YV6NVW97VH
     * head_photo : 8|6PGSWKB8YGOGIAFUQPOU68YV6NVW97VH
     * sign : 6A8E4713056DFE2CB40A0C05036A3DE1
     */

    private String token;
    private String head_photo;

    public ModifyUserInfoRequest(String token, String head_photo) {
        this.token = token;
        this.head_photo = head_photo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHead_photo() {
        return head_photo;
    }

    public void setHead_photo(String head_photo) {
        this.head_photo = head_photo;
    }
}
