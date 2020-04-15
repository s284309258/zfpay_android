package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

public class GetMessageRecordDetailBean extends HttpResponse {

    /**
     * data : {"messageRecord":{"money":"20","cre_datetime":"2019-08-13 00:00:00","sn":"92617921","op_type":"01","order_id":"20190876543210"}}
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
         * messageRecord : {"money":"20","cre_datetime":"2019-08-13 00:00:00","sn":"92617921","op_type":"01","order_id":"20190876543210"}
         */

        private MessageRecordBean messageRecord;

        public MessageRecordBean getMessageRecord() {
            return messageRecord;
        }

        public void setMessageRecord(MessageRecordBean messageRecord) {
            this.messageRecord = messageRecord;
        }

        public static class MessageRecordBean {
            /**
             * money : 20
             * cre_datetime : 2019-08-13 00:00:00
             * sn : 92617921
             * op_type : 01
             * order_id : 20190876543210
             */

            private String money;
            private String cre_datetime;
            private String sn;
            private String op_type;
            private String order_id;

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
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

            public String getOp_type() {
                return op_type;
            }

            public void setOp_type(String op_type) {
                this.op_type = op_type;
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
