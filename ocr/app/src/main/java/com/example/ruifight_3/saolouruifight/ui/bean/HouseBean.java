package com.example.ruifight_3.saolouruifight.ui.bean;

import java.util.List;

/**
 * Created by RuiFight-3 on 2018/4/19.
 */

public class HouseBean {


    /**
     * status : true
     * msg : null
     * data : {"fangwu_chuzu":0,"dyTotal":1,"dyList":[{"dys":3,"dybm":"3单元"}],"fangwu_caiji":4,"jzw":{"id":126708,"jzw_bm":"4CBAA1F49D82E4B3E0536902080AA554","jzw_mc":null,"jzw_jlxdm":"4DA9663123AEF0F2E0536902080A23B7","jzw_jlxmc":"椿树园","jzw_dizhi":"1号楼","danwei_id":null,"danwei_mc":null,"sssq_dm":"","sssq_mc":"","leixing":null,"pcsdm":"1101040403000","pcsmc":"椿树派出所","jwh_dm":"110100000000,110102000000,110102015000,1101040403007,","jwh_mc":"椿树园社区","jwzrq_dm":null,"jwzrq_mc":null,"xt_zhgxsj":null,"imp_date":1533104473000,"zulin_qssj":null,"zulin_jzsj":null,"ds_dys":3,"ds_cs":1,"ds_hs":4,"dx_dys":0,"dx_hs":0,"dx_cs":0,"cjryid":null,"cjrymc":null,"whrymc":null,"whsj":null,"beizhu":null,"is_del":"F","ldrk_count":16},"renshu_ldrk":0,"fangwu_total":4,"active":"4DA9663123AEF0F2E0536902080A23B7","renshu_rhfl":0,"renshu_hjrk":0}
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
         * fangwu_chuzu : 0
         * dyTotal : 1
         * dyList : [{"dys":3,"dybm":"3单元"}]
         * fangwu_caiji : 4
         * jzw : {"id":126708,"jzw_bm":"4CBAA1F49D82E4B3E0536902080AA554","jzw_mc":null,"jzw_jlxdm":"4DA9663123AEF0F2E0536902080A23B7","jzw_jlxmc":"椿树园","jzw_dizhi":"1号楼","danwei_id":null,"danwei_mc":null,"sssq_dm":"","sssq_mc":"","leixing":null,"pcsdm":"1101040403000","pcsmc":"椿树派出所","jwh_dm":"110100000000,110102000000,110102015000,1101040403007,","jwh_mc":"椿树园社区","jwzrq_dm":null,"jwzrq_mc":null,"xt_zhgxsj":null,"imp_date":1533104473000,"zulin_qssj":null,"zulin_jzsj":null,"ds_dys":3,"ds_cs":1,"ds_hs":4,"dx_dys":0,"dx_hs":0,"dx_cs":0,"cjryid":null,"cjrymc":null,"whrymc":null,"whsj":null,"beizhu":null,"is_del":"F","ldrk_count":16}
         * renshu_ldrk : 0
         * fangwu_total : 4
         * active : 4DA9663123AEF0F2E0536902080A23B7
         * renshu_rhfl : 0
         * renshu_hjrk : 0
         */

        private int fangwu_chuzu;
        private int dyTotal;
        private int fangwu_caiji;
        private JzwBean jzw;
        private int renshu_ldrk;
        private int fangwu_total;
        private String active;
        private int renshu_rhfl;
        private int renshu_hjrk;
        private List<DyListBean> dyList;

        public int getFangwu_chuzu() {
            return fangwu_chuzu;
        }

        public void setFangwu_chuzu(int fangwu_chuzu) {
            this.fangwu_chuzu = fangwu_chuzu;
        }

        public int getDyTotal() {
            return dyTotal;
        }

        public void setDyTotal(int dyTotal) {
            this.dyTotal = dyTotal;
        }

        public int getFangwu_caiji() {
            return fangwu_caiji;
        }

        public void setFangwu_caiji(int fangwu_caiji) {
            this.fangwu_caiji = fangwu_caiji;
        }

        public JzwBean getJzw() {
            return jzw;
        }

        public void setJzw(JzwBean jzw) {
            this.jzw = jzw;
        }

        public int getRenshu_ldrk() {
            return renshu_ldrk;
        }

        public void setRenshu_ldrk(int renshu_ldrk) {
            this.renshu_ldrk = renshu_ldrk;
        }

        public int getFangwu_total() {
            return fangwu_total;
        }

        public void setFangwu_total(int fangwu_total) {
            this.fangwu_total = fangwu_total;
        }

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
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

        public List<DyListBean> getDyList() {
            return dyList;
        }

        public void setDyList(List<DyListBean> dyList) {
            this.dyList = dyList;
        }

        public static class JzwBean {
            /**
             * id : 126708
             * jzw_bm : 4CBAA1F49D82E4B3E0536902080AA554
             * jzw_mc : null
             * jzw_jlxdm : 4DA9663123AEF0F2E0536902080A23B7
             * jzw_jlxmc : 椿树园
             * jzw_dizhi : 1号楼
             * danwei_id : null
             * danwei_mc : null
             * sssq_dm :
             * sssq_mc :
             * leixing : null
             * pcsdm : 1101040403000
             * pcsmc : 椿树派出所
             * jwh_dm : 110100000000,110102000000,110102015000,1101040403007,
             * jwh_mc : 椿树园社区
             * jwzrq_dm : null
             * jwzrq_mc : null
             * xt_zhgxsj : null
             * imp_date : 1533104473000
             * zulin_qssj : null
             * zulin_jzsj : null
             * ds_dys : 3
             * ds_cs : 1
             * ds_hs : 4
             * dx_dys : 0
             * dx_hs : 0
             * dx_cs : 0
             * cjryid : null
             * cjrymc : null
             * whrymc : null
             * whsj : null
             * beizhu : null
             * is_del : F
             * ldrk_count : 16
             */

            private int id;
            private String jzw_bm;
            private Object jzw_mc;
            private String jzw_jlxdm;
            private String jzw_jlxmc;
            private String jzw_dizhi;
            private Object danwei_id;
            private Object danwei_mc;
            private String sssq_dm;
            private String sssq_mc;
            private Object leixing;
            private String pcsdm;
            private String pcsmc;
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
            private Object cjryid;
            private Object cjrymc;
            private Object whrymc;
            private Object whsj;
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

            public Object getJzw_mc() {
                return jzw_mc;
            }

            public void setJzw_mc(Object jzw_mc) {
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

            public Object getLeixing() {
                return leixing;
            }

            public void setLeixing(Object leixing) {
                this.leixing = leixing;
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

            public Object getCjryid() {
                return cjryid;
            }

            public void setCjryid(Object cjryid) {
                this.cjryid = cjryid;
            }

            public Object getCjrymc() {
                return cjrymc;
            }

            public void setCjrymc(Object cjrymc) {
                this.cjrymc = cjrymc;
            }

            public Object getWhrymc() {
                return whrymc;
            }

            public void setWhrymc(Object whrymc) {
                this.whrymc = whrymc;
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

        public static class DyListBean {
            /**
             * dys : 3
             * dybm : 3单元
             */

            private int dys;
            private String dybm;

            public int getDys() {
                return dys;
            }

            public void setDys(int dys) {
                this.dys = dys;
            }

            public String getDybm() {
                return dybm;
            }

            public void setDybm(String dybm) {
                this.dybm = dybm;
            }
        }
    }
}
