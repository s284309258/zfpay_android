package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetTraditionalPosTradeDetailBean extends HttpResponse {

    /**
     * data : {"traditionalPosTradeDetail":[{"benefit_money":"0.17","trans_time":"2019-12-23 16:47:41","trans_amount":"486"},{"benefit_money":"0.06","trans_time":"2019-12-24 08:55:42","trans_amount":"188"},{"benefit_money":"0.10","trans_time":"2019-12-24 14:50:16","trans_amount":"543"},{"benefit_money":"0.17","trans_time":"2019-12-24 20:32:31","trans_amount":"878"},{"benefit_money":"0.12","trans_time":"2019-12-24 17:34:08","trans_amount":"348"},{"benefit_money":"0.30","trans_time":"2019-12-24 15:20:14","trans_amount":"876"},{"benefit_money":"0.19","trans_time":"2019-12-23 23:14:39","trans_amount":"985"},{"benefit_money":"0.08","trans_time":"2019-12-25 21:57:30","trans_amount":"449"},{"benefit_money":"0.06","trans_time":"2019-12-25 16:22:25","trans_amount":"345"},{"benefit_money":"0.15","trans_time":"2019-12-25 11:32:49","trans_amount":"782"},{"benefit_money":"0.04","trans_time":"2019-12-26 21:30:53","trans_amount":"216"},{"benefit_money":"0.18","trans_time":"2019-12-26 21:26:56","trans_amount":"936"},{"benefit_money":"2.44","trans_time":"2019-12-26 16:24:27","trans_amount":"6980"},{"benefit_money":"0.26","trans_time":"2019-12-26 16:02:36","trans_amount":"753"},{"benefit_money":"0.82","trans_time":"2019-12-26 11:26:01","trans_amount":"2356"},{"benefit_money":"0.09","trans_time":"2020-01-03 23:00:45","trans_amount":"482"},{"benefit_money":"0.17","trans_time":"2020-01-04 11:57:32","trans_amount":"896"},{"benefit_money":"0.34","trans_time":"2020-01-04 12:04:32","trans_amount":"983"},{"benefit_money":"0.11","trans_time":"2020-01-04 17:44:23","trans_amount":"566"},{"benefit_money":"0.07","trans_time":"2020-01-04 18:25:06","trans_amount":"365"},{"benefit_money":"0.04","trans_time":"2020-01-05 11:17:00","trans_amount":"238"},{"benefit_money":"0.17","trans_time":"2020-01-05 11:28:09","trans_amount":"880"},{"benefit_money":"0.18","trans_time":"2020-01-05 11:51:47","trans_amount":"921"},{"benefit_money":"0.15","trans_time":"2020-01-06 11:34:24","trans_amount":"789"},{"benefit_money":"0.27","trans_time":"2020-01-06 20:39:54","trans_amount":"785"},{"benefit_money":"1.24","trans_time":"2020-01-06 21:24:29","trans_amount":"3568"},{"benefit_money":"0.03","trans_time":"2020-01-06 21:44:17","trans_amount":"99"},{"benefit_money":"0.01","trans_time":"2020-01-06 22:59:07","trans_amount":"50"},{"benefit_money":"0.03","trans_time":"2020-01-06 23:03:14","trans_amount":"89"},{"benefit_money":"0.16","trans_time":"2020-01-06 12:42:32","trans_amount":"812"},{"benefit_money":"0.17","trans_time":"2020-01-06 11:56:20","trans_amount":"858"},{"benefit_money":"0.01","trans_time":"2020-01-07 09:37:27","trans_amount":"68"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<TraditionalPosTradeDetailBean> traditionalPosTradeDetail;

        public List<TraditionalPosTradeDetailBean> getTraditionalPosTradeDetail() {
            return traditionalPosTradeDetail;
        }

        public void setTraditionalPosTradeDetail(List<TraditionalPosTradeDetailBean> traditionalPosTradeDetail) {
            this.traditionalPosTradeDetail = traditionalPosTradeDetail;
        }

        public static class TraditionalPosTradeDetailBean {
            /**
             * benefit_money : 0.17
             * trans_time : 2019-12-23 16:47:41
             * trans_amount : 486
             */

            private String benefit_money;
            private String trans_time;
            private String trans_amount;

            public String getBenefit_money() {
                return benefit_money;
            }

            public void setBenefit_money(String benefit_money) {
                this.benefit_money = benefit_money;
            }

            public String getTrans_time() {
                return trans_time;
            }

            public void setTrans_time(String trans_time) {
                this.trans_time = trans_time;
            }

            public String getTrans_amount() {
                return trans_amount;
            }

            public void setTrans_amount(String trans_amount) {
                this.trans_amount = trans_amount;
            }
        }
    }
}
