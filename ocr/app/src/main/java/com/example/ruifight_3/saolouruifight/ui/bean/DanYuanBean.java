package com.example.ruifight_3.saolouruifight.ui.bean;

import java.util.List;

/**
 * Created by RuiFight-3 on 2018/7/5.
 */

public class DanYuanBean {


    /**
     * status : true
     * msg : null
     * data : {"dyDetail":{"dys":1,"bieMing":null,"cengList":[{"cengShu":1,"fjList":[{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-1","hubm":"1号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-2","hubm":"2号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-3","hubm":"3号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-4","hubm":"4号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-5","hubm":"5号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-6","hubm":"6号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-7","hubm":"7号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-8","hubm":"8号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-9","hubm":"9号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-10","hubm":"10号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null}]}]}}
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
         * dyDetail : {"dys":1,"bieMing":null,"cengList":[{"cengShu":1,"fjList":[{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-1","hubm":"1号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-2","hubm":"2号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-3","hubm":"3号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-4","hubm":"4号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-5","hubm":"5号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-6","hubm":"6号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-7","hubm":"7号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-8","hubm":"8号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-9","hubm":"9号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-10","hubm":"10号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null}]}]}
         */

        private DyDetailBean dyDetail;

        public DyDetailBean getDyDetail() {
            return dyDetail;
        }

        public void setDyDetail(DyDetailBean dyDetail) {
            this.dyDetail = dyDetail;
        }

        public static class DyDetailBean {
            /**
             * dys : 1
             * bieMing : null
             * cengList : [{"cengShu":1,"fjList":[{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-1","hubm":"1号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-2","hubm":"2号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-3","hubm":"3号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-4","hubm":"4号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-5","hubm":"5号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-6","hubm":"6号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-7","hubm":"7号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-8","hubm":"8号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-9","hubm":"9号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-10","hubm":"10号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null}]}]
             */

            private int dys;
            private Object bieMing;
            private List<CengListBean> cengList;

            public int getDys() {
                return dys;
            }

            public void setDys(int dys) {
                this.dys = dys;
            }

            public Object getBieMing() {
                return bieMing;
            }

            public void setBieMing(Object bieMing) {
                this.bieMing = bieMing;
            }

            public List<CengListBean> getCengList() {
                return cengList;
            }

            public void setCengList(List<CengListBean> cengList) {
                this.cengList = cengList;
            }

            public static class CengListBean {
                /**
                 * cengShu : 1
                 * fjList : [{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-1","hubm":"1号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-2","hubm":"2号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-3","hubm":"3号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-4","hubm":"4号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-5","hubm":"5号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-6","hubm":"6号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-7","hubm":"7号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-8","hubm":"8号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-9","hubm":"9号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null},{"huid":"4CBAA1DE2287E4B3E0536902080AA554-1-1-10","hubm":"10号","renshu_ldrk":0,"renshu_rhfl":0,"renshu_hjrk":0,"fz_fwjzlx":null}]
                 */

                private int cengShu;
                private List<FjListBean> fjList;

                public int getCengShu() {
                    return cengShu;
                }

                public void setCengShu(int cengShu) {
                    this.cengShu = cengShu;
                }

                public List<FjListBean> getFjList() {
                    return fjList;
                }

                public void setFjList(List<FjListBean> fjList) {
                    this.fjList = fjList;
                }

                public static class FjListBean {
                    /**
                     * huid : 4CBAA1DE2287E4B3E0536902080AA554-1-1-1
                     * hubm : 1号
                     * renshu_ldrk : 0
                     * renshu_rhfl : 0
                     * renshu_hjrk : 0
                     * fz_fwjzlx : null
                     */

                    private String huid;
                    private String hubm;
                    private int renshu_ldrk;
                    private int renshu_rhfl;
                    private int renshu_hjrk;
                    private Object fz_fwjzlx;

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

                    public Object getFz_fwjzlx() {
                        return fz_fwjzlx;
                    }

                    public void setFz_fwjzlx(Object fz_fwjzlx) {
                        this.fz_fwjzlx = fz_fwjzlx;
                    }
                }
            }
        }
    }
}
