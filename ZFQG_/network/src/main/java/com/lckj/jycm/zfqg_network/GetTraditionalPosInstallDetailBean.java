package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetTraditionalPosInstallDetailBean extends HttpResponse {

    /**
     * data : {"traditionalPosInstallDetail":{"biz_code":"00","agent_id":"","mer_code":"847584453990324","settle_flag":"","sdk_push_key":"sdk name","biz_msg":"装机完成","cre_datetime":"2019-09-16 10:45:04","merchant_name":"深圳市万和宝百货","source":"JSH"},"terminalList":[{"sim_card":"","is_take_machi":"","machine_id":"ME318866115","terminal":"99980258"}]}
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
         * traditionalPosInstallDetail : {"biz_code":"00","agent_id":"","mer_code":"847584453990324","settle_flag":"","sdk_push_key":"sdk name","biz_msg":"装机完成","cre_datetime":"2019-09-16 10:45:04","merchant_name":"深圳市万和宝百货","source":"JSH"}
         * terminalList : [{"sim_card":"","is_take_machi":"","machine_id":"ME318866115","terminal":"99980258"}]
         */

        private TraditionalPosInstallDetailBean traditionalPosInstallDetail;
        private List<TerminalListBean> terminalList;

        public TraditionalPosInstallDetailBean getTraditionalPosInstallDetail() {
            return traditionalPosInstallDetail;
        }

        public void setTraditionalPosInstallDetail(TraditionalPosInstallDetailBean traditionalPosInstallDetail) {
            this.traditionalPosInstallDetail = traditionalPosInstallDetail;
        }

        public List<TerminalListBean> getTerminalList() {
            return terminalList;
        }

        public void setTerminalList(List<TerminalListBean> terminalList) {
            this.terminalList = terminalList;
        }

        public static class TraditionalPosInstallDetailBean {
            /**
             * biz_code : 00
             * agent_id :
             * mer_code : 847584453990324
             * settle_flag :
             * sdk_push_key : sdk name
             * biz_msg : 装机完成
             * cre_datetime : 2019-09-16 10:45:04
             * merchant_name : 深圳市万和宝百货
             * source : JSH
             */

            private String biz_code;
            private String agent_id;
            private String mer_code;
            private String settle_flag;
            private String sdk_push_key;
            private String biz_msg;
            private String cre_datetime;
            private String merchant_name;
            private String source;

            public String getBiz_code() {
                return biz_code;
            }

            public void setBiz_code(String biz_code) {
                this.biz_code = biz_code;
            }

            public String getAgent_id() {
                return agent_id;
            }

            public void setAgent_id(String agent_id) {
                this.agent_id = agent_id;
            }

            public String getMer_code() {
                return mer_code;
            }

            public void setMer_code(String mer_code) {
                this.mer_code = mer_code;
            }

            public String getSettle_flag() {
                return settle_flag;
            }

            public void setSettle_flag(String settle_flag) {
                this.settle_flag = settle_flag;
            }

            public String getSdk_push_key() {
                return sdk_push_key;
            }

            public void setSdk_push_key(String sdk_push_key) {
                this.sdk_push_key = sdk_push_key;
            }

            public String getBiz_msg() {
                return biz_msg;
            }

            public void setBiz_msg(String biz_msg) {
                this.biz_msg = biz_msg;
            }

            public String getCre_datetime() {
                return cre_datetime;
            }

            public void setCre_datetime(String cre_datetime) {
                this.cre_datetime = cre_datetime;
            }

            public String getMerchant_name() {
                return merchant_name;
            }

            public void setMerchant_name(String merchant_name) {
                this.merchant_name = merchant_name;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }
        }

        public static class TerminalListBean {
            /**
             * sim_card :
             * is_take_machi :
             * machine_id : ME318866115
             * terminal : 99980258
             */

            private String sim_card;
            private String is_take_machi;
            private String machine_id;
            private String terminal;

            public String getSim_card() {
                return sim_card;
            }

            public void setSim_card(String sim_card) {
                this.sim_card = sim_card;
            }

            public String getIs_take_machi() {
                return is_take_machi;
            }

            public void setIs_take_machi(String is_take_machi) {
                this.is_take_machi = is_take_machi;
            }

            public String getMachine_id() {
                return machine_id;
            }

            public void setMachine_id(String machine_id) {
                this.machine_id = machine_id;
            }

            public String getTerminal() {
                return terminal;
            }

            public void setTerminal(String terminal) {
                this.terminal = terminal;
            }
        }
    }
}
