package com.lckj.jycm.zfqg_network;

import android.text.TextUtils;

import com.lckj.framework.network.HttpResponse;

public class GetReferAgencyHeadTraditionalPosInfoBean extends HttpResponse {

    /**
     * data : {"referAgencyHeadTraditionalPosInfo":{"performance":"100000.00","pos_num":"0","trade_num":"0"}}
     */

    public DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * referAgencyHeadTraditionalPosInfo : {"performance":"100000.00","pos_num":"0","trade_num":"0"}
         */

        public ReferAgencyHeadTraditionalPosInfoBean referAgencyHeadTraditionalPosInfo;
        public ReferAgencyHeadTraditionalPosInfoBean referAgencyHeadMposInfo;

        public ReferAgencyHeadTraditionalPosInfoBean getReferAgencyHeadMposInfo() {
            if(referAgencyHeadMposInfo == null)referAgencyHeadMposInfo = new ReferAgencyHeadTraditionalPosInfoBean();
            return referAgencyHeadMposInfo;
        }

        public void setReferAgencyHeadMposInfo(ReferAgencyHeadTraditionalPosInfoBean referAgencyHeadMposInfo) {
            this.referAgencyHeadMposInfo = referAgencyHeadMposInfo;
        }

        public ReferAgencyHeadTraditionalPosInfoBean getReferAgencyHeadTraditionalPosInfo() {
            if(referAgencyHeadTraditionalPosInfo == null)referAgencyHeadTraditionalPosInfo = new ReferAgencyHeadTraditionalPosInfoBean();
            return referAgencyHeadTraditionalPosInfo;
        }

        public void setReferAgencyHeadTraditionalPosInfo(ReferAgencyHeadTraditionalPosInfoBean referAgencyHeadTraditionalPosInfo) {
            this.referAgencyHeadTraditionalPosInfo = referAgencyHeadTraditionalPosInfo;
        }

        public static class ReferAgencyHeadTraditionalPosInfoBean {
            /**
             * performance : 100000.00
             * pos_num : 0
             * trade_num : 0
             */

            public String performance;
            public String pos_num;
            public String trade_num;
            public String m_pos_num;
            public String m_act_num;
            public String m_inact_num;
            public String tra_pos_num;
            public String tra_act_num;
            public String tra_inact_num;
            public String e_pos_num;
            public String e_act_num;
            public String e_inact_num;
            public String real_name;

            public String getE_pos_num() {
                return e_pos_num;
            }

            public void setE_pos_num(String e_pos_num) {
                this.e_pos_num = e_pos_num;
            }

            public String getE_act_num() {
                return e_act_num;
            }

            public void setE_act_num(String e_act_num) {
                this.e_act_num = e_act_num;
            }

            public String getE_inact_num() {
                return e_inact_num;
            }

            public void setE_inact_num(String e_inact_num) {
                this.e_inact_num = e_inact_num;
            }

            public void setTra_inact_num(String tra_inact_num) {
                this.tra_inact_num = tra_inact_num;
            }

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public String getM_pos_num() {
                return m_pos_num;
            }

            public void setM_pos_num(String m_pos_num) {
                this.m_pos_num = m_pos_num;
            }

            public String getM_act_num() {
                return m_act_num;
            }

            public void setM_act_num(String m_act_num) {
                this.m_act_num = m_act_num;
            }

            public String getM_inact_num() {
                return m_inact_num;
            }

            public void setM_inact_num(String m_inact_num) {
                this.m_inact_num = m_inact_num;
            }

            public String getTra_pos_num() {
                return tra_pos_num;
            }

            public void setTra_pos_num(String tra_pos_num) {
                this.tra_pos_num = tra_pos_num;
            }

            public String getTra_act_num() {
                return tra_act_num;
            }

            public void setTra_act_num(String tra_act_num) {
                this.tra_act_num = tra_act_num;
            }

            public String getTra_inact_num() {
                return tra_inact_num;
            }

            public String getPerformance() {
                if(TextUtils.isEmpty(performance))performance = "0";
                return performance;
            }

            public void setPerformance(String performance) {
                this.performance = performance;
            }

            public String getPos_num() {
                if(TextUtils.isEmpty(pos_num))pos_num = "0";
                return pos_num;
            }

            public void setPos_num(String pos_num) {
                this.pos_num = pos_num;
            }

            public String getTrade_num() {
                if(TextUtils.isEmpty(trade_num))trade_num = "0";
                return trade_num;
            }

            public void setTrade_num(String trade_num) {
                this.trade_num = trade_num;
            }
        }
    }
}
