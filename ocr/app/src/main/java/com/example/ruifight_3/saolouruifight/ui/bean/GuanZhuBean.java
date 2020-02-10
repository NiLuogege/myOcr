package com.example.ruifight_3.saolouruifight.ui.bean;

import java.util.List;

/**
 * Created by RuiFight-3 on 2018/5/12.
 */

public class GuanZhuBean {


    /**
     * status : false
     * msg : null
     * data : [{"id":1,"huId":"4CBAA1E1120EE4B3E0536902080AA554-1-1-2","jzwDm":"4CBAA1E1120EE4B3E0536902080AA554","createDate":1531275291000,"foundUserId":3,"foundUserDepartmentCode":null,"foundUserName":null,"jzwDizhi":"苏家坨镇后沙涧第一小区23号112","grade":null,"execUserId":2,"execUserDepartmentCode":null,"execUserName":"3333","content":null,"img":null,"overDate":null,"state":1,"markCause":"思思思思"},{"id":2,"huId":"4CBAA1E1120EE4B3E0536902080AA554-1-1-4","jzwDm":"4CBAA1E1120EE4B3E0536902080AA554","createDate":1531275301000,"foundUserId":3,"foundUserDepartmentCode":null,"foundUserName":null,"jzwDizhi":"苏家坨镇后沙涧第一小区23号114","grade":null,"execUserId":2,"execUserDepartmentCode":null,"execUserName":"3333","content":null,"img":null,"overDate":null,"state":1,"markCause":"啊啊啊啊啊"},{"id":3,"huId":"4CBAA1E1120EE4B3E0536902080AA554-1-1-8","jzwDm":"4CBAA1E1120EE4B3E0536902080AA554","createDate":1531275309000,"foundUserId":3,"foundUserDepartmentCode":null,"foundUserName":null,"jzwDizhi":"苏家坨镇后沙涧第一小区23号118","grade":null,"execUserId":2,"execUserDepartmentCode":null,"execUserName":"3333","content":null,"img":null,"overDate":null,"state":1,"markCause":"阿萨群无多群无"}]
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
         * huId : 4CBAA1E1120EE4B3E0536902080AA554-1-1-2
         * jzwDm : 4CBAA1E1120EE4B3E0536902080AA554
         * createDate : 1531275291000
         * foundUserId : 3
         * foundUserDepartmentCode : null
         * foundUserName : null
         * jzwDizhi : 苏家坨镇后沙涧第一小区23号112
         * grade : null
         * execUserId : 2
         * execUserDepartmentCode : null
         * execUserName : 3333
         * content : null
         * img : null
         * overDate : null
         * state : 1
         * markCause : 思思思思
         */

        private int id;
        private String huId;
        private String jzwDm;
        private long createDate;
        private int foundUserId;
        private Object foundUserDepartmentCode;
        private Object foundUserName;
        private String jzwDizhi;
        private Object grade;
        private int execUserId;
        private Object execUserDepartmentCode;
        private String execUserName;
        private Object content;
        private Object img;
        private Object overDate;
        private int state;
        private String markCause;

        public DataBean() {
            super();
        }

        public DataBean(int id, String huId, String jzwDm, long createDate, int foundUserId, Object foundUserDepartmentCode, Object foundUserName, String jzwDizhi, Object grade, int execUserId, Object execUserDepartmentCode, String execUserName, Object content, Object img, Object overDate, int state, String markCause) {
            this.id = id;
            this.huId = huId;
            this.jzwDm = jzwDm;
            this.createDate = createDate;
            this.foundUserId = foundUserId;
            this.foundUserDepartmentCode = foundUserDepartmentCode;
            this.foundUserName = foundUserName;
            this.jzwDizhi = jzwDizhi;
            this.grade = grade;
            this.execUserId = execUserId;
            this.execUserDepartmentCode = execUserDepartmentCode;
            this.execUserName = execUserName;
            this.content = content;
            this.img = img;
            this.overDate = overDate;
            this.state = state;
            this.markCause = markCause;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getHuId() {
            return huId;
        }

        public void setHuId(String huId) {
            this.huId = huId;
        }

        public String getJzwDm() {
            return jzwDm;
        }

        public void setJzwDm(String jzwDm) {
            this.jzwDm = jzwDm;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public int getFoundUserId() {
            return foundUserId;
        }

        public void setFoundUserId(int foundUserId) {
            this.foundUserId = foundUserId;
        }

        public Object getFoundUserDepartmentCode() {
            return foundUserDepartmentCode;
        }

        public void setFoundUserDepartmentCode(Object foundUserDepartmentCode) {
            this.foundUserDepartmentCode = foundUserDepartmentCode;
        }

        public Object getFoundUserName() {
            return foundUserName;
        }

        public void setFoundUserName(Object foundUserName) {
            this.foundUserName = foundUserName;
        }

        public String getJzwDizhi() {
            return jzwDizhi;
        }

        public void setJzwDizhi(String jzwDizhi) {
            this.jzwDizhi = jzwDizhi;
        }

        public Object getGrade() {
            return grade;
        }

        public void setGrade(Object grade) {
            this.grade = grade;
        }

        public int getExecUserId() {
            return execUserId;
        }

        public void setExecUserId(int execUserId) {
            this.execUserId = execUserId;
        }

        public Object getExecUserDepartmentCode() {
            return execUserDepartmentCode;
        }

        public void setExecUserDepartmentCode(Object execUserDepartmentCode) {
            this.execUserDepartmentCode = execUserDepartmentCode;
        }

        public String getExecUserName() {
            return execUserName;
        }

        public void setExecUserName(String execUserName) {
            this.execUserName = execUserName;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public Object getImg() {
            return img;
        }

        public void setImg(Object img) {
            this.img = img;
        }

        public Object getOverDate() {
            return overDate;
        }

        public void setOverDate(Object overDate) {
            this.overDate = overDate;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getMarkCause() {
            return markCause;
        }

        public void setMarkCause(String markCause) {
            this.markCause = markCause;
        }
    }
}
