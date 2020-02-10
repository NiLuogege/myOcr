package com.example.ruifight_3.saolouruifight.ui.bean;

import java.util.List;

/**
 * Created by RuiFight-3 on 2018/5/11.
 */

public class AddHouseBean {


    /**
     * status : true
     * msg : null
     * data : {"ryList":[{"id":1,"xm":"得得得11","sfz":"410181**1519","bieming":"","xb":"男","minzu":"蒙古","hj_ssx":"按时吃","hj_hjxz":"饭额地方是我","lxdh":"13264054531","jz_fwid":"4CBAA1EE9B90E4B3E0536902080AA554-1-1-1","jz_jzwbm":"4CBAA1EE9B90E4B3E0536902080AA554","dizhi_jzwdz":"苏家坨镇后沙涧第一小区19号","dizhi_xz":"1号","rylx":"流动人口","ldrk_whcd":"大专","ldrk_hunyin":"有配偶","ldrk_zzcs":"市场门店","ldrk_zzsy":"务工","ldrk_ljqcyzk":"","ldrk_xcyzk":"","ldrk_ljrq":1529251200000,"ldrk_fwcs":"","cjsj":1529648761000,"is_del":"否","whry":"liuguanyuan","whsj":1529648761000,"beizhu":"","picture":null}],"ryys":1,"sel_jlx":{"id":30,"jlxmc":"苏家坨镇后沙涧第一小区","jlxdm":"4D83F3EEDEB4A35FE0536902080A8882","dzysqx":"海淀区","pcsdm":"1101080822000","pcsmc":"苏家坨派出所","sssq_dm":"1101080822004","sssq_mc":"后沙涧村","jwhdm":"110108029208","jwhmc":"后沙涧村委会","jwzrqdm":"1101080822001","jwzrqmc":"苏家坨第一警务区1","is_del":"F","imp_date":1526850518000},"fangjian":{"id":5219,"jzw_dm":"4CBAA1EE9B90E4B3E0536902080AA554","hu_id":"4CBAA1EE9B90E4B3E0536902080AA554-1-1-1","jzw_dizhi":"苏家坨镇后沙涧第一小区19号","dys":1,"cs":1,"hs":1,"dymc":null,"humc":"1号","fjlx":"P","ssdwdm":null,"ssdwmc":null,"whrybm":null,"whsj":1526259330000,"is_del":"F","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_xm":"得得得","fz_lxdh":"13264054530","fz_sfz":"410181199222221516","fz_fwjzlx":"自住房屋","fz_is_del":"否","fz_whry":"liuguanyuan","fz_whsh":1529648590000,"fz_bz":"asd "},"idCard":"410181**1516","bean":{"id":null,"xm":null,"sfz":null,"bieming":null,"xb":null,"minzu":null,"hj_ssx":null,"hj_hjxz":null,"lxdh":null,"jz_fwid":"4CBAA1EE9B90E4B3E0536902080AA554-1-1-1","jz_jzwbm":"4CBAA1EE9B90E4B3E0536902080AA554","dizhi_jzwdz":"苏家坨镇后沙涧第一小区19号","dizhi_xz":"1号","rylx":"流动人口","ldrk_whcd":null,"ldrk_hunyin":null,"ldrk_zzcs":null,"ldrk_zzsy":null,"ldrk_ljqcyzk":null,"ldrk_xcyzk":null,"ldrk_ljrq":null,"ldrk_fwcs":null,"cjsj":null,"is_del":null,"whry":null,"whsj":null,"beizhu":null,"picture":null}}
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
         * ryList : [{"id":1,"xm":"得得得11","sfz":"410181**1519","bieming":"","xb":"男","minzu":"蒙古","hj_ssx":"按时吃","hj_hjxz":"饭额地方是我","lxdh":"13264054531","jz_fwid":"4CBAA1EE9B90E4B3E0536902080AA554-1-1-1","jz_jzwbm":"4CBAA1EE9B90E4B3E0536902080AA554","dizhi_jzwdz":"苏家坨镇后沙涧第一小区19号","dizhi_xz":"1号","rylx":"流动人口","ldrk_whcd":"大专","ldrk_hunyin":"有配偶","ldrk_zzcs":"市场门店","ldrk_zzsy":"务工","ldrk_ljqcyzk":"","ldrk_xcyzk":"","ldrk_ljrq":1529251200000,"ldrk_fwcs":"","cjsj":1529648761000,"is_del":"否","whry":"liuguanyuan","whsj":1529648761000,"beizhu":"","picture":null}]
         * ryys : 1
         * sel_jlx : {"id":30,"jlxmc":"苏家坨镇后沙涧第一小区","jlxdm":"4D83F3EEDEB4A35FE0536902080A8882","dzysqx":"海淀区","pcsdm":"1101080822000","pcsmc":"苏家坨派出所","sssq_dm":"1101080822004","sssq_mc":"后沙涧村","jwhdm":"110108029208","jwhmc":"后沙涧村委会","jwzrqdm":"1101080822001","jwzrqmc":"苏家坨第一警务区1","is_del":"F","imp_date":1526850518000}
         * fangjian : {"id":5219,"jzw_dm":"4CBAA1EE9B90E4B3E0536902080AA554","hu_id":"4CBAA1EE9B90E4B3E0536902080AA554-1-1-1","jzw_dizhi":"苏家坨镇后沙涧第一小区19号","dys":1,"cs":1,"hs":1,"dymc":null,"humc":"1号","fjlx":"P","ssdwdm":null,"ssdwmc":null,"whrybm":null,"whsj":1526259330000,"is_del":"F","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_xm":"得得得","fz_lxdh":"13264054530","fz_sfz":"410181199222221516","fz_fwjzlx":"自住房屋","fz_is_del":"否","fz_whry":"liuguanyuan","fz_whsh":1529648590000,"fz_bz":"asd "}
         * idCard : 410181**1516
         * bean : {"id":null,"xm":null,"sfz":null,"bieming":null,"xb":null,"minzu":null,"hj_ssx":null,"hj_hjxz":null,"lxdh":null,"jz_fwid":"4CBAA1EE9B90E4B3E0536902080AA554-1-1-1","jz_jzwbm":"4CBAA1EE9B90E4B3E0536902080AA554","dizhi_jzwdz":"苏家坨镇后沙涧第一小区19号","dizhi_xz":"1号","rylx":"流动人口","ldrk_whcd":null,"ldrk_hunyin":null,"ldrk_zzcs":null,"ldrk_zzsy":null,"ldrk_ljqcyzk":null,"ldrk_xcyzk":null,"ldrk_ljrq":null,"ldrk_fwcs":null,"cjsj":null,"is_del":null,"whry":null,"whsj":null,"beizhu":null,"picture":null}
         */

