package com.lckj.jycm.zfqg_network;

public class GetAppImgListRequest {
    /**
     * token : 8|6PGSWKB8YGOGIAFUQPOU68YV6NVW97VH
     * img_type : 01
     * sign : 3A33117A2AB665D7C7F4475E14610345
     */

    private String token;
    private String img_type;

    public GetAppImgListRequest(String token, String img_type) {
        this.token = token;
        this.img_type = img_type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getImg_type() {
        return img_type;
    }

    public void setImg_type(String img_type) {
        this.img_type = img_type;
    }
}
