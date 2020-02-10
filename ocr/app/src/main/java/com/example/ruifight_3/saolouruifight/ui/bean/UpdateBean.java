package com.example.ruifight_3.saolouruifight.ui.bean;

/**
 * Created by RuiFight-3 on 2018/5/16.
 */

public class UpdateBean {


    /**
     * status : true
     * msg : null
     * data : {"fj":{"id":5219,"jzw_dm":"4CBAA1EE9B90E4B3E0536902080AA554","hu_id":"4CBAA1EE9B90E4B3E0536902080AA554-1-1-1","jzw_dizhi":"苏家坨镇后沙涧第一小区19号","dys":1,"cs":1,"hs":1,"dymc":null,"humc":"1号","fjlx":"P","ssdwdm":null,"ssdwmc":null,"whrybm":null,"whsj":1526259330000,"is_del":"F","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_xm":"得得得","fz_lxdh":"13264054530","fz_sfz":"410181199222221516","fz_fwjzlx":"自住房屋","fz_is_del":"否","fz_whry":"liuguanyuan","fz_whsh":1531127633000,"fz_bz":"asd "}}
     */

    private boolean status;
    private Object msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * fj : {"id":5219,"jzw_dm":"4CBAA1EE9B90E4B3E0536902080AA554","hu_id":"4CBAA1EE9B90E4B3E0536902080AA554-1-1-1","jzw_dizhi":"苏家坨镇后沙涧第一小区19号","dys":1,"cs":1,"hs":1,"dymc":null,"humc":"1号","fjlx":"P","ssdwdm":null,"ssdwmc":null,"whrybm":null,"whsj":1526259330000,"is_del":"F","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_xm":"得得得","fz_lxdh":"13264054530","fz_sfz":"410181199222221516","fz_fwjzlx":"自住房屋","fz_is_del":"否","fz_whry":"liuguanyuan","fz_whsh":1531127633000,"fz_bz":"asd "}
         */

        private FjBean fj;

        public FjBean getFj() {
            return fj;
        }

        public void setFj(FjBean fj) {
            this.fj = fj;
        }

        public static class FjBean {
            /**
             * id : 5219
             * jzw_dm : 4CBAA1EE9B90E4B3E0536902080AA554
             * hu_id : 4CBAA1EE9B90E4B3E0536902080AA554-1-1-1
             * jzw_dizhi : 苏家坨镇后沙涧第一小区19号
             * dys : 1
             * cs : 1
             * hs : 1
             * dymc : null
             * humc : 1号
             * fjlx : P
             * ssdwdm : null
             * ssdwmc : null
             * whrybm : null
             * whsj : 1526259330000
             * is_del : F
             * renshu_ldrk : 0
             * renshu_rhfl : 0
             * renshu_hjrk : 0
             * fz_xm : 得得得
             * fz_lxdh : 13264054530
             * fz_sfz : 410181199222221516
             * fz_fwjzlx : 自住房屋
             * fz_is_del : 否
             * fz_whry : liuguanyuan
             * fz_whsh : 1531127633000
             * fz_bz : asd
             * is_login:
             */

            private int id;
            private String jzw_dm;
            private String hu_id;
            private String jzw_dizhi;
            private int dys;
            private int cs;
            private int hs;
            private Object dymc;
            private String humc;
            private String fjlx;
            private Object ssdwdm;
            private Object ssdwmc;
            private Object whrybm;
            private long whsj;
            private String is_del;
            private int renshu_ldrk;
            private int renshu_rhfl;
            private int renshu_hjrk;
            private String fz_xm;
            private String fz_lxdh;
            private String fz_sfz;
            private String fz_fwjzlx;
            private String fz_is_del;
            private String fz_whry;
            private long fz_whsh;
            private String fz_bz;
            private String fz_hjdz;
            private String is_login;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getJzw_dm() {
                return jzw_dm;
            }

            public void setJzw_dm(String jzw_dm) {
                this.jzw_dm = jzw_dm;
            }

            public String getHu_id() {
                return hu_id;
            }

            public void setHu_id(String hu_id) {
                this.hu_id = hu_id;
            }

            public String getJzw_dizhi() {
                return jzw_dizhi;
            }

            public void setJzw_dizhi(String jzw_dizhi) {
                this.jzw_dizhi = jzw_dizhi;
            }

            public int getDys() {
                return dys;
            }

            public void setDys(int dys) {
                this.dys = dys;
            }

            public int getCs() {
                return cs;
            }

            public void setCs(int cs) {
                this.cs = cs;
            }

            public int getHs() {
                return hs;
            }

            public void setHs(int hs) {
                this.hs = hs;
            }

            public Object getDymc() {
                return dymc;
            }

            public void setDymc(Object dymc) {
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

            public Object getSsdwdm() {
                return ssdwdm;
            }

            public void setSsdwdm(Object ssdwdm) {
                this.ssdwdm = ssdwdm;
            }

            public Object getSsdwmc() {
                return ssdwmc;
            }

            public void setSsdwmc(Object ssdwmc) {
                this.ssdwmc = ssdwmc;
            }

            public Object getWhrybm() {
                return whrybm;
            }

            public void setWhrybm(Object whrybm) {
                this.whrybm = whrybm;
            }

            public long getWhsj() {
                return whsj;
            }

            public void setWhsj(long whsj) {
                this.whsj = whsj;
            }

            public String getIs_del() {
                return is_del;
            }

            public void setIs_del(String is_del) {
                this.is_del = is_del;
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

            public long getFz_whsh() {
                return fz_whsh;
            }

            public void setFz_whsh(long fz_whsh) {
                this.fz_whsh = fz_whsh;
            }

            public String getFz_bz() {
                return fz_bz;
            }

            public void setFz_bz(String fz_bz) {
                this.fz_bz = fz_bz;
            }

            public String getFz_hjdz() {
                return fz_hjdz;
            }

            public void setFz_hjdz(String fz_hjdz) {
                this.fz_hjdz = fz_hjdz;
            }

            public String getIs_login() {
                return is_login;
            }

            public void setIs_login(String is_login) {
                this.is_login = is_login;
            }
        }
    }
}
