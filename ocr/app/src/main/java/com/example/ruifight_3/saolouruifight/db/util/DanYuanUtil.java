package com.example.ruifight_3.saolouruifight.db.util;

import android.util.Log;

import com.example.ruifight_3.saolouruifight.db.util.bean.jzw_bean;
import com.example.ruifight_3.saolouruifight.db.util.bean.t_jzw_fangjian;
import com.example.ruifight_3.saolouruifight.db.util.bean.view_ceng;
import com.example.ruifight_3.saolouruifight.db.util.bean.view_danYuan;
import com.example.ruifight_3.saolouruifight.db.util.bean.view_fangJian;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RuiFight-3 on 2018/7/13.
 * 张振 合表工具类
 */

public  class DanYuanUtil {

    public view_jzw gzchjg(jzw_bean jzw, List<t_jzw_fangjian> fjInfoList) {
        if (jzw == null || fjInfoList == null || fjInfoList.size() == 0) {
            //logger.error("in gzchjg,传入的参数为空");
            return null;
        }

        // 组合建筑物结构信息
        view_jzw jview = new view_jzw();
        jview.setJzw(jzw);// 关联建筑物信息
        jview.setFangwu_total(fjInfoList.size());//房间总数
        int iFj_caiji = 0;
        int iFj_chuzu = 0;
        int iRs_ldrk = 0;
        int iRs_hjrk = 0;
        int iRs_flrk = 0;

		/*
         * 计算层户结构 order by cs,dys,hs 现处理地下部分，逐个单元，再处理地上部分，逐个单元
		 */

        jview.setDzbm(jzw.getJzw_bm());
        List<view_danYuan> ds_dyList = new ArrayList<view_danYuan>();
        List<view_danYuan> dx_dyList = new ArrayList<view_danYuan>();
        List<view_ceng> cengList = new ArrayList<view_ceng>();
        List<view_fangJian> tmpFjList = new ArrayList<view_fangJian>();

        Integer old_dyh = null, old_cs = null;
        String old_dymc = "";

        int iSize = fjInfoList.size();
        for (int i = 0; i < iSize; i++) {
            t_jzw_fangjian fj = fjInfoList.get(i);
            //统计信息
            if (fj.getFz_fwjzlx() != null && !"".equals(fj.getFz_fwjzlx())) {
                iFj_caiji++;
                if ("出租房屋".equals(fj.getFz_fwjzlx())) {
                    iFj_chuzu++;
                }
            }
            iRs_hjrk = iRs_hjrk + fj.getRenshu_hjrk();//人数累加
            iRs_ldrk = iRs_ldrk + fj.getRenshu_ldrk();
            iRs_flrk = iRs_flrk + fj.getRenshu_rhfl();

            if (i == 0) {
                old_dyh = fj.getDys();
                old_cs = fj.getCs();
                old_dymc = fj.getDymc();

                view_fangJian tmpFj = new view_fangJian();
                tmpFj.setHuid(fj.getHu_id());
                tmpFj.setHubm(fj.getHumc());
                tmpFj.setRenshu_ldrk(fj.getRenshu_ldrk());
                tmpFj.setRenshu_hjrk(fj.getRenshu_hjrk());
                tmpFj.setRenshu_rhfl(fj.getRenshu_rhfl());
                tmpFj.setFz_fwjzlx(fj.getFz_fwjzlx());
                tmpFjList.add(tmpFj);

            } else {
                if (!old_cs.equals(fj.getCs()) || !old_dyh.equals(fj.getDys())) {
                    // 存层结构，开始下一个层。如果单元变化了，即使层没有变化也要存储
                    view_ceng ceng = new view_ceng();
                    ceng.setCengShu(old_cs);
                    ceng.setFjList(tmpFjList);
                    cengList.add(ceng);

                    tmpFjList = new ArrayList<view_fangJian>();
                }

                if (!old_dyh.equals(fj.getDys())
                        || (old_cs > 0 && fj.getCs() < 0)) {
                    // 存单元结构，开始下一个单元。如果出现地上与地下过渡，也要存单元结构
                    view_danYuan dy = new view_danYuan();
                    dy.setDys(old_dyh);
                    dy.setBieMing(old_dymc);
                    dy.setCengList(cengList);
                    if (old_cs > 0) {
                        Log.e("old_cs","ssssssss。。。。。"+old_cs);
                        ds_dyList.add(dy);
                    } else {
                        dx_dyList.add(dy);
                    }

                    cengList = new ArrayList<view_ceng>();
                }

                // 保存后，继续处理当前的
                old_dyh = fj.getDys();
                old_cs = fj.getCs();
                old_dymc = fj.getDymc();

                view_fangJian tmpFj = new view_fangJian();
                tmpFj.setHuid(fj.getHu_id());
                tmpFj.setHubm(fj.getHumc());
                tmpFj.setRenshu_ldrk(fj.getRenshu_ldrk());
                tmpFj.setRenshu_hjrk(fj.getRenshu_hjrk());
                tmpFj.setRenshu_rhfl(fj.getRenshu_rhfl());
                tmpFj.setFz_fwjzlx(fj.getFz_fwjzlx());
                tmpFjList.add(tmpFj);
            }

        }
        //统计数据保存
        jview.setFangwu_caiji(iFj_caiji);
        jview.setFangwu_chuzu(iFj_chuzu);
        jview.setRenshu_hjrk(iRs_hjrk);
        jview.setRenshu_ldrk(iRs_ldrk);
        jview.setRenshu_rhfl(iRs_flrk);

        // 最后一条记录的保存
        view_ceng ceng = new view_ceng();
        ceng.setCengShu(old_cs);
        ceng.setFjList(tmpFjList);
        cengList.add(ceng);

        view_danYuan dy = new view_danYuan();
        dy.setDys(old_dyh);
        dy.setBieMing(old_dymc);
        dy.setCengList(cengList);
        if (old_cs > 0) {
            ds_dyList.add(dy);
        } else {
            dx_dyList.add(dy);
        }
        jview.setDx_dy_list(dx_dyList);
        jview.setDs_dy_list(ds_dyList);

        return jview;
    }

