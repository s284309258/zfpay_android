package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class SearchLikeBankBean extends HttpResponse {

    /**
     * data : {"sysBankList":[{"bank_code":"103100021172","bank_name":"中国农业银行股份有限公司北京金融大街丰盛支行","id":295839},{"bank_code":"103100040103","bank_name":"中国农业银行股份有限公司北京东三环支行","id":295840},{"bank_code":"103424004438","bank_name":"中国农业银行九江浔西支行","id":295841},{"bank_code":"103617020675","bank_name":"中国农业银行股份有限公司桂林分分钟支行","id":296101},{"bank_code":"103611002363","bank_name":"中国农业银行广西南宁航洋国际支行","id":296164},{"bank_code":"103611001318","bank_name":"中国农业银行南宁兴鹏支行","id":296165},{"bank_code":"103593365226","bank_name":"中国农业银行股份有限公司广宁南街支行","id":296191},{"bank_code":"203582000090","bank_name":"中国农业发展银行韶关市曲江支行","id":296203},{"bank_code":"103593565645","bank_name":"中国农业银行股份有限公司封开南丰支行","id":296205},{"bank_code":"103593265025","bank_name":"中国农业银行股份有限公司四会大沙支行","id":296206}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<SysBankListBean> sysBankList;

        public List<SysBankListBean> getSysBankList() {
            return sysBankList;
        }

        public void setSysBankList(List<SysBankListBean> sysBankList) {
            this.sysBankList = sysBankList;
        }

        public static class SysBankListBean {
            /**
             * bank_code : 103100021172
             * bank_name : 中国农业银行股份有限公司北京金融大街丰盛支行
             * id : 295839
             */

            private String bank_code;
            private String bank_name;
            private int id;

            public String getBank_code() {
                return bank_code;
            }

            public void setBank_code(String bank_code) {
                this.bank_code = bank_code;
            }

            public String getBank_name() {
                return bank_name;
            }

            public void setBank_name(String bank_name) {
                this.bank_name = bank_name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
