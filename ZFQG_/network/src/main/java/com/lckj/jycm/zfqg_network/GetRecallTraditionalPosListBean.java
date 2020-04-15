package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetRecallTraditionalPosListBean extends HttpResponse {

    /**
     * data : {"recallTraditionalPosList":[{"cre_datetime":"2019-08-24 18:08:04","recall_id":"2","sn":"95476182"},{"cre_datetime":"2019-08-24 18:08:04","recall_id":"1","sn":"91581156"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<RecallTraditionalPosListBean> recallTraditionalPosList;
        private List<RecallTraditionalPosListBean> recallMposList;
        private List<RecallTraditionalPosListBean> recallTrafficCardList;

        public List<RecallTraditionalPosListBean> getRecallMposList() {
            return recallMposList;
        }

        public void setRecallMposList(List<RecallTraditionalPosListBean> recallMposList) {
            this.recallMposList = recallMposList;
        }

        public List<RecallTraditionalPosListBean> getRecallTrafficCardList() {
            return recallTrafficCardList;
        }

        public void setRecallTrafficCardList(List<RecallTraditionalPosListBean> recallTrafficCardList) {
            this.recallTrafficCardList = recallTrafficCardList;
        }

        public List<RecallTraditionalPosListBean> getRecallTraditionalPosList() {
            return recallTraditionalPosList;
        }

        public void setRecallTraditionalPosList(List<RecallTraditionalPosListBean> recallTraditionalPosList) {
            this.recallTraditionalPosList = recallTraditionalPosList;
        }

        public static class RecallTraditionalPosListBean {
            /**
             * cre_datetime : 2019-08-24 18:08:04
             * recall_id : 2
             * sn : 95476182
             */

            private String cre_datetime;
            private String recall_id;
            private String card_no;
            private String sn;

            public String getCard_no() {
                return card_no;
            }

            public void setCard_no(String card_no) {
                this.card_no = card_no;
            }

            public String getCre_datetime() {
                return cre_datetime;
            }

            public void setCre_datetime(String cre_datetime) {
                this.cre_datetime = cre_datetime;
            }

            public String getRecall_id() {
                return recall_id;
            }

            public void setRecall_id(String recall_id) {
                this.recall_id = recall_id;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }
        }
    }
}
