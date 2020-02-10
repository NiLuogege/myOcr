package com.example.ruifight_3.saolouruifight.ui.bean;

import java.util.List;

/**
 * Created by RuiFight-3 on 2019/4/29.
 * Creator: 张震 (Android  zhangzhen)
 * Describe:
 */
public class PerSouSuoBean {


    /**
     * status : true
     * msg : null
     * data : [{"lxdh":"13333333333","xm":"朱振强","sfz":"110107199407110911","dizhi_xz":"椿树园18号楼 1 单元 11 层 1101号","xb":"男","id":152,"picture":"image/2018/11/13/1000001452056352.jpg"},{"lxdh":"15533113311","xm":"朱振强","sfz":"110107199407110911","dizhi_xz":"椿树园11号楼 1 单元 3 层 315号","xb":"男","id":156,"picture":"image/2018/11/16/1000001195435260.jpg"},{"lxdh":"15911000088","xm":"朱振强","sfz":"110107199407110911","dizhi_xz":"椿树园18号楼 5 单元 3 层 302号","xb":"男","id":157,"picture":"image/2018/11/16/1000002040392422.jpg"},{"lxdh":"13366333366","xm":"朱振强","sfz":"110107199407110911","dizhi_xz":"椿树园测试街道cscs 2 单元 1 层 102","xb":"男","id":158,"picture":"image/2018/11/16/1000001837019697.jpg"},{"lxdh":"13103696331","xm":"朱振强","sfz":"110107199407110911","dizhi_xz":"椿树园6号楼 1 单元 13 层 1301号","xb":"男","id":160,"picture":"image/2018/11/16/1542338301430.jpg"}]
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
         * lxdh : 13333333333
         * xm : 朱振强
         * sfz : 110107199407110911
         * dizhi_xz : 椿树园18号楼 1 单元 11 层 1101号
         * xb : 男
         * id : 152
         * picture : image/2018/11/13/1000001452056352.jpg
         */

        private String lxdh;
        private String xm;
        private String sfz;
        private String dizhi_xz;
        private String xb;
        private int id;
        private String picture;

        public String getLxdh() {
            return lxdh;
        }

        public void setLxdh(String lxdh) {
            this.lxdh = lxdh;
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

        public String getDizhi_xz() {
            return dizhi_xz;
        }

        public void setDizhi_xz(String dizhi_xz) {
            this.dizhi_xz = dizhi_xz;
        }

        public String getXb() {
            return xb;
        }

        public void setXb(String xb) {
            this.xb = xb;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}
