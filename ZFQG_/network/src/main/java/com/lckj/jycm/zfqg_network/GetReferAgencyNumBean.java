package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

public class GetReferAgencyNumBean extends HttpResponse {

    /**
     * data : {"referer_num":"0"}
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
         * referer_num : 0
         */

        public String referer_num;
        public String m_pos_num;
        public String m_act_num;
        public String m_inact_num;
        public String tra_pos_num;
        public String tra_act_num;
        public String tra_inact_num;
        public String e_pos_num;
        public String e_act_num;
        public String e_inact_num;

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

        public void setTra_inact_num(String tra_inact_num) {
            this.tra_inact_num = tra_inact_num;
        }

        public String getReferer_num() {
            return referer_num;
        }

        public void setReferer_num(String referer_num) {
            this.referer_num = referer_num;
        }
    }
}
