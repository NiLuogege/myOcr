package com.example.ruifight_3.saolouruifight.db.util.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by RuiFight-3 on 2018/7/13.
 */

public class view_ceng implements Serializable{

    private static final long serialVersionUID = 7382253276094442343L;
    private int cengShu;
    private List<view_fangJian> fjList;
    public int getCengShu() {
        return cengShu;
    }
    public void setCengShu(int cengShu) {
        this.cengShu = cengShu;
    }
    public List<view_fangJian> getFjList() {
        return fjList;
    }
    public void setFjList(List<view_fangJian> fjList) {
        this.fjList = fjList;
    }
}
