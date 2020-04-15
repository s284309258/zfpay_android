package com.lckj.jycm.zfqg_network;

public class SubmitUserAuthInfoRequest {
    /**
     * token : 3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
     * id_card : 421126199408701234
     * real_name : 沧海
     * card_photo : defaultAvatar.png,defaultAvatar.png,defaultAvatar.png
     * sign : 20F789F4B17C2B81AD4E4FFC2A77BC2A
     */

    private String token;
    private String id_card;
    private String real_name;
    private String card_photo;

    public SubmitUserAuthInfoRequest(String token, String id_card, String real_name, String card_photo) {
        this.token = token;
        this.id_card = id_card;
        this.real_name = real_name;
        this.card_photo = card_photo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getCard_photo() {
        return card_photo;
    }

    public void setCard_photo(String card_photo) {
        this.card_photo = card_photo;
    }
}
