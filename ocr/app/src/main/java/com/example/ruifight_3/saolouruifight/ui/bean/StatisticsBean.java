package com.example.ruifight_3.saolouruifight.ui.bean;

/**
 * Created by RuiFight-3 on 2019/4/28.
 * Creator: 张震 (Android  zhangzhen)
 * Describe:
 */
public class StatisticsBean {

    /**
     * status : true
     * msg : null
     * data : {"fangwu_qita":2,"jzwTotal":19,"userTotal":158,"renshu_rhfl":0,"fangwu_xianzhi":1,"fangwu_jiti":2,"fzTotal":80,"renshu_hjrk":3,"fangwu_chuzu":23,"fangwu_caiji":2764,"renshu_ldrk":155,"fangwu_zizhu":50,"fangwu_chujie":2}
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
         * fangwu_qita : 2
         * jzwTotal : 19
         * userTotal : 158
         * renshu_rhfl : 0
         * fangwu_xianzhi : 1
         * fangwu_jiti : 2
         * fzTotal : 80
         * renshu_hjrk : 3
         * fangwu_chuzu : 23
         * fangwu_caiji : 2764
         * renshu_ldrk : 155
         * fangwu_zizhu : 50
         * fangwu_chujie : 2
         */

        private int fangwu_qita;
        private int jzwTotal;
        private int userTotal;
        private int renshu_rhfl;
        private int fangwu_xianzhi;
        private int fangwu_jiti;
        private int fzTotal;
        private int renshu_hjrk;
        private int fangwu_chuzu;
        private int fangwu_caiji;
        private int renshu_ldrk;
        private int fangwu_zizhu;
        private int fangwu_chujie;

        public int getFangwu_qita() {
            return fangwu_qita;
        }

        public void setFangwu_qita(int fangwu_qita) {
            this.fangwu_qita = fangwu_qita;
        }

        public int getJzwTotal() {
            return jzwTotal;
        }

        public void setJzwTotal(int jzwTotal) {
            this.jzwTotal = jzwTotal;
        }

        public int getUserTotal() {
            return userTotal;
        }

        public void setUserTotal(int userTotal) {
            this.userTotal = userTotal;
        }

        public int getRenshu_rhfl() {
            return renshu_rhfl;
        }

        public void setRenshu_rhfl(int renshu_rhfl) {
            this.renshu_rhfl = renshu_rhfl;
        }

        public int getFangwu_xianzhi() {
            return fangwu_xianzhi;
        }

        public void setFangwu_xianzhi(int fangwu_xianzhi) {
            this.fangwu_xianzhi = fangwu_xianzhi;
        }

        public int getFangwu_jiti() {
            return fangwu_jiti;
        }

        public void setFangwu_jiti(int fangwu_jiti) {
            this.fangwu_jiti = fangwu_jiti;
        }

        public int getFzTotal() {
            return fzTotal;
        }

        public void setFzTotal(int fzTotal) {
            this.fzTotal = fzTotal;
        }

        public int getRenshu_hjrk() {
            return renshu_hjrk;
        }

        public void setRenshu_hjrk(int renshu_hjrk) {
            this.renshu_hjrk = renshu_hjrk;
        }

        public int getFangwu_chuzu() {
            return fangwu_chuzu;
        }

        public void setFangwu_chuzu(int fangwu_chuzu) {
            this.fangwu_chuzu = fangwu_chuzu;
        }

        public int getFangwu_caiji() {
            return fangwu_caiji;
        }

        public void setFangwu_caiji(int fangwu_caiji) {
            this.fangwu_caiji = fangwu_caiji;
        }

        public int getRenshu_ldrk() {
            return renshu_ldrk;
        }

        public void setRenshu_ldrk(int renshu_ldrk) {
            this.renshu_ldrk = renshu_ldrk;
        }

        public int getFangwu_zizhu() {
            return fangwu_zizhu;
        }

        public void setFangwu_zizhu(int fangwu_zizhu) {
            this.fangwu_zizhu = fangwu_zizhu;
        }

        public int getFangwu_chujie() {
            return fangwu_chujie;
        }

        public void setFangwu_chujie(int fangwu_chujie) {
            this.fangwu_chujie = fangwu_chujie;
        }
    }
}
