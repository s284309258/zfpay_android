package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetNewNoticeBean extends HttpResponse {

    /**
     * data : {"noticeInfoList":[{"notice_title":"新中付钱柜上线了！","is_read":0,"cre_date":"2019-08-21 00:00:00","notice_id":"2"},{"notice_title":"关于新中付钱柜，新加功能的解说公告","is_read":0,"cre_date":"2019-08-21 00:00:00","notice_id":"1"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<NoticeInfoListBean> noticeInfoList;

        public List<NoticeInfoListBean> getNoticeInfoList() {
            return noticeInfoList;
        }

        public void setNoticeInfoList(List<NoticeInfoListBean> noticeInfoList) {
            this.noticeInfoList = noticeInfoList;
        }

        public static class NoticeInfoListBean {
            /**
             * notice_title : 新中付钱柜上线了！
             * is_read : 0
             * cre_date : 2019-08-21 00:00:00
             * notice_id : 2
             */

            private String notice_title;
            private int is_read;
            private String cre_date;
            private String notice_id;

            public String getNotice_title() {
                return notice_title;
            }

            public void setNotice_title(String notice_title) {
                this.notice_title = notice_title;
            }

            public int getIs_read() {
                return is_read;
            }

            public void setIs_read(int is_read) {
                this.is_read = is_read;
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
