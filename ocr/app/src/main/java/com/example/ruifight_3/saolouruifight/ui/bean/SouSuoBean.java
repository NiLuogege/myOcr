package com.example.ruifight_3.saolouruifight.ui.bean;

import java.util.List;

/**
 * Created by RuiFight-3 on 2018/5/18.
 */

public class SouSuoBean {

    /**
     * msg : 成功显示管辖范围
     * floorList : [{"addressName":"海淀区苏家坨镇西埠头村北街49号","floorCode":"4CBAA1E6E9F6E4B3E0536902080AA554"}]
     * state : 1
     */

    private String msg;
    private int state;
    private List<FloorListBean> floorList;

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

    public List<FloorListBean> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<FloorListBean> floorList) {
        this.floorList = floorList;
    }

    public static class FloorListBean {
        /**
         * addressName : 海淀区苏家坨镇西埠头村北街49号
         * floorCode : 4CBAA1E6E9F6E4B3E0536902080AA554
         */

        private String addressName;
        private String floorCode;

        public String getAddressName() {
            return addressName;
        }

        public void setAddressName(String addressName) {
            this.addressName = addressName;
        }

        public String getFloorCode() {
            return floorCode;
        }

        public void setFloorCode(String floorCode) {
            this.floorCode = floorCode;
        }
    }
}