        private int ryys;
        private SelJlxBean sel_jlx;
        private FangjianBean fangjian;
        private String idCard;
        private BeanBean bean;
        private List<RyListBean> ryList;

        public int getRyys() {
            return ryys;
        }

        public void setRyys(int ryys) {
            this.ryys = ryys;
        }

        public SelJlxBean getSel_jlx() {
            return sel_jlx;
        }

        public void setSel_jlx(SelJlxBean sel_jlx) {
            this.sel_jlx = sel_jlx;
        }

        public FangjianBean getFangjian() {
            return fangjian;
        }

        public void setFangjian(FangjianBean fangjian) {
            this.fangjian = fangjian;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public BeanBean getBean() {
            return bean;
        }

        public void setBean(BeanBean bean) {
            this.bean = bean;
        }

        public List<RyListBean> getRyList() {
            return ryList;
        }

        public void setRyList(List<RyListBean> ryList) {
            this.ryList = ryList;
        }

        public static class SelJlxBean {
            /**
             * id : 30
             * jlxmc : 苏家坨镇后沙涧第一小区
             * jlxdm : 4D83F3EEDEB4A35FE0536902080A8882
             * dzysqx : 海淀区
             * pcsdm : 1101080822000
             * pcsmc : 苏家坨派出所
             * sssq_dm : 1101080822004
             * sssq_mc : 后沙涧村
             * jwhdm : 110108029208
             * jwhmc : 后沙涧村委会
             * jwzrqdm : 1101080822001
             * jwzrqmc : 苏家坨第一警务区1
             * is_del : F
             * imp_date : 1526850518000
             */

            private int id;
            private String jlxmc;
            private String jlxdm;
            private String dzysqx;
            private String pcsdm;
            private String pcsmc;
            private String sssq_dm;
            private String sssq_mc;
            private String jwhdm;
            private String jwhmc;
            private String jwzrqdm;
            private String jwzrqmc;
            private String is_del;
            private long imp_date;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getJlxmc() {
                return jlxmc;
            }

            public void setJlxmc(String jlxmc) {
                this.jlxmc = jlxmc;
            }

            public String getJlxdm() {
                return jlxdm;
            }

            public void setJlxdm(String jlxdm) {
                this.jlxdm = jlxdm;
            }

            public String getDzysqx() {
                return dzysqx;
            }

            public void setDzysqx(String dzysqx) {
                this.dzysqx = dzysqx;
            }

            public String getPcsdm() {
                return pcsdm;
            }

            public void setPcsdm(String pcsdm) {
                this.pcsdm = pcsdm;
            }

            public String getPcsmc() {
                return pcsmc;
            }

            public void setPcsmc(String pcsmc) {
                this.pcsmc = pcsmc;
            }

            public String getSssq_dm() {
                return sssq_dm;
            }

            public void setSssq_dm(String sssq_dm) {
                this.sssq_dm = sssq_dm;
            }

            public String getSssq_mc() {
                return sssq_mc;
            }

            public void setSssq_mc(String sssq_mc) {
                this.sssq_mc = sssq_mc;
            }

            public String getJwhdm() {
                return jwhdm;
            }

            public void setJwhdm(String jwhdm) {
                this.jwhdm = jwhdm;
            }

            public String getJwhmc() {
                return jwhmc;
            }

            public void setJwhmc(String jwhmc) {
                this.jwhmc = jwhmc;
            }

            public String getJwzrqdm() {
                return jwzrqdm;
            }

            public void setJwzrqdm(String jwzrqdm) {
                this.jwzrqdm = jwzrqdm;
            }

            public String getJwzrqmc() {
                return jwzrqmc;
            }

            public void setJwzrqmc(String jwzrqmc) {
                this.jwzrqmc = jwzrqmc;
            }

            public String getIs_del() {
                return is_del;
            }

            public void setIs_del(String is_del) {
                this.is_del = is_del;
            }

            public long getImp_date() {
                return imp_date;
            }

            public void setImp_date(long imp_date) {
                this.imp_date = imp_date;
            }
        }

        public static class FangjianBean {
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
             * fz_whsh : 1529648590000
             * fz_bz : asd
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
        }

        public static class BeanBean {
            /**
             * id : null
             * xm : null
             * sfz : null
             * bieming : null
             * xb : null
             * minzu : null
             * hj_ssx : null
             * hj_hjxz : null
             * lxdh : null
             * jz_fwid : 4CBAA1EE9B90E4B3E0536902080AA554-1-1-1
             * jz_jzwbm : 4CBAA1EE9B90E4B3E0536902080AA554
             * dizhi_jzwdz : 苏家坨镇后沙涧第一小区19号
             * dizhi_xz : 1号
             * rylx : 流动人口
             * ldrk_whcd : null
             * ldrk_hunyin : null
             * ldrk_zzcs : null
             * ldrk_zzsy : null
             * ldrk_ljqcyzk : null
             * ldrk_xcyzk : null
             * ldrk_ljrq : null
             * ldrk_fwcs : null
             * cjsj : null
             * is_del : null
             * whry : null
             * whsj : null
             * beizhu : null
             * picture : null
             */

            private Object id;
            private Object xm;
            private Object sfz;
            private Object bieming;
            private Object xb;
            private Object minzu;
            private Object hj_ssx;
            private Object hj_hjxz;
            private Object lxdh;
            private String jz_fwid;
            private String jz_jzwbm;
            private String dizhi_jzwdz;
            private String dizhi_xz;
            private String rylx;
            private Object ldrk_whcd;
            private Object ldrk_hunyin;
            private Object ldrk_zzcs;
            private Object ldrk_zzsy;
            private Object ldrk_ljqcyzk;
            private Object ldrk_xcyzk;
            private Object ldrk_ljrq;
            private Object ldrk_fwcs;
            private Object cjsj;
            private Object is_del;
            private Object whry;
            private Object whsj;
            private Object beizhu;
            private Object picture;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public Object getXm() {
                return xm;
            }

            public void setXm(Object xm) {
                this.xm = xm;
            }

            public Object getSfz() {
                return sfz;
            }

            public void setSfz(Object sfz) {
                this.sfz = sfz;
            }

            public Object getBieming() {
                return bieming;
            }

            public void setBieming(Object bieming) {
                this.bieming = bieming;
            }

            public Object getXb() {
                return xb;
            }

            public void setXb(Object xb) {
                this.xb = xb;
            }

            public Object getMinzu() {
                return minzu;
            }

            public void setMinzu(Object minzu) {
                this.minzu = minzu;
            }

            public Object getHj_ssx() {
                return hj_ssx;
            }

            public void setHj_ssx(Object hj_ssx) {
                this.hj_ssx = hj_ssx;
            }

            public Object getHj_hjxz() {
                return hj_hjxz;
            }

            public void setHj_hjxz(Object hj_hjxz) {
                this.hj_hjxz = hj_hjxz;
            }

            public Object getLxdh() {
                return lxdh;
            }

            public void setLxdh(Object lxdh) {
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

            public String getRylx() {
                return rylx;
            }

            public void setRylx(String rylx) {
                this.rylx = rylx;
            }

            public Object getLdrk_whcd() {
                return ldrk_whcd;
            }

            public void setLdrk_whcd(Object ldrk_whcd) {
                this.ldrk_whcd = ldrk_whcd;
            }

            public Object getLdrk_hunyin() {
                return ldrk_hunyin;
            }

            public void setLdrk_hunyin(Object ldrk_hunyin) {
                this.ldrk_hunyin = ldrk_hunyin;
            }

            public Object getLdrk_zzcs() {
                return ldrk_zzcs;
            }

            public void setLdrk_zzcs(Object ldrk_zzcs) {
                this.ldrk_zzcs = ldrk_zzcs;
            }

            public Object getLdrk_zzsy() {
                return ldrk_zzsy;
            }

            public void setLdrk_zzsy(Object ldrk_zzsy) {
                this.ldrk_zzsy = ldrk_zzsy;
            }

            public Object getLdrk_ljqcyzk() {
                return ldrk_ljqcyzk;
            }

            public void setLdrk_ljqcyzk(Object ldrk_ljqcyzk) {
                this.ldrk_ljqcyzk = ldrk_ljqcyzk;
            }

            public Object getLdrk_xcyzk() {
                return ldrk_xcyzk;
            }

            public void setLdrk_xcyzk(Object ldrk_xcyzk) {
                this.ldrk_xcyzk = ldrk_xcyzk;
            }

            public Object getLdrk_ljrq() {
                return ldrk_ljrq;
            }

            public void setLdrk_ljrq(Object ldrk_ljrq) {
                this.ldrk_ljrq = ldrk_ljrq;
            }

            public Object getLdrk_fwcs() {
                return ldrk_fwcs;
            }

            public void setLdrk_fwcs(Object ldrk_fwcs) {
                this.ldrk_fwcs = ldrk_fwcs;
            }

            public Object getCjsj() {
                return cjsj;
            }

            public void setCjsj(Object cjsj) {
                this.cjsj = cjsj;
            }

            public Object getIs_del() {
                return is_del;
            }

            public void setIs_del(Object is_del) {
                this.is_del = is_del;
            }

            public Object getWhry() {
                return whry;
            }

            public void setWhry(Object whry) {
                this.whry = whry;
            }

            public Object getWhsj() {
                return whsj;
            }

            public void setWhsj(Object whsj) {
                this.whsj = whsj;
            }

            public Object getBeizhu() {
                return beizhu;
            }

            public void setBeizhu(Object beizhu) {
                this.beizhu = beizhu;
            }

            public Object getPicture() {
                return picture;
            }

            public void setPicture(Object picture) {
                this.picture = picture;
            }
        }

        public static class RyListBean {
            /**
             * id : 1
             * xm : 得得得11
             * sfz : 410181**1519
             * bieming :
             * xb : 男
             * minzu : 蒙古
             * hj_ssx : 按时吃
             * hj_hjxz : 饭额地方是我
             * lxdh : 13264054531
             * jz_fwid : 4CBAA1EE9B90E4B3E0536902080AA554-1-1-1
             * jz_jzwbm : 4CBAA1EE9B90E4B3E0536902080AA554
             * dizhi_jzwdz : 苏家坨镇后沙涧第一小区19号
             * dizhi_xz : 1号
             * rylx : 流动人口
             * ldrk_whcd : 大专
             * ldrk_hunyin : 有配偶
             * ldrk_zzcs : 市场门店
             * ldrk_zzsy : 务工
             * ldrk_ljqcyzk :
             * ldrk_xcyzk :
             * ldrk_ljrq : 1529251200000
             * ldrk_fwcs :
             * cjsj : 1529648761000
             * is_del : 否
             * whry : liuguanyuan
             * whsj : 1529648761000
             * beizhu :
             * picture : null
             */

            private int id;
            private String xm;
            private String sfz;
            private String bieming;
            private String xb;
            private String minzu;
            private String hj_ssx;
            private String hj_hjxz;
            private String lxdh;
            private String jz_fwid;
            private String jz_jzwbm;
            private String dizhi_jzwdz;
            private String dizhi_xz;
            private String rylx;
            private String ldrk_whcd;
            private String ldrk_hunyin;
            private String ldrk_zzcs;
            private String ldrk_zzsy;
            private String ldrk_ljqcyzk;
            private String ldrk_xcyzk;
            private long ldrk_ljrq;
            private String ldrk_lxjzdrq;
            private String ldrk_fwcs;
            private long cjsj;
            private String is_del;
            private String whry;
            private long whsj;
            private String beizhu;
            private Object picture;
            private Object new_picture;

            public Object getNew_picture() {
                return new_picture;
            }

            public void setNew_picture(Object new_picture) {
                this.new_picture = new_picture;
            }

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

            public String getBieming() {
                return bieming;
            }

            public void setBieming(String bieming) {
                this.bieming = bieming;
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

            public String getHj_ssx() {
                return hj_ssx;
            }

            public void setHj_ssx(String hj_ssx) {
                this.hj_ssx = hj_ssx;
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

            public String getRylx() {
                return rylx;
            }

            public void setRylx(String rylx) {
                this.rylx = rylx;
            }

            public String getLdrk_whcd() {
                return ldrk_whcd;
            }

            public void setLdrk_whcd(String ldrk_whcd) {
                this.ldrk_whcd = ldrk_whcd;
            }

            public String getLdrk_hunyin() {
                return ldrk_hunyin;
            }

            public void setLdrk_hunyin(String ldrk_hunyin) {
                this.ldrk_hunyin = ldrk_hunyin;
            }

            public String getLdrk_zzcs() {
                return ldrk_zzcs;
            }

            public void setLdrk_zzcs(String ldrk_zzcs) {
                this.ldrk_zzcs = ldrk_zzcs;
            }

            public String getLdrk_zzsy() {
                return ldrk_zzsy;
            }

            public void setLdrk_zzsy(String ldrk_zzsy) {
                this.ldrk_zzsy = ldrk_zzsy;
            }

            public String getLdrk_ljqcyzk() {
                return ldrk_ljqcyzk;
            }

            public void setLdrk_ljqcyzk(String ldrk_ljqcyzk) {
                this.ldrk_ljqcyzk = ldrk_ljqcyzk;
            }

            public String getLdrk_xcyzk() {
                return ldrk_xcyzk;
            }

            public void setLdrk_xcyzk(String ldrk_xcyzk) {
                this.ldrk_xcyzk = ldrk_xcyzk;
            }

            public long getLdrk_ljrq() {
                return ldrk_ljrq;
            }

            public void setLdrk_ljrq(long ldrk_ljrq) {
                this.ldrk_ljrq = ldrk_ljrq;
            }

            public String getLdrk_lxjzdrq() {
                return ldrk_lxjzdrq;
            }

            public void setLdrk_lxjzdrq(String ldrk_lxjzdrq) {
                this.ldrk_lxjzdrq = ldrk_lxjzdrq;
            }

            public String getLdrk_fwcs() {
                return ldrk_fwcs;
            }

            public void setLdrk_fwcs(String ldrk_fwcs) {
                this.ldrk_fwcs = ldrk_fwcs;
            }

            public long getCjsj() {
                return cjsj;
            }

            public void setCjsj(long cjsj) {
                this.cjsj = cjsj;
            }

            public String getIs_del() {
                return is_del;
            }

            public void setIs_del(String is_del) {
                this.is_del = is_del;
            }

            public String getWhry() {
                return whry;
            }

            public void setWhry(String whry) {
                this.whry = whry;
            }

            public long getWhsj() {
                return whsj;
            }

            public void setWhsj(long whsj) {
                this.whsj = whsj;
            }

            public String getBeizhu() {
                return beizhu;
            }

            public void setBeizhu(String beizhu) {
                this.beizhu = beizhu;
            }

            public Object getPicture() {
                return picture;
            }

            public void setPicture(Object picture) {
                this.picture = picture;
            }
        }
    }
}
