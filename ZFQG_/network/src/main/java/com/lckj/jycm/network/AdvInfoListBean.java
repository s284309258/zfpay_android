package com.lckj.jycm.network;

import com.lckj.framework.network.HttpResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdvInfoListBean extends HttpResponse {


    /**
     * data : {"token":null,"sign":null,"page":{"currentPageNo":1,"pageSize":10,"startIndex":0,"totalPageNum":1,"totalRecord":2,"lastPage":true},"list":[{"id":1,"frontUserId":11,"artId":null,"advName":null,"advTitle":"雷神鼠标 很好用","proCode":null,"cityCode":null,"areaCode":null,"putType":1,"putTime":null,"price":null,"putBudget":0,"revShare":0,"resBal":null,"shareNum":0,"limitDay":0,"advTxt":null,"advImg":"http://mmbiz.qpic.cn/mmbiz_jpg/nhlGsolibOWFbzP77VCGbSdfo8XTA6txicdJmGVRQjMwTyzPQXx2tedia7jZPCB7BaGglCELzjNVCnPqPZcSmucEQ/0?wx_fmt=jpeg","advVideo":null,"advIntro":"好用 性价比高。。游戏爱好者标配","status":1,"validStatus":1,"validDate":null,"remark":null,"createTime":null,"createBy":"18682031914","createDate":"2019-04-11 17:50:34","updateBy":null,"updateDate":null,"headPhoto":"http://wx.qlogo.cn/mmhead/Q3auHgzwzM4QwSA6icU9XIJNzeh8G5wricAZG8Ippd9MFZlGSTlOz9aQ/132","advUrl":"https://mp.weixin.qq.com/s/m7vWM9wq6zYXqJF0KqWafeffeffw","advArt":{"id":1,"artTitle":"14 年经验程序员的吐槽：如何招不到程序员？","artIntro":"程序员相关事情，可以的测试新数据。。。","artType":"互联网行业","artCover":"https://mmbiz.qpic.cn/mmbiz_png/2A8tXicCG8ymIrwF8nkfUPCzy8f62pVE2jdUE5jECbj0x5E1Z6gC9rbbbvss8yZpZqqo5EB8iaGqOjVpqaJALzQ/640?wx_fmt=png","artUrl":"https://mp.weixin.qq.com/s/IVDCx8dpyq_481b-kfWtWwfefefe ","auditStatus":1,"shareNum":0,"source":"程序员的那些事","createBy":"18682031914","createDate":"2019-04-09 16:52:30","updateBy":null,"updateDate":null,"remark":null,"frontUserId":11}}]}
     * token : null
     * code : null
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
         * token : null
         * sign : null
         * page : {"currentPageNo":1,"pageSize":10,"startIndex":0,"totalPageNum":1,"totalRecord":2,"lastPage":true}
         * list : [{"id":1,"frontUserId":11,"artId":null,"advName":null,"advTitle":"雷神鼠标 很好用","proCode":null,"cityCode":null,"areaCode":null,"putType":1,"putTime":null,"price":null,"putBudget":0,"revShare":0,"resBal":null,"shareNum":0,"limitDay":0,"advTxt":null,"advImg":"http://mmbiz.qpic.cn/mmbiz_jpg/nhlGsolibOWFbzP77VCGbSdfo8XTA6txicdJmGVRQjMwTyzPQXx2tedia7jZPCB7BaGglCELzjNVCnPqPZcSmucEQ/0?wx_fmt=jpeg","advVideo":null,"advIntro":"好用 性价比高。。游戏爱好者标配","status":1,"validStatus":1,"validDate":null,"remark":null,"createTime":null,"createBy":"18682031914","createDate":"2019-04-11 17:50:34","updateBy":null,"updateDate":null,"headPhoto":"http://wx.qlogo.cn/mmhead/Q3auHgzwzM4QwSA6icU9XIJNzeh8G5wricAZG8Ippd9MFZlGSTlOz9aQ/132","advUrl":"https://mp.weixin.qq.com/s/m7vWM9wq6zYXqJF0KqWafeffeffw","advArt":{"id":1,"artTitle":"14 年经验程序员的吐槽：如何招不到程序员？","artIntro":"程序员相关事情，可以的测试新数据。。。","artType":"互联网行业","artCover":"https://mmbiz.qpic.cn/mmbiz_png/2A8tXicCG8ymIrwF8nkfUPCzy8f62pVE2jdUE5jECbj0x5E1Z6gC9rbbbvss8yZpZqqo5EB8iaGqOjVpqaJALzQ/640?wx_fmt=png","artUrl":"https://mp.weixin.qq.com/s/IVDCx8dpyq_481b-kfWtWwfefefe ","auditStatus":1,"shareNum":0,"source":"程序员的那些事","createBy":"18682031914","createDate":"2019-04-09 16:52:30","updateBy":null,"updateDate":null,"remark":null,"frontUserId":11}}]
         */

        private PageBean page;
        private List<ListBean> list;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public List<ListBean> getList() {
            if(list == null)list = new ArrayList<ListBean>();
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
             * totalRecord : 2
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

        public static class ListBean implements Serializable {
            /**
             * id : 1
             * frontUserId : 11
             * artId : null
             * advName : null
             * advTitle : 雷神鼠标 很好用
             * proCode : null
             * cityCode : null
             * areaCode : null
             * putType : 1
             * putTime : null
             * price : null
             * putBudget : 0
             * revShare : 0
             * resBal : null
             * shareNum : 0
             * limitDay : 0
             * advTxt : null
             * advImg : http://mmbiz.qpic.cn/mmbiz_jpg/nhlGsolibOWFbzP77VCGbSdfo8XTA6txicdJmGVRQjMwTyzPQXx2tedia7jZPCB7BaGglCELzjNVCnPqPZcSmucEQ/0?wx_fmt=jpeg
             * advVideo : null
             * advIntro : 好用 性价比高。。游戏爱好者标配
             * status : 1
             * validStatus : 1
             * validDate : null
             * remark : null
             * createTime : null
             * createBy : 18682031914
             * createDate : 2019-04-11 17:50:34
             * updateBy : null
             * updateDate : null
             * headPhoto : http://wx.qlogo.cn/mmhead/Q3auHgzwzM4QwSA6icU9XIJNzeh8G5wricAZG8Ippd9MFZlGSTlOz9aQ/132
             * advUrl : https://mp.weixin.qq.com/s/m7vWM9wq6zYXqJF0KqWafeffeffw
             * advArt : {"id":1,"artTitle":"14 年经验程序员的吐槽：如何招不到程序员？","artIntro":"程序员相关事情，可以的测试新数据。。。","artType":"互联网行业","artCover":"https://mmbiz.qpic.cn/mmbiz_png/2A8tXicCG8ymIrwF8nkfUPCzy8f62pVE2jdUE5jECbj0x5E1Z6gC9rbbbvss8yZpZqqo5EB8iaGqOjVpqaJALzQ/640?wx_fmt=png","artUrl":"https://mp.weixin.qq.com/s/IVDCx8dpyq_481b-kfWtWwfefefe ","auditStatus":1,"shareNum":0,"source":"程序员的那些事","createBy":"18682031914","createDate":"2019-04-09 16:52:30","updateBy":null,"updateDate":null,"remark":null,"frontUserId":11}
             */

            private int id;
            private int frontUserId;
            private Object artId;
            private Object advName;
            private String advTitle;
            private Object proCode;
            private Object cityCode;
            private Object areaCode;
            private int putType;
            private Object putTime;
            private Object price;
            private String putBudget;
            private String revShare;
            private Object resBal;
            private int shareNum;
            private int limitDay;
            private Object advTxt;
            private String advImg;
            private Object advVideo;
            private String advIntro;
            private int status;
            private int validStatus;
            private Object validDate;
            private Object remark;
            private Object createTime;
            private String createBy;
            private String createDate;
            private Object updateBy;
            private Object updateDate;
            private String headPhoto;
            private String advUrl;
            private AdvArtBean advArt;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getFrontUserId() {
                return frontUserId;
            }

            public void setFrontUserId(int frontUserId) {
                this.frontUserId = frontUserId;
            }

            public Object getArtId() {
                return artId;
            }

            public void setArtId(Object artId) {
                this.artId = artId;
            }

            public Object getAdvName() {
                return advName;
            }

            public void setAdvName(Object advName) {
                this.advName = advName;
            }

            public String getAdvTitle() {
                return advTitle;
            }

            public void setAdvTitle(String advTitle) {
                this.advTitle = advTitle;
            }

            public Object getProCode() {
                return proCode;
            }

            public void setProCode(Object proCode) {
                this.proCode = proCode;
            }

            public Object getCityCode() {
                return cityCode;
            }

            public void setCityCode(Object cityCode) {
                this.cityCode = cityCode;
            }

            public Object getAreaCode() {
                return areaCode;
            }

            public void setAreaCode(Object areaCode) {
                this.areaCode = areaCode;
            }

            public int getPutType() {
                return putType;
            }

            public void setPutType(int putType) {
                this.putType = putType;
            }

            public Object getPutTime() {
                return putTime;
            }

            public void setPutTime(Object putTime) {
                this.putTime = putTime;
            }

            public Object getPrice() {
                return price;
            }

            public void setPrice(Object price) {
                this.price = price;
            }

            public String getPutBudget() {
                return putBudget;
            }

            public void setPutBudget(String putBudget) {
                this.putBudget = putBudget;
            }

            public String getRevShare() {
                return revShare;
            }

            public void setRevShare(String revShare) {
                this.revShare = revShare;
            }

            public Object getResBal() {
                return resBal;
            }

            public void setResBal(Object resBal) {
                this.resBal = resBal;
            }

            public int getShareNum() {
                return shareNum;
            }

            public void setShareNum(int shareNum) {
                this.shareNum = shareNum;
            }

            public int getLimitDay() {
                return limitDay;
            }

            public void setLimitDay(int limitDay) {
                this.limitDay = limitDay;
            }

            public Object getAdvTxt() {
                return advTxt;
            }

            public void setAdvTxt(Object advTxt) {
                this.advTxt = advTxt;
            }

            public String getAdvImg() {
                return advImg;
            }

            public void setAdvImg(String advImg) {
                this.advImg = advImg;
            }

            public Object getAdvVideo() {
                return advVideo;
            }

            public void setAdvVideo(Object advVideo) {
                this.advVideo = advVideo;
            }

            public String getAdvIntro() {
                return advIntro;
            }

            public void setAdvIntro(String advIntro) {
                this.advIntro = advIntro;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getValidStatus() {
                return validStatus;
            }

            public void setValidStatus(int validStatus) {
                this.validStatus = validStatus;
            }

            public Object getValidDate() {
                return validDate;
            }

            public void setValidDate(Object validDate) {
                this.validDate = validDate;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
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

            public String getHeadPhoto() {
                return headPhoto;
            }

            public void setHeadPhoto(String headPhoto) {
                this.headPhoto = headPhoto;
            }

            public String getAdvUrl() {
                return advUrl;
            }

            public void setAdvUrl(String advUrl) {
                this.advUrl = advUrl;
            }

            public AdvArtBean getAdvArt() {
                return advArt;
            }

            public void setAdvArt(AdvArtBean advArt) {
                this.advArt = advArt;
            }

            public static class AdvArtBean implements Serializable{
                /**
                 * id : 1
                 * artTitle : 14 年经验程序员的吐槽：如何招不到程序员？
                 * artIntro : 程序员相关事情，可以的测试新数据。。。
                 * artType : 互联网行业
                 * artCover : https://mmbiz.qpic.cn/mmbiz_png/2A8tXicCG8ymIrwF8nkfUPCzy8f62pVE2jdUE5jECbj0x5E1Z6gC9rbbbvss8yZpZqqo5EB8iaGqOjVpqaJALzQ/640?wx_fmt=png
                 * artUrl : https://mp.weixin.qq.com/s/IVDCx8dpyq_481b-kfWtWwfefefe
                 * auditStatus : 1
                 * shareNum : 0
                 * source : 程序员的那些事
                 * createBy : 18682031914
                 * createDate : 2019-04-09 16:52:30
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
}
