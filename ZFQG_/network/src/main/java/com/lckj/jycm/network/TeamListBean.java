package com.lckj.jycm.network;

import com.lckj.framework.network.HttpResponse;

import java.util.ArrayList;
import java.util.List;

public class TeamListBean extends HttpResponse {

    /**
     * data : {"count":3,"list":[{"userRegtime":"1554704781344","userName":"卓七","headPhoto":"http://p1.pstatp.com/large/pgc"},{"userRegtime":"1554704781344","userName":"赵六","headPhoto":"http://p1.pstatp.com/large/pgc"},{"userRegtime":"1554704781344","userName":"李四","headPhoto":"http://p1.pstatp.com/large/pgc"}]}
     * token : null
     * code : null
     */

    private DataBean data;

    public DataBean getData() {
        if (data == null) data = new DataBean();
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * count : 3
         * list : [{"userRegtime":"1554704781344","userName":"卓七","headPhoto":"http://p1.pstatp.com/large/pgc"},{"userRegtime":"1554704781344","userName":"赵六","headPhoto":"http://p1.pstatp.com/large/pgc"},{"userRegtime":"1554704781344","userName":"李四","headPhoto":"http://p1.pstatp.com/large/pgc"}]
         */

        private int count;
        private List<ListBean> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ListBean> getList() {
            if (list == null) list = new ArrayList<ListBean>();
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * userRegtime : 1554704781344
             * userName : 卓七
             * headPhoto : http://p1.pstatp.com/large/pgc
             */

            private String userRegtime;
            private String userName;
            private String headPhoto;

            public String getUserRegtime() {
                return userRegtime;
            }

            public void setUserRegtime(String userRegtime) {
                this.userRegtime = userRegtime;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getHeadPhoto() {
                return headPhoto;
            }

            public void setHeadPhoto(String headPhoto) {
                this.headPhoto = headPhoto;
            }
        }
    }
}
