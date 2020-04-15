package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetAllMerchantTraditionalPosListBean extends HttpResponse {

    /**
     * data : {"allMerchantTraditionalPosList":[{"name":null,"tel":null,"sn":"95314618","trapos_id":"10"},{"name":null,"tel":null,"sn":"92617921","trapos_id":"9"},{"name":null,"tel":null,"sn":"92932752","trapos_id":"8"},{"name":null,"tel":null,"sn":"95217293","trapos_id":"7"},{"name":null,"tel":null,"sn":"92308252","trapos_id":"6"},{"name":null,"tel":null,"sn":"94585127","trapos_id":"2"},{"name":null,"tel":null,"sn":"95370909","trapos_id":"1"}]}
     */

    public DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        public List<AllMerchantTraditionalPosListBean> allMerchantTraditionalPosList;
        public List<AllMerchantTraditionalPosListBean> excellentMerchantTraditionalPosList;
        public List<AllMerchantTraditionalPosListBean> activeMerchantTraditionalPosList;
        public List<AllMerchantTraditionalPosListBean> dormantMerchantTraditionalPosList;
        public List<AllMerchantTraditionalPosListBean> allMerchantMposList;
        public List<AllMerchantTraditionalPosListBean> excellentMerchantMposList;
        public List<AllMerchantTraditionalPosListBean> activeMerchantMposList;
        public List<AllMerchantTraditionalPosListBean> dormantMerchantMposList;

        public List<AllMerchantTraditionalPosListBean> getExcellentMerchantTraditionalPosList() {
            return excellentMerchantTraditionalPosList;
        }

        public void setExcellentMerchantTraditionalPosList(List<AllMerchantTraditionalPosListBean> excellentMerchantTraditionalPosList) {
            this.excellentMerchantTraditionalPosList = excellentMerchantTraditionalPosList;
        }

        public List<AllMerchantTraditionalPosListBean> getActiveMerchantTraditionalPosList() {
            return activeMerchantTraditionalPosList;
        }

        public void setActiveMerchantTraditionalPosList(List<AllMerchantTraditionalPosListBean> activeMerchantTraditionalPosList) {
            this.activeMerchantTraditionalPosList = activeMerchantTraditionalPosList;
        }

        public List<AllMerchantTraditionalPosListBean> getDormantMerchantTraditionalPosList() {
            return dormantMerchantTraditionalPosList;
        }

        public void setDormantMerchantTraditionalPosList(List<AllMerchantTraditionalPosListBean> dormantMerchantTraditionalPosList) {
            this.dormantMerchantTraditionalPosList = dormantMerchantTraditionalPosList;
        }

        public List<AllMerchantTraditionalPosListBean> getAllMerchantMposList() {
            return allMerchantMposList;
        }

        public void setAllMerchantMposList(List<AllMerchantTraditionalPosListBean> allMerchantMposList) {
            this.allMerchantMposList = allMerchantMposList;
        }

        public List<AllMerchantTraditionalPosListBean> getExcellentMerchantMposList() {
            return excellentMerchantMposList;
        }

        public void setExcellentMerchantMposList(List<AllMerchantTraditionalPosListBean> excellentMerchantMposList) {
            this.excellentMerchantMposList = excellentMerchantMposList;
        }

        public List<AllMerchantTraditionalPosListBean> getActiveMerchantMposList() {
            return activeMerchantMposList;
        }

        public void setActiveMerchantMposList(List<AllMerchantTraditionalPosListBean> activeMerchantMposList) {
            this.activeMerchantMposList = activeMerchantMposList;
        }

        public List<AllMerchantTraditionalPosListBean> getDormantMerchantMposList() {
            return dormantMerchantMposList;
        }

        public void setDormantMerchantMposList(List<AllMerchantTraditionalPosListBean> dormantMerchantMposList) {
            this.dormantMerchantMposList = dormantMerchantMposList;
        }

        public List<AllMerchantTraditionalPosListBean> getAllMerchantTraditionalPosList() {
            return allMerchantTraditionalPosList;
        }

        public void setAllMerchantTraditionalPosList(List<AllMerchantTraditionalPosListBean> allMerchantTraditionalPosList) {
            this.allMerchantTraditionalPosList = allMerchantTraditionalPosList;
        }

        public static class AllMerchantTraditionalPosListBean {
            /**
             * name : null
             * tel : null
             * sn : 95314618
             * trapos_id : 10
             */

            public String name;
            public String mer_name;
            public String tel;
            public String mer_id;
            public String sn;
            public String trapos_id;
            public String mpos_id;

            public String getMer_name() {
                return mer_name;
            }

            public void setMer_name(String mer_name) {
                this.mer_name = mer_name;
            }

            public String getMer_id() {
                return mer_id;
            }

            public void setMer_id(String mer_id) {
                this.mer_id = mer_id;
            }

            public String getMpos_id() {
                return mpos_id;
            }

            public void setMpos_id(String mpos_id) {
                this.mpos_id = mpos_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getTrapos_id() {
                return trapos_id;
            }

            public void setTrapos_id(String trapos_id) {
                this.trapos_id = trapos_id;
            }
        }
    }
}
