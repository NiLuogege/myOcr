package com.example.ruifight_3.saolouruifight.db.util.bean;

import java.util.Date;

/**
 * Created by RuiFight-3 on 2018/7/13.
 */

public class t_jzw_fangjian {

    private Long id;
    private String jzw_dm;
    private String hu_id;
    private String jzw_dizhi;//是否需要分开存储??
    private Integer dys;
    private Integer cs;
    private Integer hs;
    private String dymc;
    private String humc;
    private String fjlx;
    private String ssdwdm;
    private String ssdwmc;
    private String whrybm;
    private Date whsj;
    private String is_del;
    private Integer renshu_ldrk;//居住人数，用于显示
    private Integer renshu_rhfl;//人户分离人口
    private Integer renshu_hjrk;//户籍人口

    private String fz_xm;
    private String fz_lxdh;
    private String fz_sfz;
    private String fz_fwjzlx;
    private String fz_is_del;//是 or 否
    private String fz_whry;
    private Date fz_whsh;
    private String fz_bz;


    public String getJzw_dm() {
        return jzw_dm;
    }

    public void setJzw_dm(String jzw_dm) {
        this.jzw_dm = jzw_dm;
    }
    public Integer getDys() {
        return dys;
    }

    public void setDys(Integer dys) {
        this.dys = dys;
    }

    public Integer getCs() {
        return cs;
    }

    public void setCs(Integer cs) {
        this.cs = cs;
    }

    public Integer getHs() {
        return hs;
    }

    public void setHs(Integer hs) {
        this.hs = hs;
    }

    public String getDymc() {
        return dymc;
    }

    public void setDymc(String dymc) {
        this.dymc = dymc;
    }

    public String getHumc() {
        return humc;
    }

    public void setHumc(String humc) {
        this.humc = humc;
    }

    public String getFjlx() {
        return fjlx;
    }

    public void setFjlx(String fjlx) {
        this.fjlx = fjlx;
    }

    public String getSsdwdm() {
        return ssdwdm;
    }

    public void setSsdwdm(String ssdwdm) {
        this.ssdwdm = ssdwdm;
    }

    public String getSsdwmc() {
        return ssdwmc;
    }

    public void setSsdwmc(String ssdwmc) {
        this.ssdwmc = ssdwmc;
    }

    public String getWhrybm() {
        return whrybm;
    }

    public void setWhrybm(String whrybm) {
        this.whrybm = whrybm;
    }

    public Date getWhsj() {
        return whsj;
    }

    public void setWhsj(Date whsj) {
        this.whsj = whsj;
    }

    public String getIs_del() {
        return is_del;
    }

    public void setIs_del(String is_del) {
        this.is_del = is_del;
    }

    public String getHu_id() {
        return hu_id;
    }

    public void setHu_id(String hu_id) {
        this.hu_id = hu_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRenshu_ldrk() {
        return renshu_ldrk;
    }

    public void setRenshu_ldrk(Integer renshu_ldrk) {
        this.renshu_ldrk = renshu_ldrk;
    }

    public Integer getRenshu_rhfl() {
        return renshu_rhfl;
    }

    public void setRenshu_rhfl(Integer renshu_rhfl) {
        this.renshu_rhfl = renshu_rhfl;
    }

    public Integer getRenshu_hjrk() {
        return renshu_hjrk;
    }

    public void setRenshu_hjrk(Integer renshu_hjrk) {
        this.renshu_hjrk = renshu_hjrk;
    }

    public String getJzw_dizhi() {
        return jzw_dizhi;
    }

    public void setJzw_dizhi(String jzw_dizhi) {
        this.jzw_dizhi = jzw_dizhi;
    }

    public String getFz_xm() {
        return fz_xm;
    }

    public void setFz_xm(String fz_xm) {
        this.fz_xm = fz_xm;
    }

    public String getFz_lxdh() {
        return fz_lxdh;
    }

    public void setFz_lxdh(String fz_lxdh) {
        this.fz_lxdh = fz_lxdh;
    }

    public String getFz_sfz() {
        return fz_sfz;
    }

    public void setFz_sfz(String fz_sfz) {
        this.fz_sfz = fz_sfz;
    }

    public String getFz_fwjzlx() {
        return fz_fwjzlx;
    }

    public void setFz_fwjzlx(String fz_fwjzlx) {
        this.fz_fwjzlx = fz_fwjzlx;
    }

    public String getFz_is_del() {
        return fz_is_del;
    }

    public void setFz_is_del(String fz_is_del) {
        this.fz_is_del = fz_is_del;
    }

    public String getFz_whry() {
        return fz_whry;
    }

    public void setFz_whry(String fz_whry) {
        this.fz_whry = fz_whry;
    }

    public Date getFz_whsh() {
        return fz_whsh;
    }

    public void setFz_whsh(Date fz_whsh) {
        this.fz_whsh = fz_whsh;
    }

    public String getFz_bz() {
        return fz_bz;
    }

    public void setFz_bz(String fz_bz) {
        this.fz_bz = fz_bz;
    }
}
