package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class SelectPolicy3RecordBean extends HttpResponse {

    /**
     * data : {"machinesPolicy3Record":[{"end_date":"20200302","mer_id":"847555056510195","policy_id":"16","trade_amount":439109,"begin_date":"20190924","policy3List":[{"end_date":null,"isuse":"1","pos_type":"TraditionalPOS","policy_amount":15,"begin_date":null,"policy_name":"交易达标返现1W返15","cre_date":"2019-12-17T11:03:17.000+0000","remark":"","upd_by":null,"policy_type":3,"policy_end_day":90,"manager_id":26,"upd_date":null,"policy_begin_day":0,"policy_quantity":10000,"id":21},{"end_date":null,"isuse":"1","pos_type":"TraditionalPOS","policy_amount":150,"begin_date":null,"policy_name":"交易达标返现10W返150","cre_date":"2019-12-17T11:02:18.000+0000","remark":"","upd_by":null,"policy_type":3,"policy_end_day":90,"manager_id":26,"upd_date":null,"policy_begin_day":0,"policy_quantity":100000,"id":20}],"choose":16,"mer_name":"长沙倚桐服装店"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<MachinesPolicy3RecordBean> machinesPolicy3Record;

        public List<MachinesPolicy3RecordBean> getMachinesPolicy3Record() {
            return machinesPolicy3Record;
        }

        public void setMachinesPolicy3Record(List<MachinesPolicy3RecordBean> machinesPolicy3Record) {
            this.machinesPolicy3Record = machinesPolicy3Record;
        }

        public static class MachinesPolicy3RecordBean {
            /**
             * end_date : 20200302
             * mer_id : 847555056510195
             * policy_id : 16
             * trade_amount : 439109.0
             * begin_date : 20190924
             * policy3List : [{"end_date":null,"isuse":"1","pos_type":"TraditionalPOS","policy_amount":15,"begin_date":null,"policy_name":"交易达标返现1W返15","cre_date":"2019-12-17T11:03:17.000+0000","remark":"","upd_by":null,"policy_type":3,"policy_end_day":90,"manager_id":26,"upd_date":null,"policy_begin_day":0,"policy_quantity":10000,"id":21},{"end_date":null,"isuse":"1","pos_type":"TraditionalPOS","policy_amount":150,"begin_date":null,"policy_name":"交易达标返现10W返150","cre_date":"2019-12-17T11:02:18.000+0000","remark":"","upd_by":null,"policy_type":3,"policy_end_day":90,"manager_id":26,"upd_date":null,"policy_begin_day":0,"policy_quantity":100000,"id":20}]
             * choose : 16
             * mer_name : 长沙倚桐服装店
             */

            private String end_date;
            private String mer_id;
            private String policy_id;
            private double trade_amount;
            private String begin_date;
            private String choose;
            private String mer_name;
            private String expire_day;
            private List<Policy3ListBean> policy3List;

            public String getExpire_day() {
                return expire_day;
            }

            public void setExpire_day(String expire_day) {
                this.expire_day = expire_day;
            }

            public String getEnd_date() {
                return end_date;
            }

            public void setEnd_date(String end_date) {
                this.end_date = end_date;
            }

            public String getMer_id() {
                return mer_id;
            }

            public void setMer_id(String mer_id) {
                this.mer_id = mer_id;
            }

            public String getPolicy_id() {
                return policy_id;
            }

            public void setPolicy_id(String policy_id) {
                this.policy_id = policy_id;
            }

            public double getTrade_amount() {
                return trade_amount;
            }

            public void setTrade_amount(double trade_amount) {
                this.trade_amount = trade_amount;
            }

            public String getBegin_date() {
                return begin_date;
            }

            public void setBegin_date(String begin_date) {
                this.begin_date = begin_date;
            }

            public String getChoose() {
                return choose;
            }

            public void setChoose(String choose) {
                this.choose = choose;
            }

            public String getMer_name() {
                return mer_name;
            }

            public void setMer_name(String mer_name) {
                this.mer_name = mer_name;
            }

            public List<Policy3ListBean> getPolicy3List() {
                return policy3List;
            }

            public void setPolicy3List(List<Policy3ListBean> policy3List) {
                this.policy3List = policy3List;
            }

            public static class Policy3ListBean {
                /**
                 * end_date : null
                 * isuse : 1
                 * pos_type : TraditionalPOS
                 * policy_amount : 15
                 * begin_date : null
                 * policy_name : 交易达标返现1W返15
                 * cre_date : 2019-12-17T11:03:17.000+0000
                 * remark :
                 * upd_by : null
                 * policy_type : 3
                 * policy_end_day : 90
                 * manager_id : 26
                 * upd_date : null
                 * policy_begin_day : 0
                 * policy_quantity : 10000
                 * id : 21
                 */

                private String end_date;
                private String isuse;
                private String pos_type;
                private String policy_amount;
                private String begin_date;
                private String policy_name;
                private String cre_date;
                private String remark;
                private String upd_by;
                private String policy_type;
                private String policy_end_day;
                private String manager_id;
                private String upd_date;
                private String policy_begin_day;
                private int policy_quantity;
                private String id;

                public String getEnd_date() {
                    return end_date;
                }

                public void setEnd_date(String end_date) {
                    this.end_date = end_date;
                }

                public String getIsuse() {
                    return isuse;
                }

                public void setIsuse(String isuse) {
                    this.isuse = isuse;
                }

                public String getPos_type() {
                    return pos_type;
                }

                public void setPos_type(String pos_type) {
                    this.pos_type = pos_type;
                }

                public String getPolicy_amount() {
                    return policy_amount;
                }

                public void setPolicy_amount(String policy_amount) {
                    this.policy_amount = policy_amount;
                }

                public String getBegin_date() {
                    return begin_date;
                }

                public void setBegin_date(String begin_date) {
                    this.begin_date = begin_date;
                }

                public String getPolicy_name() {
                    return policy_name;
                }

                public void setPolicy_name(String policy_name) {
                    this.policy_name = policy_name;
                }

                public String getCre_date() {
                    return cre_date;
                }

                public void setCre_date(String cre_date) {
                    this.cre_date = cre_date;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getUpd_by() {
                    return upd_by;
                }

                public void setUpd_by(String upd_by) {
                    this.upd_by = upd_by;
                }

                public String getPolicy_type() {
                    return policy_type;
                }

                public void setPolicy_type(String policy_type) {
                    this.policy_type = policy_type;
                }

                public String getPolicy_end_day() {
                    return policy_end_day;
                }

                public void setPolicy_end_day(String policy_end_day) {
                    this.policy_end_day = policy_end_day;
                }

                public String getManager_id() {
                    return manager_id;
                }

                public void setManager_id(String manager_id) {
                    this.manager_id = manager_id;
                }

                public String getUpd_date() {
                    return upd_date;
                }

                public void setUpd_date(String upd_date) {
                    this.upd_date = upd_date;
                }

                public String getPolicy_begin_day() {
                    return policy_begin_day;
                }

                public void setPolicy_begin_day(String policy_begin_day) {
                    this.policy_begin_day = policy_begin_day;
                }

                public int getPolicy_quantity() {
                    return policy_quantity;
                }

                public void setPolicy_quantity(int policy_quantity) {
                    this.policy_quantity = policy_quantity;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }
            }
        }
    }
}
