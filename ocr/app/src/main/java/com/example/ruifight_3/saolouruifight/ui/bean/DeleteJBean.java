package com.example.ruifight_3.saolouruifight.ui.bean;

import java.util.List;

/**
 * Created by RuiFight-3 on 2019/7/26.
 * Creator: 张震 (Android  zhangzhen)
 * Describe:
 */
public class DeleteJBean {


    /**
     * status : true
     * msg : null
     * data : [{"id":1,"xm":"朱振强","sfz":"110107111111111111","xb":"男","minzu":"汉族","hj_hjxz":"北京市昌平区","lxdh":"13333333333","jz_fwid":"7a13744d21cb449787b2cf6f623013cb-1-1-1","jz_jzwbm":"7a13744d21cb449787b2cf6f623013cb","dizhi_jzwdz":"椿树园测试街道cs40","dizhi_xz":"椿树园测试街道cs401单元1层101","cjsj":1564022816000,"whry":"chunshuyuan","scsj":1564022848000,"jwhdm":"110100000000,110102000000,110102015000,1101040403007,"},{"id":2,"xm":"任晗楠","sfz":"130626199609177436","xb":"男","minzu":"汉族","hj_hjxz":"河北省保定市定兴县杨村乡杨村二十三区13号","lxdh":"17600325866","jz_fwid":"7a13744d21cb449787b2cf6f623013cb-1-1-1","jz_jzwbm":"7a13744d21cb449787b2cf6f623013cb","dizhi_jzwdz":null,"dizhi_xz":"椿树园测试街道cs40 1 单元 1 层 101","cjsj":1561957124000,"whry":"chunshuyuan","scsj":1564026627000,"jwhdm":"110100000000,110102000000,110102015000,1101040403007,"},{"id":3,"xm":"嗯","sfz":"211382199411295816","xb":"男","minzu":"汉族","hj_hjxz":"图","lxdh":"17600325869","jz_fwid":"c7f68b473e7f480aa9b9a84b779689f1-1-1-1","jz_jzwbm":"c7f68b473e7f480aa9b9a84b779689f1","dizhi_jzwdz":null,"dizhi_xz":"椿树园测试街道cs测试的建筑物 1 单元 1 层 101","cjsj":1562218951000,"whry":"chunshuyuan","scsj":1564027906000,"jwhdm":"110100000000,110102000000,110102015000,1101040403007,"}]
     */

    private boolean status;
    private Object msg;
    private List<DataBean> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * xm : 朱振强
         * sfz : 110107111111111111
         * xb : 男
         * minzu : 汉族
         * hj_hjxz : 北京市昌平区
         * lxdh : 13333333333
         * jz_fwid : 7a13744d21cb449787b2cf6f623013cb-1-1-1
         * jz_jzwbm : 7a13744d21cb449787b2cf6f623013cb
         * dizhi_jzwdz : 椿树园测试街道cs40
         * dizhi_xz : 椿树园测试街道cs401单元1层101
         * cjsj : 1564022816000
         * whry : chunshuyuan
         * scsj : 1564022848000
         * jwhdm : 110100000000,110102000000,110102015000,1101040403007,
         */

        private int id;
        private String xm;
        private String sfz;
        private String xb;
        private String minzu;
        private String hj_hjxz;
        private String lxdh;
        private String jz_fwid;
        private String jz_jzwbm;
        private String dizhi_jzwdz;
        private String dizhi_xz;
        private long cjsj;
        private String whry;
        private long scsj;
        private String jwhdm;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getXm() {
            return xm;
        }

        public void setXm(String xm) {
            this.xm = xm;
        }

        public String getSfz() {
            return sfz;
        }

        public void setSfz(String sfz) {
            this.sfz = sfz;
        }

        public String getXb() {
            return xb;
        }

        public void setXb(String xb) {
            this.xb = xb;
        }

        public String getMinzu() {
            return minzu;
        }

        public void setMinzu(String minzu) {
            this.minzu = minzu;
        }

        public String getHj_hjxz() {
            return hj_hjxz;
        }

        public void setHj_hjxz(String hj_hjxz) {
            this.hj_hjxz = hj_hjxz;
        }

        public String getLxdh() {
            return lxdh;
        }

        public void setLxdh(String lxdh) {
            this.lxdh = lxdh;
        }

        public String getJz_fwid() {
            return jz_fwid;
        }

        public void setJz_fwid(String jz_fwid) {
            this.jz_fwid = jz_fwid;
        }

        public String getJz_jzwbm() {
            return jz_jzwbm;
        }

        public void setJz_jzwbm(String jz_jzwbm) {
            this.jz_jzwbm = jz_jzwbm;
        }

        public String getDizhi_jzwdz() {
            return dizhi_jzwdz;
        }

        public void setDizhi_jzwdz(String dizhi_jzwdz) {
            this.dizhi_jzwdz = dizhi_jzwdz;
        }

        public String getDizhi_xz() {
            return dizhi_xz;
        }

        public void setDizhi_xz(String dizhi_xz) {
            this.dizhi_xz = dizhi_xz;
        }

        public long getCjsj() {
            return cjsj;
        }

        public void setCjsj(long cjsj) {
            this.cjsj = cjsj;
        }

        public String getWhry() {
            return whry;
        }

        public void setWhry(String whry) {
            this.whry = whry;
        }

        public long getScsj() {
            return scsj;
        }

        public void setScsj(long scsj) {
            this.scsj = scsj;
        }

        public String getJwhdm() {
            return jwhdm;
        }

        public void setJwhdm(String jwhdm) {
            this.jwhdm = jwhdm;
        }
    }
}
