package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetMachineBackTraditionalPosListBean extends HttpResponse {

    /**
     * data : {"machineBackTraditionalPosList":[{"record_id":"1","money":"200","frozen_time":"2019-08-20","cre_datetime":"2019-08-25 00:00:00","sn":"92932752","order_id":"2019082513456512343211300004"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<MachineBackTraditionalPosListBean> machineBackTraditionalPosList;
        private List<MachineBackTraditionalPosListBean> machineBackMposList;

        public List<MachineBackTraditionalPosListBean> getMachineBackMposList() {
            return machineBackMposList;
        }

        public void setMachineBackMposList(List<MachineBackTraditionalPosListBean> machineBackMposList) {
            this.machineBackMposList = machineBackMposList;
        }

        public List<MachineBackTraditionalPosListBean> getMachineBackTraditionalPosList() {
            return machineBackTraditionalPosList;
        }

        public void setMachineBackTraditionalPosList(List<MachineBackTraditionalPosListBean> machineBackTraditionalPosList) {
            this.machineBackTraditionalPosList = machineBackTraditionalPosList;
        }

        public static class MachineBackTraditionalPosListBean {
            /**
             * record_id : 1
             * money : 200
             * frozen_time : 2019-08-20
             * cre_datetime : 2019-08-25 00:00:00
             * sn : 92932752
             * order_id : 2019082513456512343211300004
             */

            private String record_id;
            private String money;
            private String frozen_time;
            private String cre_datetime;
            private String sn;
            private String order_id;

            public String getRecord_id() {
                return record_id;
            }

            public void setRecord_id(String record_id) {
                this.record_id = record_id;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getFrozen_time() {
                return frozen_time;
            }

            public void setFrozen_time(String frozen_time) {
                this.frozen_time = frozen_time;
            }

            public String getCre_datetime() {
                return cre_datetime;
            }

            public void setCre_datetime(String cre_datetime) {
                this.cre_datetime = cre_datetime;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }
        }
    }
}
