package com.lckj.zfqg.bean;

public class CardInfo {
    public String name;
    public int name_bg;
    public int Card_bg;
    public String benefit;
    public String merchant_benefit;
    public String agency_benefit;
    public String date;

    public CardInfo(String name, int name_bg, int card_bg, String benefit, String merchant_benefit, String agency_benefit, String date) {
        this.name = name;
        this.name_bg = name_bg;
        Card_bg = card_bg;
        this.benefit = benefit;
        this.merchant_benefit = merchant_benefit;
        this.agency_benefit = agency_benefit;
        this.date = date;
    }


    public CardInfo(String name, int name_bg, int card_bg) {
        this.name = name;
        this.name_bg = name_bg;
        Card_bg = card_bg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public String getMerchant_benefit() {
        return merchant_benefit;
    }

    public void setMerchant_benefit(String merchant_benefit) {
        this.merchant_benefit = merchant_benefit;
    }

    public String getAgency_benefit() {
        return agency_benefit;
    }

    public void setAgency_benefit(String agency_benefit) {
        this.agency_benefit = agency_benefit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getName_bg() {
        return name_bg;
    }

    public void setName_bg(int name_bg) {
        this.name_bg = name_bg;
    }

    public int getCard_bg() {
        return Card_bg;
    }

    public void setCard_bg(int card_bg) {
        Card_bg = card_bg;
    }
}
