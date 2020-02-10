package com.example.ruifight_3.saolouruifight.ui.bean;

import java.util.List;

/**
 * Created by RuiFight-3 on 2018/5/21.
 */

public class CengBean {

    /**
     * msg : 查询成功
     * homeownerList : [{"hwName":"hah","hwIDCard":"211382199411295814","hwId":463,"hwPhone":"17600346866"},{"hwName":"hhh","hwIDCard":"211382199511295816","hwId":475,"hwPhone":"17636664366"}]
     * state : 1
     */

    private String msg;
    private int state;
    private List<HomeownerListBean> homeownerList;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<HomeownerListBean> getHomeownerList() {
        return homeownerList;
    }

    public void setHomeownerList(List<HomeownerListBean> homeownerList) {
        this.homeownerList = homeownerList;
    }

    public static class HomeownerListBean {
        /**
         * hwName : hah
         * hwIDCard : 211382199411295814
         * hwId : 463
         * hwPhone : 17600346866
         */

        private String hwName;
        private String hwIDCard;
        private int hwId;
        private String hwPhone;

        public String getHwName() {
            return hwName;
        }

        public void setHwName(String hwName) {
            this.hwName = hwName;
        }

        public String getHwIDCard() {
            return hwIDCard;
        }

        public void setHwIDCard(String hwIDCard) {
            this.hwIDCard = hwIDCard;
        }

        public int getHwId() {
            return hwId;
        }

        public void setHwId(int hwId) {
            this.hwId = hwId;
        }

        public String getHwPhone() {
            return hwPhone;
        }

        public void setHwPhone(String hwPhone) {
            this.hwPhone = hwPhone;
        }
    }
}
