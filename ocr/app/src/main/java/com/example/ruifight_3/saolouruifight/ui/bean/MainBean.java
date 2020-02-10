package com.example.ruifight_3.saolouruifight.ui.bean;

import java.util.List;

/**
 * Created by RuiFight-3 on 2018/4/17.
 */

public class MainBean {


    /**
     * status : true
     * msg : null
     * data : {"jzwList":[{"id":126730,"jzw_bm":"f2cd02c22b4d4c2da82d06ec7019a376","jzw_mc":"1","jzw_jlxdm":"a7cf8d677fb9463cb598ce0b426e4cec","jzw_jlxmc":"椿树园测试街道cs","jzw_dizhi":"椿树园测试建筑物长度","danwei_id":null,"danwei_mc":null,"sssq_dm":null,"sssq_mc":null,"leixing":"住宅用房","pcsdm":null,"pcsmc":null,"jwh_dm":"110100000000,110102000000,110102015000,1101040403007,","jwh_mc":"椿树园测试街道cs","jwzrq_dm":null,"jwzrq_mc":null,"xt_zhgxsj":null,"imp_date":1542771668000,"zulin_qssj":null,"zulin_jzsj":null,"ds_dys":3,"ds_cs":2,"ds_hs":2,"dx_dys":0,"dx_hs":0,"dx_cs":0,"cjryid":"25","cjrymc":"cspcs","whrymc":"cspcs","whsj":1555659855000,"beizhu":null,"is_del":"F","ldrk_count":2}],"sel_jlx":{"id":1072,"jlxmc":"椿树园测试街道cs","jlxdm":"a7cf8d677fb9463cb598ce0b426e4cec","dzysqx":null,"pcsdm":null,"pcsmc":null,"sssq_dm":null,"sssq_mc":null,"jwhdm":"110100000000,110102000000,110102015000,1101040403007,","jwhmc":"椿树园社区居委会","jwzrqdm":null,"jwzrqmc":null,"is_del":"F","imp_date":1542182873000},"active":"a7cf8d677fb9463cb598ce0b426e4cec","jlxList":[{"id":1070,"jlxmc":"椿树园","jlxdm":"4DA9663123AEF0F2E0536902080A23B7","dzysqx":"西城区","pcsdm":"1101040403000","pcsmc":"椿树派出所","sssq_dm":null,"sssq_mc":null,"jwhdm":"110100000000,110102000000,110102015000,1101040403007,","jwhmc":"椿树园社区","jwzrqdm":null,"jwzrqmc":null,"is_del":null,"imp_date":null},{"id":1072,"jlxmc":"椿树园测试街道cs","jlxdm":"a7cf8d677fb9463cb598ce0b426e4cec","dzysqx":null,"pcsdm":null,"pcsmc":null,"sssq_dm":null,"sssq_mc":null,"jwhdm":"110100000000,110102000000,110102015000,1101040403007,","jwhmc":"椿树园社区居委会","jwzrqdm":null,"jwzrqmc":null,"is_del":null,"imp_date":null}]}
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
         * jzwList : [{"id":126730,"jzw_bm":"f2cd02c22b4d4c2da82d06ec7019a376","jzw_mc":"1","jzw_jlxdm":"a7cf8d677fb9463cb598ce0b426e4cec","jzw_jlxmc":"椿树园测试街道cs","jzw_dizhi":"椿树园测试建筑物长度","danwei_id":null,"danwei_mc":null,"sssq_dm":null,"sssq_mc":null,"leixing":"住宅用房","pcsdm":null,"pcsmc":null,"jwh_dm":"110100000000,110102000000,110102015000,1101040403007,","jwh_mc":"椿树园测试街道cs","jwzrq_dm":null,"jwzrq_mc":null,"xt_zhgxsj":null,"imp_date":1542771668000,"zulin_qssj":null,"zulin_jzsj":null,"ds_dys":3,"ds_cs":2,"ds_hs":2,"dx_dys":0,"dx_hs":0,"dx_cs":0,"cjryid":"25","cjrymc":"cspcs","whrymc":"cspcs","whsj":1555659855000,"beizhu":null,"is_del":"F","ldrk_count":2}]
         * sel_jlx : {"id":1072,"jlxmc":"椿树园测试街道cs","jlxdm":"a7cf8d677fb9463cb598ce0b426e4cec","dzysqx":null,"pcsdm":null,"pcsmc":null,"sssq_dm":null,"sssq_mc":null,"jwhdm":"110100000000,110102000000,110102015000,1101040403007,","jwhmc":"椿树园社区居委会","jwzrqdm":null,"jwzrqmc":null,"is_del":"F","imp_date":1542182873000}
         * active : a7cf8d677fb9463cb598ce0b426e4cec
         * jlxList : [{"id":1070,"jlxmc":"椿树园","jlxdm":"4DA9663123AEF0F2E0536902080A23B7","dzysqx":"西城区","pcsdm":"1101040403000","pcsmc":"椿树派出所","sssq_dm":null,"sssq_mc":null,"jwhdm":"110100000000,110102000000,110102015000,1101040403007,","jwhmc":"椿树园社区","jwzrqdm":null,"jwzrqmc":null,"is_del":null,"imp_date":null},{"id":1072,"jlxmc":"椿树园测试街道cs","jlxdm":"a7cf8d677fb9463cb598ce0b426e4cec","dzysqx":null,"pcsdm":null,"pcsmc":null,"sssq_dm":null,"sssq_mc":null,"jwhdm":"110100000000,110102000000,110102015000,1101040403007,","jwhmc":"椿树园社区居委会","jwzrqdm":null,"jwzrqmc":null,"is_del":null,"imp_date":null}]
         */

