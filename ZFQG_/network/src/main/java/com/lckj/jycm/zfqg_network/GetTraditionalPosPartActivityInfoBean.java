package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetTraditionalPosPartActivityInfoBean extends HttpResponse {

    /**
     * data : {"traditionalPosList":[{"sn":"92280318"},{"sn":"91581156"},{"sn":"95476182"},{"sn":"92308252"},{"sn":"95217293"},{"sn":"92932752"},{"sn":"92617921"},{"sn":"95314618"}],"traditionalPosActivityRewardList":[{"expenditure":"2","activity_reward_id":"1","reward_money":"20","pos_num":"2"},{"expenditure":"2","activity_reward_id":"2","reward_money":"20","pos_num":"3"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<TraditionalPosListBean> traditionalPosList;
        private List<TraditionalPosActivityRewardListBean> traditionalPosActivityRewardList;
        private List<TraditionalPosActivityRewardListBean> mposActivityRewardList;
        private List<TraditionalPosListBean> mposList;

        public List<TraditionalPosActivityRewardListBean> getMposActivityRewardList() {
            return mposActivityRewardList;
        }

        public void setMposActivityRewardList(List<TraditionalPosActivityRewardListBean> mposActivityRewardList) {
            this.mposActivityRewardList = mposActivityRewardList;
        }

        public List<TraditionalPosListBean> getMposList() {
            return mposList;
        }

        public void setMposList(List<TraditionalPosListBean> mposList) {
            this.mposList = mposList;
        }

        public List<TraditionalPosListBean> getTraditionalPosList() {
            return traditionalPosList;
        }

        public void setTraditionalPosList(List<TraditionalPosListBean> traditionalPosList) {
            this.traditionalPosList = traditionalPosList;
        }

        public List<TraditionalPosActivityRewardListBean> getTraditionalPosActivityRewardList() {
            return traditionalPosActivityRewardList;
        }

        public void setTraditionalPosActivityRewardList(List<TraditionalPosActivityRewardListBean> traditionalPosActivityRewardList) {
            this.traditionalPosActivityRewardList = traditionalPosActivityRewardList;
        }

        public static class TraditionalPosListBean {
            /**
             * sn : 92280318
             */

            private String sn;

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }
        }

        public static class TraditionalPosActivityRewardListBean {
            /**
             * expenditure : 2
             * activity_reward_id : 1
             * reward_money : 20
             * pos_num : 2
             */

            private String expenditure;
            private String activity_reward_id;
            private String reward_money;
            private String pos_num;

            public String getExpenditure() {
                return expenditure;
            }

            public void setExpenditure(String expenditure) {
                this.expenditure = expenditure;
            }

            public String getActivity_reward_id() {
                return activity_reward_id;
            }

            public void setActivity_reward_id(String activity_reward_id) {
                this.activity_reward_id = activity_reward_id;
            }

            public String getReward_money() {
                return reward_money;
            }

            public void setReward_money(String reward_money) {
                this.reward_money = reward_money;
            }

            public String getPos_num() {
                return pos_num;
            }

            public void setPos_num(String pos_num) {
                this.pos_num = pos_num;
            }
        }
    }
}
