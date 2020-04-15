package com.lckj.jycm.zfqg_network;

import android.text.TextUtils;

import com.lckj.framework.network.HttpResponse;

public class GetNewsDetailBean extends HttpResponse {

    /**
     * data : {"newsInfo":{"news_content":"banners_3.jpg","cre_date":"2019-08-21 00:00:00","news_title":"POS机大揭秘","news_id":"3","news_nav":"banners_3.jpg"}}
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
         * newsInfo : {"news_content":"banners_3.jpg","cre_date":"2019-08-21 00:00:00","news_title":"POS机大揭秘","news_id":"3","news_nav":"banners_3.jpg"}
         */

        private NewsInfoBean newsInfo;

        public NewsInfoBean getNewsInfo() {
            return newsInfo;
        }

        public void setNewsInfo(NewsInfoBean newsInfo) {
            this.newsInfo = newsInfo;
        }

        public static class NewsInfoBean {
            /**
             * news_content : banners_3.jpg
             * cre_date : 2019-08-21 00:00:00
             * news_title : POS机大揭秘
             * news_id : 3
             * news_nav : banners_3.jpg
             */

            private String news_content;
            private String cre_date;
            private String news_title;
            private String news_id;
            private String news_nav;
            private String browse_num;

            public String getBrowse_num() {
                if (TextUtils.isEmpty(browse_num)) browse_num = "0";
                return browse_num;
            }

            public void setBrowse_num(String browse_num) {
                this.browse_num = browse_num;
            }

            public String getNews_content() {
                return news_content;
            }

            public void setNews_content(String news_content) {
                this.news_content = news_content;
            }

            public String getCre_date() {
                return cre_date;
            }

            public void setCre_date(String cre_date) {
                this.cre_date = cre_date;
            }

            public String getNews_title() {
                return news_title;
            }

            public void setNews_title(String news_title) {
                this.news_title = news_title;
            }

            public String getNews_id() {
                return news_id;
            }

            public void setNews_id(String news_id) {
                this.news_id = news_id;
            }

            public String getNews_nav() {
                return news_nav;
            }

            public void setNews_nav(String news_nav) {
                this.news_nav = news_nav;
            }
        }
    }
}
