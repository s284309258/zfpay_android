package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

public class GetNoticeDetailBean extends HttpResponse {

    /**
     * data : {"noticeInfo":{"notice_title":"中付钱柜上线啦","is_read":"0","notice_content":"49de8050-6757-4533-bd97-cfaa571b4762.png,e9416df4-cee1-487e-b1ec-33c948230b44.png,11a4ceb2-c673-4e33-ab84-2533aa642b36.png","cre_date":"2019-09-05 16:01:16","notice_id":"4"}}
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
         * noticeInfo : {"notice_title":"中付钱柜上线啦","is_read":"0","notice_content":"49de8050-6757-4533-bd97-cfaa571b4762.png,e9416df4-cee1-487e-b1ec-33c948230b44.png,11a4ceb2-c673-4e33-ab84-2533aa642b36.png","cre_date":"2019-09-05 16:01:16","notice_id":"4"}
         */

        private NoticeInfoBean noticeInfo;

        public NoticeInfoBean getNoticeInfo() {
            return noticeInfo;
        }

        public void setNoticeInfo(NoticeInfoBean noticeInfo) {
            this.noticeInfo = noticeInfo;
        }

        public static class NoticeInfoBean {
            /**
             * notice_title : 中付钱柜上线啦
             * is_read : 0
             * notice_content : 49de8050-6757-4533-bd97-cfaa571b4762.png,e9416df4-cee1-487e-b1ec-33c948230b44.png,11a4ceb2-c673-4e33-ab84-2533aa642b36.png
             * cre_date : 2019-09-05 16:01:16
             * notice_id : 4
             */

            private String notice_title;
            private String is_read;
            private String notice_content;
            private String cre_date;
            private String notice_id;

            public String getNotice_title() {
                return notice_title;
            }

            public void setNotice_title(String notice_title) {
                this.notice_title = notice_title;
            }

            public String getIs_read() {
                return is_read;
            }

            public void setIs_read(String is_read) {
                this.is_read = is_read;
            }

            public String getNotice_content() {
                return notice_content;
            }

            public void setNotice_content(String notice_content) {
                this.notice_content = notice_content;
            }

            public String getCre_date() {
                return cre_date;
            }

            public void setCre_date(String cre_date) {
                this.cre_date = cre_date;
            }

            public String getNotice_id() {
                return notice_id;
            }

            public void setNotice_id(String notice_id) {
                this.notice_id = notice_id;
            }
        }
    }
}
