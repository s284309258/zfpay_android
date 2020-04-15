package com.lckj.jycm.network;

public class AddUserExpandRequest {
    /**
     * cardBackImg : http://123432.jpg
     * cardFrontImg : http://123432.jpg
     * companyName : 蓝波测试公司
     * idCard : 430410203202114301
     * sign : 7E31BA60ABE29B9132CA869E63248D7C
     * token : 11|A8JZ42C484T5SVPOY4YBD3XXDRKIIQYK
     * userName : 兰博
     */

    private String cardBackImg;
    private String cardFrontImg;
    private String idCard;
    private String token;
    private String system_type = "addUserExpand";
    private String userName;

    public AddUserExpandRequest(String cardBackImg, String cardFrontImg, String idCard, String token, String userName) {
        this.cardBackImg = cardBackImg;
        this.cardFrontImg = cardFrontImg;
        this.idCard = idCard;
        this.token = token;
        this.userName = userName;
    }

    public String getCardBackImg() {
        return cardBackImg;
    }

    public void setCardBackImg(String cardBackImg) {
        this.cardBackImg = cardBackImg;
    }

    public String getCardFrontImg() {
        return cardFrontImg;
    }

    public void setCardFrontImg(String cardFrontImg) {
        this.cardFrontImg = cardFrontImg;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
