package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetNewNewsBean extends HttpResponse {

    /**
     * data : {"newsInfoList":[{"news_cover":"banners_3.jpg","browse_num":"0","cre_date":"2019-08-21 00:00:00","news_title":"POS机大揭秘","news_id":"3"},{"news_cover":"banners_2.jpg","browse_num":"0","cre_date":"2019-08-21 00:00:00","news_title":"颠覆传统POS机","news_id":"2"},{"news_cover":"banners_1.jpg","browse_num":"0","cre_date":"2019-08-21 00:00:00","news_title":"上海地铁站现诗歌POS机","news_id":"1"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<NewsInfoListBean> newsInfoList;

        public List<NewsInfoListBean> getNewsInfoList() {
            return newsInfoList;
        }

        public void setNewsInfoList(List<NewsInfoListBean> newsInfoList) {
            this.newsInfoList = newsInfoList;
        }

        public static class NewsInfoListBean {
            /**
             * news_cover : banners_3.jpg
             * browse_num : 0
             * cre_date : 2019-08-21 00:00:00
             * news_title : POS机大揭秘
             * news_id : 3
             */

            private String news_cover;
            private String browse_num;
            private String cre_date;
            private String news_title;
            private String news_id;

            public String getNews_cover() {
                return news_cover;
            }

            public void setNews_cover(String news_cover) {
                this.news_cover = news_cover;
            }

            public String getBrowse_num() {
                return browse_num;
            }

            public void setBrowse_num(String browse_num) {
                this.browse_num = browse_num;
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
        }
    }
}
