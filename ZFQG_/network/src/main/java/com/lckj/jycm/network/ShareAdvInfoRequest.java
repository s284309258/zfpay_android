package com.lckj.jycm.network;

public class ShareAdvInfoRequest{
    /**
     * advId : 4
     * artId : 2
     * shareChannel : 1
     * sign : C5F30DD8BEAEEEB6AC8F31DD4B54B65D
     * token : 11|TQBSTLIH6Q79CMDIOUP8BKX4KXHUB5BF
     */

    private String system_type = "shareAdvInfo";
    private int advId;
    private int artId;
    private int shareChannel;
    private String token;

    public ShareAdvInfoRequest(int advId, int artId, int shareChannel, String token) {
        this.advId = advId;
        this.artId = artId;
        this.shareChannel = shareChannel;
        this.token = token;
    }

    private String getSystem_type() {
        return system_type;
    }

    private void setSystem_type(String system_type) {
        this.system_type = system_type;
    }

    public int getAdvId() {
        return advId;
    }

    public void setAdvId(int advId) {
        this.advId = advId;
    }

    public int getArtId() {
        return artId;
    }

    public void setArtId(int artId) {
        this.artId = artId;
    }

    public int getShareChannel() {
        return shareChannel;
    }

    public void setShareChannel(int shareChannel) {
        this.shareChannel = shareChannel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