    public static class view_jzw implements Serializable{

        private String dzbm;//地址编码，建筑物的唯一标识
        private jzw_bean jzw;//建筑物的信息
        private List<view_danYuan> ds_dy_list;//地上单元列表
        private List<view_danYuan> dx_dy_list;//地下单元列表

        private int renshu_ldrk;//居住人数，流动人口
        private int renshu_hjrk;//户籍人口
        private int renshu_rhfl;//人户分离人口

        private int fangwu_total;//房屋总数
        private int fangwu_caiji;//采集房主的数量
        private int fangwu_chuzu;//采集出租房屋


        public String getDzbm() {
            return dzbm;
        }

        public void setDzbm(String dzbm) {
            this.dzbm = dzbm;
        }

        public jzw_bean getJzw() {
            return jzw;
        }

        public void setJzw(jzw_bean jzw) {
            this.jzw = jzw;
        }

        public List<view_danYuan> getDs_dy_list() {
            return ds_dy_list;
        }

        public void setDs_dy_list(List<view_danYuan> ds_dy_list) {
            this.ds_dy_list = ds_dy_list;
        }

        public List<view_danYuan> getDx_dy_list() {
            return dx_dy_list;
        }

        public void setDx_dy_list(List<view_danYuan> dx_dy_list) {
            this.dx_dy_list = dx_dy_list;
        }

        public int getRenshu_ldrk() {
            return renshu_ldrk;
        }

        public void setRenshu_ldrk(int renshu_ldrk) {
            this.renshu_ldrk = renshu_ldrk;
        }

        public int getRenshu_hjrk() {
            return renshu_hjrk;
        }

        public void setRenshu_hjrk(int renshu_hjrk) {
            this.renshu_hjrk = renshu_hjrk;
        }

        public int getRenshu_rhfl() {
            return renshu_rhfl;
        }

        public void setRenshu_rhfl(int renshu_rhfl) {
            this.renshu_rhfl = renshu_rhfl;
        }

        public int getFangwu_total() {
            return fangwu_total;
        }

        public void setFangwu_total(int fangwu_total) {
            this.fangwu_total = fangwu_total;
        }

        public int getFangwu_caiji() {
            return fangwu_caiji;
        }

        public void setFangwu_caiji(int fangwu_caiji) {
            this.fangwu_caiji = fangwu_caiji;
        }

        public int getFangwu_chuzu() {
            return fangwu_chuzu;
        }

        public void setFangwu_chuzu(int fangwu_chuzu) {
            this.fangwu_chuzu = fangwu_chuzu;
        }

        @Override
        public String toString() {
            return "view_jzw{" +
                    "dzbm='" + dzbm + '\'' +
                    ", jzw=" + jzw +
                    ", ds_dy_list=" + ds_dy_list +
                    ", dx_dy_list=" + dx_dy_list +
                    ", renshu_ldrk=" + renshu_ldrk +
                    ", renshu_hjrk=" + renshu_hjrk +
                    ", renshu_rhfl=" + renshu_rhfl +
                    ", fangwu_total=" + fangwu_total +
                    ", fangwu_caiji=" + fangwu_caiji +
                    ", fangwu_chuzu=" + fangwu_chuzu +
                    '}';
        }
    }
}