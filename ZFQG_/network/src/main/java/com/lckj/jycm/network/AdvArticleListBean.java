package com.lckj.jycm.network;

import com.lckj.framework.network.HttpResponse;

import java.util.ArrayList;
import java.util.List;

public class AdvArticleListBean extends HttpResponse {


    /**
     * data : {"token":null,"sign":null,"page":{"currentPageNo":1,"pageSize":10,"startIndex":0,"totalPageNum":1,"totalRecord":1,"lastPage":true},"list":[{"token":null,"sign":null,"id":2,"artTitle":"14 年经验程序员的吐槽：如何招不到程序员？","artIntro":"要自己写文章简介吗","artType":"互联网行业","artCover":"https://mmbiz.qpic.cn/mmbiz_png/2A8tXicCG8ymIrwFa8nkfUPCzy8f62pVE2jdUE5jECbj0x5E1Z6gC9rbbbvss8yZpZqqo5EB8iaGqOjVpqaJALzQ/640?wx_fmt=png","artUrl":"https://mp.weixin.qq.com/s/IVDCx8dpyq_481b-kfWtWw","auditStatus":1,"shareNum":0,"source":"程序员的那些事","createBy":"18588435864","createDate":"2019-04-09 21:05:19","updateBy":null,"updateDate":null,"remark":null,"frontUserId":14}]}
     * token : “”
     * code : “”
     */

    private DataBean data;
    private String token;

    public DataBean getData() {
        if (data ==null )data = new DataBean();
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


    public static class DataBean {
        /**
         * token : null
         * sign : null
         * page : {"currentPageNo":1,"pageSize":10,"startIndex":0,"totalPageNum":1,"totalRecord":1,"lastPage":true}
         * list : [{"token":null,"sign":null,"id":2,"artTitle":"14 年经验程序员的吐槽：如何招不到程序员？","artIntro":"要自己写文章简介吗","artType":"互联网行业","artCover":"https://mmbiz.qpic.cn/mmbiz_png/2A8tXicCG8ymIrwFa8nkfUPCzy8f62pVE2jdUE5jECbj0x5E1Z6gC9rbbbvss8yZpZqqo5EB8iaGqOjVpqaJALzQ/640?wx_fmt=png","artUrl":"https://mp.weixin.qq.com/s/IVDCx8dpyq_481b-kfWtWw","auditStatus":1,"shareNum":0,"source":"程序员的那些事","createBy":"18588435864","createDate":"2019-04-09 21:05:19","updateBy":null,"updateDate":null,"remark":null,"frontUserId":14}]
         */

        private Object token;
        private Object sign;
        private PageBean page;
        private List<ListBean> list;

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

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public List<ListBean> getList() {
            if (list==null)list = new ArrayList<>();
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PageBean {
            /**
             * currentPageNo : 1
             * pageSize : 10
             * startIndex : 0
             * totalPageNum : 1
             * totalRecord : 1
             * lastPage : true
             */

            private int currentPageNo;
            private int pageSize;
            private int startIndex;
            private int totalPageNum;
            private int totalRecord;
            private boolean lastPage;

            public int getCurrentPageNo() {
                return currentPageNo;
            }

            public void setCurrentPageNo(int currentPageNo) {
                this.currentPageNo = currentPageNo;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getStartIndex() {
                return startIndex;
            }

            public void setStartIndex(int startIndex) {
                this.startIndex = startIndex;
            }

            public int getTotalPageNum() {
                return totalPageNum;
            }

            public void setTotalPageNum(int totalPageNum) {
                this.totalPageNum = totalPageNum;
            }

            public int getTotalRecord() {
                return totalRecord;
            }

            public void setTotalRecord(int totalRecord) {
                this.totalRecord = totalRecord;
            }

            public boolean isLastPage() {
                return lastPage;
            }

            public void setLastPage(boolean lastPage) {
                this.lastPage = lastPage;
            }
        }

        public static class ListBean {
            /**
             * token : null
             * sign : null
             * id : 2
             * artTitle : 14 年经验程序员的吐槽：如何招不到程序员？
             * artIntro : 要自己写文章简介吗
             * artType : 互联网行业
             * artCover : https://mmbiz.qpic.cn/mmbiz_png/2A8tXicCG8ymIrwFa8nkfUPCzy8f62pVE2jdUE5jECbj0x5E1Z6gC9rbbbvss8yZpZqqo5EB8iaGqOjVpqaJALzQ/640?wx_fmt=png
             * artUrl : https://mp.weixin.qq.com/s/IVDCx8dpyq_481b-kfWtWw
             * auditStatus : 1
             * shareNum : 0
             * source : 程序员的那些事
             * createBy : 18588435864
             * createDate : 2019-04-09 21:05:19
             * updateBy : null
             * updateDate : null
             * remark : null
             * frontUserId : 14
             */

            private Object token;
            private Object sign;
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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getArtTitle() {
                if (artTitle==null)artTitle = "";
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
                if (createDate==null)createDate = "";
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
