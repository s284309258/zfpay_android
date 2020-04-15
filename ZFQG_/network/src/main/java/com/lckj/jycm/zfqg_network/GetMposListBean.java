package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetMposListBean extends HttpResponse {

    /**
     * data : {"mposList":[{"mpos_id":"10","name":null,"tel":null,"sn":"95314109"},{"mpos_id":"9","name":null,"tel":null,"sn":"95314108"},{"mpos_id":"8","name":null,"tel":null,"sn":"95314107"},{"mpos_id":"7","name":null,"tel":null,"sn":"95314106"},{"mpos_id":"6","name":null,"tel":null,"sn":"95314105"},{"mpos_id":"5","name":null,"tel":null,"sn":"95314104"},{"mpos_id":"4","name":null,"tel":null,"sn":"95314103"},{"mpos_id":"3","name":null,"tel":null,"sn":"95314102"},{"mpos_id":"2","name":null,"tel":null,"sn":"95314101"},{"mpos_id":"1","name":null,"tel":null,"sn":"95314100"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<MposListBean> mposList;

        public List<MposListBean> getMposList() {
            return mposList;
        }

        public void setMposList(List<MposListBean> mposList) {
            this.mposList = mposList;
        }

        public static class MposListBean {
            /**
             * mpos_id : 10
             * name : null
             * tel : null
             * sn : 95314109
             */

            private String mpos_id;
            private String name;
            private String tel;
            private String sn;

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
        }
    }
}
