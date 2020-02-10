package com.example.ruifight_3.saolouruifight.db.util.bean;

import java.io.Serializable;

/**
 * Created by RuiFight-3 on 2018/7/13.
 */

public class view_fangJian implements Serializable{
    private static final long serialVersionUID = -320168074959562467L;
    private String huid;
    private String hubm;
    private int renshu_ldrk;//居住人数，用于显示
    private int renshu_rhfl;//人户分离人口
    private int renshu_hjrk;//户籍人口
    private String fz_fwjzlx;//房屋类型

    public String getHuid() {
        return huid;
    }
    public void setHuid(String huid) {
        this.huid = huid;
    }
    public String getHubm() {
        return hubm;
    }
    public void setHubm(String hubm) {
        this.hubm = hubm;
    }
    public int getRenshu_ldrk() {
        return renshu_ldrk;
    }
    public void setRenshu_ldrk(int renshu_ldrk) {
        this.renshu_ldrk = renshu_ldrk;
    }
    public int getRenshu_rhfl() {
        return renshu_rhfl;
    }
    public void setRenshu_rhfl(int renshu_rhfl) {
        this.renshu_rhfl = renshu_rhfl;
    }
    public int getRenshu_hjrk() {
        return renshu_hjrk;
    }
    public void setRenshu_hjrk(int renshu_hjrk) {
        this.renshu_hjrk = renshu_hjrk;
    }
    public String getFz_fwjzlx() {
        return fz_fwjzlx;
    }
    public void setFz_fwjzlx(String fz_fwjzlx) {
        this.fz_fwjzlx = fz_fwjzlx;
    }
}
