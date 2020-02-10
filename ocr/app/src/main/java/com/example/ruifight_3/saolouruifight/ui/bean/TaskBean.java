package com.example.ruifight_3.saolouruifight.ui.bean;

/**
 * Created by RuiFight-3 on 2018/5/15.
 */

public class TaskBean {


    /**
     * status : true
     * msg : null
     * data : {"id":1,"huId":"4CBAA1DE4CD5E4B3E0536902080AA554-1-1-2","jzwDm":"4CBAA1DE4CD5E4B3E0536902080AA554","createDate":1531290847000,"foundUserId":3,"foundUserDepartmentCode":null,"foundUserName":null,"jzwDizhi":"苏家坨镇后沙涧第一小区21号112","grade":null,"execUserId":2,"execUserDepartmentCode":null,"execUserName":"3333","content":"嗯嗯","img":"null","overDate":1531298239000,"state":2,"markCause":"看看啊\r\n","jlxDm":"4D83F3EEDEB4A35FE0536902080A8882"}
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
         * id : 1
         * huId : 4CBAA1DE4CD5E4B3E0536902080AA554-1-1-2
         * jzwDm : 4CBAA1DE4CD5E4B3E0536902080AA554
         * createDate : 1531290847000
         * foundUserId : 3
         * foundUserDepartmentCode : null
         * foundUserName : null
         * jzwDizhi : 苏家坨镇后沙涧第一小区21号112
         * grade : null
         * execUserId : 2
         * execUserDepartmentCode : null
         * execUserName : 3333
         * content : 嗯嗯
         * img : null
         * overDate : 1531298239000
         * state : 2
         * markCause : 看看啊

         * jlxDm : 4D83F3EEDEB4A35FE0536902080A8882
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
        private String content;
        private String img;
        private long overDate;
        private int state;
        private String markCause;
        private String jlxDm;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public long getOverDate() {
            return overDate;
        }

        public void setOverDate(long overDate) {
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

        public String getJlxDm() {
            return jlxDm;
        }

        public void setJlxDm(String jlxDm) {
            this.jlxDm = jlxDm;
        }
    }
}
