package com.lckj.jycm.network;

import android.text.TextUtils;

import com.lckj.framework.network.HttpResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MerchantListBean extends HttpResponse {

    /**
     * data : {"page":{"currentPageNo":1,"pageSize":10,"startIndex":0,"totalPageNum":1,"totalRecord":4,"lastPage":true},"list":[{"id":2,"frontUserId":11,"merName":"展涛大厦八元里自选快餐","userName":"联系人","userTel":"13802211221","industry":"1","localtion":"民治大道223号","lng":"113.93029","lat":"22.53291","showImg":"1234abc.img,234cac.img","busLic":"12344321abc.img","status":1,"remark":null,"createTime":null,"createBy":"18682031914","createDate":"2019-04-10 16:44:08","updateBy":null,"updateDate":null,"distance":"16080.7"}]}
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


    public static class DataBean {
        /**
         * page : {"currentPageNo":1,"pageSize":10,"startIndex":0,"totalPageNum":1,"totalRecord":4,"lastPage":true}
         * list : [{"id":2,"frontUserId":11,"merName":"展涛大厦八元里自选快餐","userName":"联系人","userTel":"13802211221","industry":"1","localtion":"民治大道223号","lng":"113.93029","lat":"22.53291","showImg":"1234abc.img,234cac.img","busLic":"12344321abc.img","status":1,"remark":null,"createTime":null,"createBy":"18682031914","createDate":"2019-04-10 16:44:08","updateBy":null,"updateDate":null,"distance":"16080.7"}]
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
             * totalRecord : 4
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
             * id : 2
             * frontUserId : 11
             * merName : 展涛大厦八元里自选快餐
             * userName : 联系人
             * userTel : 13802211221
             * industry : 1
             * localtion : 民治大道223号
             * lng : 113.93029
             * lat : 22.53291
             * showImg : 1234abc.img,234cac.img
             * busLic : 12344321abc.img
             * status : 1
             * remark : null
             * createTime : null
             * createBy : 18682031914
             * createDate : 2019-04-10 16:44:08
             * updateBy : null
             * updateDate : null
             * distance : 16080.7
             */

            private int id;
            private int frontUserId;
            private String merName;
            private String userName;
            private String userTel;
            private String industry;
            private String localtion;
            private String lng;
            private String lat;
            private String showImg;
            private String busLic;
            private int status;
            private Object remark;
            private Object createTime;
            private String createBy;
            private String createDate;
            private Object updateBy;
            private Object updateDate;
            private String distance;
            private String proCity;

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

            public String getMerName() {
                return merName;
            }

            public void setMerName(String merName) {
                this.merName = merName;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserTel() {
                return userTel;
            }

            public void setUserTel(String userTel) {
                this.userTel = userTel;
            }

            public String getIndustry() {
                return industry;
            }
            public int getIndustryIndex() {
                if (TextUtils.isEmpty(industry))industry = "1";
                int i = Integer.parseInt(industry);
                if (i == 0)i = 1;
                return i-1;
            }


            public void setIndustry(String industry) {
                this.industry = industry;
            }

            public String getLocaltion() {
                return localtion;
            }

            public void setLocaltion(String localtion) {
                this.localtion = localtion;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getShowImg() {
                if (showImg==null)showImg = "";
                return showImg;
            }

            public String getFirstImg() {
                if (TextUtils.isEmpty(showImg)){
                    showImg ="";
                }
                return showImg.split(",")[0];
            }

            public void setShowImg(String showImg) {
                this.showImg = showImg;
            }

            public String getBusLic() {
                return busLic;
            }

            public void setBusLic(String busLic) {
                this.busLic = busLic;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
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

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getProCity() {
                return proCity;
            }

            public void setProCity(String proCity) {
                this.proCity = proCity;
            }
        }
    }
}