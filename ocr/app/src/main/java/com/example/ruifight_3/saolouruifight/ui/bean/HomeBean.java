package com.example.ruifight_3.saolouruifight.ui.bean;

import java.util.List;

/**
 * Created by RuiFight-3 on 2018/4/18.
 */

public class HomeBean {


    /**
     * status : true
     * msg : null
     * data : {"active":"list","jlxList":[{"id":30,"jlxmc":"苏家坨镇后沙涧第一小区","jlxdm":"4D83F3EEDEB4A35FE0536902080A8882","dzysqx":"海淀区","pcsdm":"1101080822000","pcsmc":"苏家坨派出所","sssq_dm":"1101080822004","sssq_mc":"后沙涧村","jwhdm":"110108029208","jwhmc":"后沙涧村委会","jwzrqdm":"1101080822001","jwzrqmc":"苏家坨第一警务区1","is_del":"F","imp_date":1526850518000},{"id":32,"jlxmc":"苏家坨镇后沙涧第三小区","jlxdm":"4D83F3EEDEB5A35FE0536902080A8882","dzysqx":"海淀区","pcsdm":"1101080822000","pcsmc":"苏家坨派出所","sssq_dm":"1101080822004","sssq_mc":"后沙涧村","jwhdm":"110108029208","jwhmc":"后沙涧村委会","jwzrqdm":"1101080822001","jwzrqmc":"苏家坨第一警务区1","is_del":"F","imp_date":1526850518000},{"id":33,"jlxmc":"苏家坨镇后沙涧第九小区","jlxdm":"4D83F3EEDEDCA35FE0536902080A8882","dzysqx":"海淀区","pcsdm":"1101080822000","pcsmc":"苏家坨派出所","sssq_dm":"1101080822004","sssq_mc":"后沙涧村","jwhdm":"110108029208","jwhmc":"后沙涧村委会","jwzrqdm":"1101080822001","jwzrqmc":"苏家坨第一警务区1","is_del":"F","imp_date":1526850518000},{"id":31,"jlxmc":"苏家坨镇后沙涧第二小区","jlxdm":"4D83F3EEDEDBA35FE0536902080A8882","dzysqx":"海淀区","pcsdm":"1101080822000","pcsmc":"苏家坨派出所","sssq_dm":"1101080822004","sssq_mc":"后沙涧村","jwhdm":"110108029208","jwhmc":"后沙涧村委会","jwzrqdm":"1101080822001","jwzrqmc":"苏家坨第一警务区1","is_del":"F","imp_date":1526850518000},{"id":35,"jlxmc":"苏家坨镇后沙涧第五小区","jlxdm":"4D83F3EEDEE1A35FE0536902080A8882","dzysqx":"海淀区","pcsdm":"1101080822000","pcsmc":"苏家坨派出所","sssq_dm":"1101080822004","sssq_mc":"后沙涧村","jwhdm":"110108029208","jwhmc":"后沙涧村委会","jwzrqdm":"1101080822001","jwzrqmc":"苏家坨第一警务区1","is_del":"F","imp_date":1526850518000},{"id":36,"jlxmc":"苏家坨镇后沙涧第六小区","jlxdm":"4D83F3EEDEB7A35FE0536902080A8882","dzysqx":"海淀区","pcsdm":"1101080822000","pcsmc":"苏家坨派出所","sssq_dm":"1101080822004","sssq_mc":"后沙涧村","jwhdm":"110108029208","jwhmc":"后沙涧村委会","jwzrqdm":"1101080822001","jwzrqmc":"苏家坨第一警务区1","is_del":"F","imp_date":1526850518000},{"id":34,"jlxmc":"苏家坨镇后沙涧第四小区","jlxdm":"4D83F3EEDEB6A35FE0536902080A8882","dzysqx":"海淀区","pcsdm":"1101080822000","pcsmc":"苏家坨派出所","sssq_dm":"1101080822004","sssq_mc":"后沙涧村","jwhdm":"110108029208","jwhmc":"后沙涧村委会","jwzrqdm":"1101080822001","jwzrqmc":"苏家坨第一警务区1","is_del":"F","imp_date":1526850518000}],"title":"信息浏览"}
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
         * active : list
         * jlxList : [{"id":30,"jlxmc":"苏家坨镇后沙涧第一小区","jlxdm":"4D83F3EEDEB4A35FE0536902080A8882","dzysqx":"海淀区","pcsdm":"1101080822000","pcsmc":"苏家坨派出所","sssq_dm":"1101080822004","sssq_mc":"后沙涧村","jwhdm":"110108029208","jwhmc":"后沙涧村委会","jwzrqdm":"1101080822001","jwzrqmc":"苏家坨第一警务区1","is_del":"F","imp_date":1526850518000},{"id":32,"jlxmc":"苏家坨镇后沙涧第三小区","jlxdm":"4D83F3EEDEB5A35FE0536902080A8882","dzysqx":"海淀区","pcsdm":"1101080822000","pcsmc":"苏家坨派出所","sssq_dm":"1101080822004","sssq_mc":"后沙涧村","jwhdm":"110108029208","jwhmc":"后沙涧村委会","jwzrqdm":"1101080822001","jwzrqmc":"苏家坨第一警务区1","is_del":"F","imp_date":1526850518000},{"id":33,"jlxmc":"苏家坨镇后沙涧第九小区","jlxdm":"4D83F3EEDEDCA35FE0536902080A8882","dzysqx":"海淀区","pcsdm":"1101080822000","pcsmc":"苏家坨派出所","sssq_dm":"1101080822004","sssq_mc":"后沙涧村","jwhdm":"110108029208","jwhmc":"后沙涧村委会","jwzrqdm":"1101080822001","jwzrqmc":"苏家坨第一警务区1","is_del":"F","imp_date":1526850518000},{"id":31,"jlxmc":"苏家坨镇后沙涧第二小区","jlxdm":"4D83F3EEDEDBA35FE0536902080A8882","dzysqx":"海淀区","pcsdm":"1101080822000","pcsmc":"苏家坨派出所","sssq_dm":"1101080822004","sssq_mc":"后沙涧村","jwhdm":"110108029208","jwhmc":"后沙涧村委会","jwzrqdm":"1101080822001","jwzrqmc":"苏家坨第一警务区1","is_del":"F","imp_date":1526850518000},{"id":35,"jlxmc":"苏家坨镇后沙涧第五小区","jlxdm":"4D83F3EEDEE1A35FE0536902080A8882","dzysqx":"海淀区","pcsdm":"1101080822000","pcsmc":"苏家坨派出所","sssq_dm":"1101080822004","sssq_mc":"后沙涧村","jwhdm":"110108029208","jwhmc":"后沙涧村委会","jwzrqdm":"1101080822001","jwzrqmc":"苏家坨第一警务区1","is_del":"F","imp_date":1526850518000},{"id":36,"jlxmc":"苏家坨镇后沙涧第六小区","jlxdm":"4D83F3EEDEB7A35FE0536902080A8882","dzysqx":"海淀区","pcsdm":"1101080822000","pcsmc":"苏家坨派出所","sssq_dm":"1101080822004","sssq_mc":"后沙涧村","jwhdm":"110108029208","jwhmc":"后沙涧村委会","jwzrqdm":"1101080822001","jwzrqmc":"苏家坨第一警务区1","is_del":"F","imp_date":1526850518000},{"id":34,"jlxmc":"苏家坨镇后沙涧第四小区","jlxdm":"4D83F3EEDEB6A35FE0536902080A8882","dzysqx":"海淀区","pcsdm":"1101080822000","pcsmc":"苏家坨派出所","sssq_dm":"1101080822004","sssq_mc":"后沙涧村","jwhdm":"110108029208","jwhmc":"后沙涧村委会","jwzrqdm":"1101080822001","jwzrqmc":"苏家坨第一警务区1","is_del":"F","imp_date":1526850518000}]
         * title : 信息浏览
         */

        private String active;
        private String title;
        private List<JlxListBean> jlxList;

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<JlxListBean> getJlxList() {
            return jlxList;
        }

        public void setJlxList(List<JlxListBean> jlxList) {
            this.jlxList = jlxList;
        }

        public static class JlxListBean {
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
    }
}
