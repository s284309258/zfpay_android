package com.lckj.jycm.network;

import android.text.TextUtils;

import com.lckj.framework.network.HttpResponse;

import java.util.ArrayList;
import java.util.List;

public class AccountRecordListBean extends HttpResponse {
    /**
     * msg : 请求成功
     * success : true
     * data : {"list":[{"flowingId":13,"accountId":6,"flowingNo":"QD1554884568237","assetsBefrom":20,"assetsAfter":30,"assetsChange":10,"changeTxt":"+10","orderNo":null,"type":1,"status":1,"dealType":"每日签到","dealDesc":"每日连续签到获得的奖励","createTime":null,"createBy":null,"createDate":"2019-04-08 16:22:48","updateBy":null,"updateDate":null}],"fuAccount":{"id":6,"gold":1250,"amount":0,"sumAmount":0,"createBy":null,"createDate":"2019-04-08 14:26:21","updateBy":null,"updateDate":"2019-04-22 09:58:57","teamMember":null,"shareReward":null,"yesdayEarn":18}}
     * token : null
     * code : null
     */

    private DataBean data;

    public DataBean getData() {
        if (data == null) data = new DataBean();
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * list : [{"flowingId":13,"accountId":6,"flowingNo":"QD1554884568237","assetsBefrom":20,"assetsAfter":30,"assetsChange":10,"changeTxt":"+10","orderNo":null,"type":1,"status":1,"dealType":"每日签到","dealDesc":"每日连续签到获得的奖励","createTime":null,"createBy":null,"createDate":"2019-04-08 16:22:48","updateBy":null,"updateDate":null}]
         * fuAccount : {"id":6,"gold":1250,"amount":0,"sumAmount":0,"createBy":null,"createDate":"2019-04-08 14:26:21","updateBy":null,"updateDate":"2019-04-22 09:58:57","teamMember":null,"shareReward":null,"yesdayEarn":18}
         */

        private FuAccountBean fuAccount;
        private List<ListBean> list;

        public FuAccountBean getFuAccount() {
            return fuAccount;
        }

        public void setFuAccount(FuAccountBean fuAccount) {
            this.fuAccount = fuAccount;
        }

        public List<ListBean> getList() {
            if (list == null) list = new ArrayList<ListBean>();
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class FuAccountBean {
            /**
             * id : 6
             * gold : 1250
             * amount : 0
             * sumAmount : 0
             * createBy : null
             * createDate : 2019-04-08 14:26:21
             * updateBy : null
             * updateDate : 2019-04-22 09:58:57
             * teamMember : null
             * shareReward : null
             * yesdayEarn : 18
             */

            private int id;
            private String gold;
            private String amount;
            private String sumAmount;
            private Object createBy;
            private String createDate;
            private Object updateBy;
            private String updateDate;
            private Object teamMember;
            private Object shareReward;
            private String yesdayEarn;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getGold() {
                if (TextUtils.isEmpty(gold)) gold = "0.00";
                return gold;
            }

            public void setGold(String gold) {
                this.gold = gold;
            }

            public String getAmount() {
                if (TextUtils.isEmpty(amount)) amount = "0.00";
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getSumAmount() {
                if (TextUtils.isEmpty(sumAmount)) sumAmount = "0.00";
                return sumAmount;
            }

            public void setSumAmount(String sumAmount) {
                this.sumAmount = sumAmount;
            }

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
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

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }

            public Object getTeamMember() {
                return teamMember;
            }

            public void setTeamMember(Object teamMember) {
                this.teamMember = teamMember;
            }

            public Object getShareReward() {
                return shareReward;
            }

            public void setShareReward(Object shareReward) {
                this.shareReward = shareReward;
            }

            public String getYesdayEarn() {
                if (TextUtils.isEmpty(yesdayEarn)) yesdayEarn = "0.00";
                return yesdayEarn;
            }

            public void setYesdayEarn(String yesdayEarn) {
                this.yesdayEarn = yesdayEarn;
            }
        }

        public static class ListBean {
            /**
             * flowingId : 13
             * accountId : 6
             * flowingNo : QD1554884568237
             * assetsBefrom : 20
             * assetsAfter : 30
             * assetsChange : 10
             * changeTxt : +10
             * orderNo : null
             * type : 1
             * status : 1
             * dealType : 每日签到
             * dealDesc : 每日连续签到获得的奖励
             * createTime : null
             * createBy : null
             * createDate : 2019-04-08 16:22:48
             * updateBy : null
             * updateDate : null
             */

            private int flowingId;
            private int accountId;
            private String flowingNo;
            private String assetsBefrom;
            private String assetsAfter;
            private String assetsChange;
            private String changeTxt;
            private Object orderNo;
            private int type;
            private int status;
            private String dealType;
            private String dealDesc;
            private Object createTime;
            private Object createBy;
            private String createDate;
            private Object updateBy;
            private Object updateDate;

            public int getFlowingId() {
                return flowingId;
            }

            public void setFlowingId(int flowingId) {
                this.flowingId = flowingId;
            }

            public int getAccountId() {
                return accountId;
            }

            public void setAccountId(int accountId) {
                this.accountId = accountId;
            }

            public String getFlowingNo() {
                return flowingNo;
            }

            public void setFlowingNo(String flowingNo) {
                this.flowingNo = flowingNo;
            }

            public String getAssetsBefrom() {
                return assetsBefrom;
            }

            public void setAssetsBefrom(String assetsBefrom) {
                this.assetsBefrom = assetsBefrom;
            }

            public String getAssetsAfter() {
                return assetsAfter;
            }

            public void setAssetsAfter(String assetsAfter) {
                this.assetsAfter = assetsAfter;
            }

            public String getAssetsChange() {
                return assetsChange;
            }

            public void setAssetsChange(String assetsChange) {
                this.assetsChange = assetsChange;
            }

            public String getChangeTxt() {
                return changeTxt;
            }

            public void setChangeTxt(String changeTxt) {
                this.changeTxt = changeTxt;
            }

            public Object getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(Object orderNo) {
                this.orderNo = orderNo;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getDealType() {
                return dealType;
            }

            public void setDealType(String dealType) {
                this.dealType = dealType;
            }

            public String getDealDesc() {
                return dealDesc;
            }

            public void setDealDesc(String dealDesc) {
                this.dealDesc = dealDesc;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
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
        }
    }
}
