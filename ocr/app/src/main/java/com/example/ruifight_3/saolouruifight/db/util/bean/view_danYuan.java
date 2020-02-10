package com.example.ruifight_3.saolouruifight.db.util.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by RuiFight-3 on 2018/7/13.
 */

public class view_danYuan  implements Serializable {
    private static final long serialVersionUID = 5936244218032110125L;
    private int dys;//单元编号
    private String bieMing;//单元别名
    private List<view_ceng> cengList;



    public List<view_ceng> getCengList() {
        return cengList;
    }

    public void setCengList(List<view_ceng> cengList) {
        this.cengList = cengList;
    }
    public String getBieMing() {
        return bieMing;
    }

    public void setBieMing(String bieMing) {
        this.bieMing = bieMing;
    }

    public int getDys() {
        return dys;
    }

    public void setDys(int dys) {
        this.dys = dys;
    }
}
