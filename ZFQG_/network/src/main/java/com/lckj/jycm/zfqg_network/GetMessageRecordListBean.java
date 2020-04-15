package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetMessageRecordListBean extends HttpResponse {

    /**
     * data : {"messageRecordList":[{"money":"20","cre_datetime":"2019-08-13 00:00:00","message_id":"1"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<MessageRecordListBean> messageRecordList;

        public List<MessageRecordListBean> getMessageRecordList() {
            return messageRecordList;
        }

        public void setMessageRecordList(List<MessageRecordListBean> messageRecordList) {
            this.messageRecordList = messageRecordList;
        }

        public static class MessageRecordListBean {
            /**
             * money : 20
             * cre_datetime : 2019-08-13 00:00:00
             * message_id : 1
             */

            private String money;
            private String cre_datetime;
            private String message_id;

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

            public String getMessage_id() {
                return message_id;
            }

            public void setMessage_id(String message_id) {
                this.message_id = message_id;
            }
        }
    }
}
