package com.lckj.jycm.network;

import com.lckj.framework.network.HttpResponse;

import java.io.Serializable;

public class ArticleByURLResponse extends HttpResponse {


    /**
     * data : {"token":null,"sign":null,"id":null,"artTitle":"14 年经验程序员的吐槽：如何招不到程序员？","artIntro":null,"artType":null,"artCover":"https://mmbiz.qpic.cn/mmbiz_png/2A8tXicCG8ymIrwFa8nkfUPCzy8f62pVE2jdUE5jECbj0x5E1Z6gC9rbbbvss8yZpZqqo5EB8iaGqOjVpqaJALzQ/640?wx_fmt=png","artUrl":"https://mp.weixin.qq.com/s/IVDCx8dpyq_481b-kfWtWw","auditStatus":null,"shareNum":null,"source":"程序员的那些事","createBy":null,"createDate":null,"updateBy":null,"updateDate":null}
     * token :
     * code :
     */

    private DataBean data;
    private String token;

    public DataBean getData() {
        if (data==null)data = new DataBean();
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public static class DataBean implements Serializable {
        /**
         * token : null
         * sign : null
         * id : null
         * artTitle : 14 年经验程序员的吐槽：如何招不到程序员？
         * artIntro : null
         * artType : null
         * artCover : https://mmbiz.qpic.cn/mmbiz_png/2A8tXicCG8ymIrwFa8nkfUPCzy8f62pVE2jdUE5jECbj0x5E1Z6gC9rbbbvss8yZpZqqo5EB8iaGqOjVpqaJALzQ/640?wx_fmt=png
         * artUrl : https://mp.weixin.qq.com/s/IVDCx8dpyq_481b-kfWtWw
         * auditStatus : null
         * shareNum : null
         * source : 程序员的那些事
         * createBy : null
         * createDate : null
         * updateBy : null
         * updateDate : null
         */

        private Object token;
        private Object sign;
        private Object id;
        private String artTitle;
        private String artIntro;
        private Object artType;
        private String artCover;
        private String artUrl;
        private Object auditStatus;
        private Object shareNum;
        private String source;
        private Object createBy;
        private Object createDate;
        private Object updateBy;
        private Object updateDate;

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
        }

        public Object getSign() {
            return sign;
        }

        public void setSign(Object sign) {
            this.sign = sign;
        }

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public String getArtTitle() {
            if (artTitle == null)artIntro = "";
            return artTitle;
        }

        public void setArtTitle(String artTitle) {
            this.artTitle = artTitle;
        }

        public String getArtIntro() {
            if (artIntro == null)artIntro = "";
            return artIntro;
        }

        public void setArtIntro(String artIntro) {
            this.artIntro = artIntro;
        }

        public Object getArtType() {
            return artType;
        }

        public void setArtType(Object artType) {
            this.artType = artType;
        }

        public String getArtCover() {
            if (artCover==null)artCover = "";
            return artCover;
        }

        public void setArtCover(String artCover) {
            this.artCover = artCover;
        }

        public String getArtUrl() {
            return artUrl;
        }

        public void setArtUrl(String artUrl) {
            this.artUrl = artUrl;
        }

        public Object getAuditStatus() {
            return auditStatus;
        }

        public void setAuditStatus(Object auditStatus) {
            this.auditStatus = auditStatus;
        }

        public Object getShareNum() {
            return shareNum;
        }

        public void setShareNum(Object shareNum) {
            this.shareNum = shareNum;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Object createDate) {
            this.createDate = createDate;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(Object updateDate) {
            this.updateDate = updateDate;
        }
    }
}
