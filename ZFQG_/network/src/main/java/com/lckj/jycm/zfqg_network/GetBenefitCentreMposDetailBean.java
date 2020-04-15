package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

public class GetBenefitCentreMposDetailBean extends HttpResponse {

    /**
     * data : {"mposBenefitDeatil":{"activity_benefit":"0.00","return_benefit":"0.00","merchant_benefit":"0.00","agency_benefit":"0.00","deduct_money":"0.00","share_benefit":"0.00","benefit":"0.00"}}
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
         * mposBenefitDeatil : {"activity_benefit":"0.00","return_benefit":"0.00","merchant_benefit":"0.00","agency_benefit":"0.00","deduct_money":"0.00","share_benefit":"0.00","benefit":"0.00"}
         */

        private MposBenefitDeatilBean mposBenefitDeatil;
        private MposBenefitDeatilBean traditionalPosBenefitDeatil;

        public MposBenefitDeatilBean getTraditionalPosBenefitDeatil() {
            return traditionalPosBenefitDeatil;
        }

        public void setTraditionalPosBenefitDeatil(MposBenefitDeatilBean traditionalPosBenefitDeatil) {
            this.traditionalPosBenefitDeatil = traditionalPosBenefitDeatil;
        }

        public MposBenefitDeatilBean getMposBenefitDeatil() {
            return mposBenefitDeatil;
        }

        public void setMposBenefitDeatil(MposBenefitDeatilBean mposBenefitDeatil) {
            this.mposBenefitDeatil = mposBenefitDeatil;
        }

        public static class MposBenefitDeatilBean {
            /**
             * activity_benefit : 0.00
             * return_benefit : 0.00
             * merchant_benefit : 0.00
             * agency_benefit : 0.00
             * deduct_money : 0.00
             * share_benefit : 0.00
             * benefit : 0.00
             */

            private String activity_benefit;
            private String return_benefit;
            private String merchant_benefit;
            private String agency_benefit;
            private String deduct_money;
            private String share_benefit;
            private String benefit;

            public String getActivity_benefit() {
                return activity_benefit;
            }

            public void setActivity_benefit(String activity_benefit) {
                this.activity_benefit = activity_benefit;
            }

            public String getReturn_benefit() {
                return return_benefit;
            }

            public void setReturn_benefit(String return_benefit) {
                this.return_benefit = return_benefit;
            }

            public String getMerchant_benefit() {
                return merchant_benefit;
            }

            public void setMerchant_benefit(String merchant_benefit) {
                this.merchant_benefit = merchant_benefit;
            }

            public String getAgency_benefit() {
                return agency_benefit;
            }

            public void setAgency_benefit(String agency_benefit) {
                this.agency_benefit = agency_benefit;
            }

            public String getDeduct_money() {
                return deduct_money;
            }

            public void setDeduct_money(String deduct_money) {
                this.deduct_money = deduct_money;
            }

            public String getShare_benefit() {
                return share_benefit;
            }

            public void setShare_benefit(String share_benefit) {
                this.share_benefit = share_benefit;
            }

            public String getBenefit() {
                return benefit;
            }

            public void setBenefit(String benefit) {
                this.benefit = benefit;
            }
        }
    }
}
