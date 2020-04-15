package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.math.BigDecimal;

public class GetHomePageInfoBean extends HttpResponse {

    /**
     * data : {"traposDetail":{"agency_trade_num":"0","performance":"0.00","pos_num":"10"},"mposDetail":{"agency_trade_num":"0","performance":"0.00","pos_num":"10"}}
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
         * traposDetail : {"agency_trade_num":"0","performance":"0.00","pos_num":"10"}
         * mposDetail : {"agency_trade_num":"0","performance":"0.00","pos_num":"10"}
         */

        private TraposDetailBean traposDetail;
        private MposDetailBean mposDetail;
        private MposDetailBean eposDetail;

        public MposDetailBean getEposDetail() {
            return eposDetail;
        }

        private void setEposDetail(MposDetailBean eposDetail) {
            this.eposDetail = eposDetail;
        }

        public TraposDetailBean getTraposDetail() {
            return traposDetail;
        }

        public void setTraposDetail(TraposDetailBean traposDetail) {
            this.traposDetail = traposDetail;
        }

        public MposDetailBean getMposDetail() {
            return mposDetail;
        }

        public void setMposDetail(MposDetailBean mposDetail) {
            this.mposDetail = mposDetail;
        }

        public static class TraposDetailBean {
            /**
             * agency_trade_num : 0
             * performance : 0.00
             * pos_num : 10
             */

            private String act_num;
            private String performance;
            private String pos_num;
            private String pos_avg;
            private String act_num_day;

            public String getAct_num_day() {
                return act_num_day;
            }

            public void setAct_num_day(String act_num_day) {
                this.act_num_day = act_num_day;
            }

            public String getPos_avg() {
                return pos_avg;
            }

            public void setPos_avg(String pos_avg) {
                this.pos_avg = pos_avg;
            }

            public String getAct_num() {
                return act_num;
            }

            public void setAct_num(String act_num) {
                this.act_num = act_num;
            }

            public String getPerformance() {
                return performance;
            }

            public void setPerformance(String performance) {
                this.performance = performance;
            }

            public String getPos_num() {
                return pos_num;
            }

            public void setPos_num(String pos_num) {
                this.pos_num = pos_num;
            }
        }

        public static class MposDetailBean {
            /**
             * agency_trade_num : 0
             * performance : 0.00
             * pos_num : 10
             */

            private String act_num;
            private String performance;
            private String pos_num;
            private String pos_avg;
            private String act_num_day;

            public String getAct_num_day() {
                return act_num_day;
            }

            public void setAct_num_day(String act_num_day) {
                this.act_num_day = act_num_day;
            }

            public String getPos_avg() {
                return pos_avg;
            }

            public void setPos_avg(String pos_avg) {
                this.pos_avg = pos_avg;
            }

            public String getAct_num() {
                return act_num;
            }

            public void setAct_num(String act_num) {
                this.act_num = act_num;
            }

            public String getPerformance() {
                return performance;
            }

            public void setPerformance(String performance) {
                this.performance = performance;
            }

            public String getPos_num() {
                return pos_num;
            }

            public void setPos_num(String pos_num) {
                this.pos_num = pos_num;
            }
        }
    }
}
