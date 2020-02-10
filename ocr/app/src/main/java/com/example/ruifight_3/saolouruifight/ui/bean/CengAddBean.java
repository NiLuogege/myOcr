package com.example.ruifight_3.saolouruifight.ui.bean;

import java.util.List;

/**
 * Created by RuiFight-3 on 2018/5/21.
 */

public class CengAddBean {

    /**
     * msg : 查询成功
     * homeownerList : []
     * state : 1
     */

    private String msg;
    private int state;
    private List<?> homeownerList;

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

    public List<?> getHomeownerList() {
        return homeownerList;
    }

    public void setHomeownerList(List<?> homeownerList) {
        this.homeownerList = homeownerList;
    }
}