        private SelJlxBean sel_jlx;
        private String active;
        private List<JzwListBean> jzwList;
        private List<JlxListBean> jlxList;

        public SelJlxBean getSel_jlx() {
            return sel_jlx;
        }

        public void setSel_jlx(SelJlxBean sel_jlx) {
            this.sel_jlx = sel_jlx;
        }

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }

        public List<JzwListBean> getJzwList() {
            return jzwList;
        }

        public void setJzwList(List<JzwListBean> jzwList) {
            this.jzwList = jzwList;
        }

        public List<JlxListBean> getJlxList() {
            return jlxList;
        }

        public void setJlxList(List<JlxListBean> jlxList) {
            this.jlxList = jlxList;
        }

        public static class SelJlxBean {
            /**
             * id : 1072
             * jlxmc : 椿树园测试街道cs
             * jlxdm : a7cf8d677fb9463cb598ce0b426e4cec
             * dzysqx : null
             * pcsdm : null
             * pcsmc : null
             * sssq_dm : null
             * sssq_mc : null
             * jwhdm : 110100000000,110102000000,110102015000,1101040403007,
             * jwhmc : 椿树园社区居委会
             * jwzrqdm : null
             * jwzrqmc : null
             * is_del : F
             * imp_date : 1542182873000
             */

            private int id;
            private String jlxmc;
            private String jlxdm;
            private Object dzysqx;
            private Object pcsdm;
            private Object pcsmc;
            private Object sssq_dm;
            private Object sssq_mc;
            private String jwhdm;
            private String jwhmc;
            private Object jwzrqdm;
            private Object jwzrqmc;
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

            public Object getDzysqx() {
                return dzysqx;
            }

            public void setDzysqx(Object dzysqx) {
                this.dzysqx = dzysqx;
            }

            public Object getPcsdm() {
                return pcsdm;
            }

            public void setPcsdm(Object pcsdm) {
                this.pcsdm = pcsdm;
            }

            public Object getPcsmc() {
                return pcsmc;
            }

            public void setPcsmc(Object pcsmc) {
                this.pcsmc = pcsmc;
            }

            public Object getSssq_dm() {
                return sssq_dm;
            }

            public void setSssq_dm(Object sssq_dm) {
                this.sssq_dm = sssq_dm;
            }

            public Object getSssq_mc() {
                return sssq_mc;
            }

            public void setSssq_mc(Object sssq_mc) {
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

            public Object getJwzrqdm() {
                return jwzrqdm;
            }

            public void setJwzrqdm(Object jwzrqdm) {
                this.jwzrqdm = jwzrqdm;
            }

            public Object getJwzrqmc() {
                return jwzrqmc;
            }

            public void setJwzrqmc(Object jwzrqmc) {
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

        public static class JzwListBean {
            /**
             * id : 126730
             * jzw_bm : f2cd02c22b4d4c2da82d06ec7019a376
             * jzw_mc : 1
             * jzw_jlxdm : a7cf8d677fb9463cb598ce0b426e4cec
             * jzw_jlxmc : 椿树园测试街道cs
             * jzw_dizhi : 椿树园测试建筑物长度
             * danwei_id : null
             * danwei_mc : null
             * sssq_dm : null
             * sssq_mc : null
             * leixing : 住宅用房
             * pcsdm : null
             * pcsmc : null
             * jwh_dm : 110100000000,110102000000,110102015000,1101040403007,
             * jwh_mc : 椿树园测试街道cs
             * jwzrq_dm : null
             * jwzrq_mc : null
             * xt_zhgxsj : null
             * imp_date : 1542771668000
             * zulin_qssj : null
             * zulin_jzsj : null
             * ds_dys : 3
             * ds_cs : 2
             * ds_hs : 2
             * dx_dys : 0
             * dx_hs : 0
             * dx_cs : 0
             * cjryid : 25
             * cjrymc : cspcs
             * whrymc : cspcs
             * whsj : 1555659855000
             * beizhu : null
             * is_del : F
             * ldrk_count : 2
             */

            private int id;
            private String jzw_bm;
            private String jzw_mc;
            private String jzw_jlxdm;
            private String jzw_jlxmc;
            private String jzw_dizhi;
            private Object danwei_id;
            private Object danwei_mc;
            private Object sssq_dm;
            private Object sssq_mc;
            private String leixing;
            private Object pcsdm;
            private Object pcsmc;
            private String jwh_dm;
            private String jwh_mc;
            private Object jwzrq_dm;
            private Object jwzrq_mc;
            private Object xt_zhgxsj;
            private long imp_date;
            private Object zulin_qssj;
            private Object zulin_jzsj;
            private int ds_dys;
            private int ds_cs;
            private int ds_hs;
            private int dx_dys;
            private int dx_hs;
            private int dx_cs;
            private String cjryid;
            private String cjrymc;
            private String whrymc;
            private long whsj;
            private Object beizhu;
            private String is_del;
            private int ldrk_count;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getJzw_bm() {
                return jzw_bm;
            }

            public void setJzw_bm(String jzw_bm) {
                this.jzw_bm = jzw_bm;
            }

            public String getJzw_mc() {
                return jzw_mc;
            }

            public void setJzw_mc(String jzw_mc) {
                this.jzw_mc = jzw_mc;
            }

            public String getJzw_jlxdm() {
                return jzw_jlxdm;
            }

            public void setJzw_jlxdm(String jzw_jlxdm) {
                this.jzw_jlxdm = jzw_jlxdm;
            }

            public String getJzw_jlxmc() {
                return jzw_jlxmc;
            }

            public void setJzw_jlxmc(String jzw_jlxmc) {
                this.jzw_jlxmc = jzw_jlxmc;
            }

            public String getJzw_dizhi() {
                return jzw_dizhi;
            }

            public void setJzw_dizhi(String jzw_dizhi) {
                this.jzw_dizhi = jzw_dizhi;
            }

            public Object getDanwei_id() {
                return danwei_id;
            }

            public void setDanwei_id(Object danwei_id) {
                this.danwei_id = danwei_id;
            }

            public Object getDanwei_mc() {
                return danwei_mc;
            }

            public void setDanwei_mc(Object danwei_mc) {
                this.danwei_mc = danwei_mc;
            }

            public Object getSssq_dm() {
                return sssq_dm;
            }

            public void setSssq_dm(Object sssq_dm) {
                this.sssq_dm = sssq_dm;
            }

            public Object getSssq_mc() {
                return sssq_mc;
            }

            public void setSssq_mc(Object sssq_mc) {
                this.sssq_mc = sssq_mc;
            }

            public String getLeixing() {
                return leixing;
            }

            public void setLeixing(String leixing) {
                this.leixing = leixing;
            }

            public Object getPcsdm() {
                return pcsdm;
            }

            public void setPcsdm(Object pcsdm) {
                this.pcsdm = pcsdm;
            }

            public Object getPcsmc() {
                return pcsmc;
            }

            public void setPcsmc(Object pcsmc) {
                this.pcsmc = pcsmc;
            }

            public String getJwh_dm() {
                return jwh_dm;
            }

            public void setJwh_dm(String jwh_dm) {
                this.jwh_dm = jwh_dm;
            }

            public String getJwh_mc() {
                return jwh_mc;
            }

            public void setJwh_mc(String jwh_mc) {
                this.jwh_mc = jwh_mc;
            }

            public Object getJwzrq_dm() {
                return jwzrq_dm;
            }

            public void setJwzrq_dm(Object jwzrq_dm) {
                this.jwzrq_dm = jwzrq_dm;
            }

            public Object getJwzrq_mc() {
                return jwzrq_mc;
            }

            public void setJwzrq_mc(Object jwzrq_mc) {
                this.jwzrq_mc = jwzrq_mc;
            }

            public Object getXt_zhgxsj() {
                return xt_zhgxsj;
            }

            public void setXt_zhgxsj(Object xt_zhgxsj) {
                this.xt_zhgxsj = xt_zhgxsj;
            }

            public long getImp_date() {
                return imp_date;
            }

            public void setImp_date(long imp_date) {
                this.imp_date = imp_date;
            }

            public Object getZulin_qssj() {
                return zulin_qssj;
            }

            public void setZulin_qssj(Object zulin_qssj) {
                this.zulin_qssj = zulin_qssj;
            }

            public Object getZulin_jzsj() {
                return zulin_jzsj;
            }

            public void setZulin_jzsj(Object zulin_jzsj) {
                this.zulin_jzsj = zulin_jzsj;
            }

            public int getDs_dys() {
                return ds_dys;
            }

            public void setDs_dys(int ds_dys) {
                this.ds_dys = ds_dys;
            }

            public int getDs_cs() {
                return ds_cs;
            }

            public void setDs_cs(int ds_cs) {
                this.ds_cs = ds_cs;
            }

            public int getDs_hs() {
                return ds_hs;
            }

            public void setDs_hs(int ds_hs) {
                this.ds_hs = ds_hs;
            }

            public int getDx_dys() {
                return dx_dys;
            }

            public void setDx_dys(int dx_dys) {
                this.dx_dys = dx_dys;
            }

            public int getDx_hs() {
                return dx_hs;
            }

            public void setDx_hs(int dx_hs) {
                this.dx_hs = dx_hs;
            }

            public int getDx_cs() {
                return dx_cs;
            }

            public void setDx_cs(int dx_cs) {
                this.dx_cs = dx_cs;
            }

            public String getCjryid() {
                return cjryid;
            }

            public void setCjryid(String cjryid) {
                this.cjryid = cjryid;
            }

            public String getCjrymc() {
                return cjrymc;
            }

            public void setCjrymc(String cjrymc) {
                this.cjrymc = cjrymc;
            }

            public String getWhrymc() {
                return whrymc;
            }

            public void setWhrymc(String whrymc) {
                this.whrymc = whrymc;
            }

            public long getWhsj() {
                return whsj;
            }

            public void setWhsj(long whsj) {
                this.whsj = whsj;
            }

            public Object getBeizhu() {
                return beizhu;
            }

            public void setBeizhu(Object beizhu) {
                this.beizhu = beizhu;
            }

            public String getIs_del() {
                return is_del;
            }

            public void setIs_del(String is_del) {
                this.is_del = is_del;
            }

            public int getLdrk_count() {
                return ldrk_count;
            }

            public void setLdrk_count(int ldrk_count) {
                this.ldrk_count = ldrk_count;
            }
        }

        public static class JlxListBean {
            /**
             * id : 1070
             * jlxmc : 椿树园
             * jlxdm : 4DA9663123AEF0F2E0536902080A23B7
             * dzysqx : 西城区
             * pcsdm : 1101040403000
             * pcsmc : 椿树派出所
             * sssq_dm : null
             * sssq_mc : null
             * jwhdm : 110100000000,110102000000,110102015000,1101040403007,
             * jwhmc : 椿树园社区
             * jwzrqdm : null
             * jwzrqmc : null
             * is_del : null
             * imp_date : null
             */

            private int id;
            private String jlxmc;
            private String jlxdm;
            private String dzysqx;
            private String pcsdm;
            private String pcsmc;
            private Object sssq_dm;
            private Object sssq_mc;
            private String jwhdm;
            private String jwhmc;
            private Object jwzrqdm;
            private Object jwzrqmc;
            private Object is_del;
            private Object imp_date;

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

            public Object getSssq_dm() {
                return sssq_dm;
            }

            public void setSssq_dm(Object sssq_dm) {
                this.sssq_dm = sssq_dm;
            }

            public Object getSssq_mc() {
                return sssq_mc;
            }

            public void setSssq_mc(Object sssq_mc) {
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

            public Object getJwzrqdm() {
                return jwzrqdm;
            }

            public void setJwzrqdm(Object jwzrqdm) {
                this.jwzrqdm = jwzrqdm;
            }

            public Object getJwzrqmc() {
                return jwzrqmc;
            }

            public void setJwzrqmc(Object jwzrqmc) {
                this.jwzrqmc = jwzrqmc;
            }

            public Object getIs_del() {
                return is_del;
            }

            public void setIs_del(Object is_del) {
                this.is_del = is_del;
            }

            public Object getImp_date() {
                return imp_date;
            }

            public void setImp_date(Object imp_date) {
                this.imp_date = imp_date;
            }
        }
    }
}
