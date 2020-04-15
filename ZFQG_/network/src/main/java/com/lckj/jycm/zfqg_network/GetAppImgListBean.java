package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.ArrayList;
import java.util.List;

public class GetAppImgListBean extends HttpResponse {

    /**
     * data : {"appImgList":[{"img_type":"01","img_url":"72318c58-da46-4fc0-ae7d-43ab61880418.jpg","id":1},{"img_type":"01","img_url":"851868dc-7448-4bae-9e9a-dc65bbb5d22b.jpg","id":4},{"img_type":"01","img_url":"2e0c3898-387a-47b4-8864-b0b489e65efc.jpg","id":5}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<AppImgListBean> appImgList;

        public List<AppImgListBean> getAppImgList() {
            if(appImgList == null)appImgList = new ArrayList<>();
            return appImgList;
        }

        public void setAppImgList(List<AppImgListBean> appImgList) {
            this.appImgList = appImgList;
        }

        public static class AppImgListBean {
            /**
             * img_type : 01
             * img_url : 72318c58-da46-4fc0-ae7d-43ab61880418.jpg
             * id : 1
             */

            private String img_type;
            private String img_url;
            private int id;

            public String getImg_type() {
                return img_type;
            }

            public void setImg_type(String img_type) {
                this.img_type = img_type;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
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
