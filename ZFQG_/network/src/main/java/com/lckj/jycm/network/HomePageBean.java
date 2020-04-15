package com.lckj.jycm.network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class HomePageBean extends HttpResponse {

    /**
     * data : {"homePageBanner":"Fl4v_kUP9iBmygphHmLfjN-b42bb,FndizcuGWyl88uvnYzVtB_XfxA1W,Fm0g7R-UeLWRnPdlLI4ZLaRlfCGj","list":[{"id":2,"artTitle":"40岁来临前，给你7点建议","artIntro":"40岁来临测试数据。。可以的","artType":"互联网行业","artCover":"https://mmbiz.qpic.cn/mmbiz_jpg/tvjCIzFrpTv2FpSxcsYsBuCQNiblLEeTrfibfq3DJZiaTlU78WV37jSMQ8znbA6lehMnzFJzyRN7gNVuuwlD22vqw/640?wx_fmt=jpeg","artUrl":"https://mp.weixin.qq.com/s/-RvQVENAecxLGr1SrdSLpQ","auditStatus":2,"shareNum":0,"source":"程序员的那些事","createBy":"18682031914","createDate":"2019-04-09 18:47:29","updateBy":null,"updateDate":null,"remark":null,"frontUserId":11}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * homePageBanner : Fl4v_kUP9iBmygphHmLfjN-b42bb,FndizcuGWyl88uvnYzVtB_XfxA1W,Fm0g7R-UeLWRnPdlLI4ZLaRlfCGj
         * list : [{"id":2,"artTitle":"40岁来临前，给你7点建议","artIntro":"40岁来临测试数据。。可以的","artType":"互联网行业","artCover":"https://mmbiz.qpic.cn/mmbiz_jpg/tvjCIzFrpTv2FpSxcsYsBuCQNiblLEeTrfibfq3DJZiaTlU78WV37jSMQ8znbA6lehMnzFJzyRN7gNVuuwlD22vqw/640?wx_fmt=jpeg","artUrl":"https://mp.weixin.qq.com/s/-RvQVENAecxLGr1SrdSLpQ","auditStatus":2,"shareNum":0,"source":"程序员的那些事","createBy":"18682031914","createDate":"2019-04-09 18:47:29","updateBy":null,"updateDate":null,"remark":null,"frontUserId":11}]
         */

        private String homePageBanner;
        private List<ListBean> list;

        public String getHomePageBanner() {
            return homePageBanner;
        }

        public void setHomePageBanner(String homePageBanner) {
            this.homePageBanner = homePageBanner;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 2
             * artTitle : 40岁来临前，给你7点建议
             * artIntro : 40岁来临测试数据。。可以的
             * artType : 互联网行业
             * artCover : https://mmbiz.qpic.cn/mmbiz_jpg/tvjCIzFrpTv2FpSxcsYsBuCQNiblLEeTrfibfq3DJZiaTlU78WV37jSMQ8znbA6lehMnzFJzyRN7gNVuuwlD22vqw/640?wx_fmt=jpeg
             * artUrl : https://mp.weixin.qq.com/s/-RvQVENAecxLGr1SrdSLpQ
             * auditStatus : 2
             * shareNum : 0
             * source : 程序员的那些事
             * createBy : 18682031914
             * createDate : 2019-04-09 18:47:29
             * updateBy : null
             * updateDate : null
             * remark : null
             * frontUserId : 11
             */

            private int id;
            private String artTitle;
            private String artIntro;
            private String artType;
            private String artCover;
            private String artUrl;
            private int auditStatus;
            private int shareNum;
            private String source;
            private String createBy;
            private String createDate;
            private Object updateBy;
            private Object updateDate;
            private Object remark;
            private int frontUserId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getArtTitle() {
                return artTitle;
            }

            public void setArtTitle(String artTitle) {
                this.artTitle = artTitle;
            }

            public String getArtIntro() {
                return artIntro;
            }

            public void setArtIntro(String artIntro) {
                this.artIntro = artIntro;
            }

            public String getArtType() {
                return artType;
            }

            public void setArtType(String artType) {
                this.artType = artType;
            }

            public String getArtCover() {
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

            public int getAuditStatus() {
                return auditStatus;
            }

            public void setAuditStatus(int auditStatus) {
                this.auditStatus = auditStatus;
            }

            public int getShareNum() {
                return shareNum;
            }

            public void setShareNum(int shareNum) {
                this.shareNum = shareNum;
            }

            public String getSource() {
                if (source==null)source = "";
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getCreateBy() {
                return createBy;
            }

            public void setCreateBy(String createBy) {
                this.createBy = createBy;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
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

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public int getFrontUserId() {
                return frontUserId;
            }

            public void setFrontUserId(int frontUserId) {
                this.frontUserId = frontUserId;
            }
        }
    }
}
