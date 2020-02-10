package com.example.ruifight_3.saolouruifight.ui.bean;


/**
 * Created by RuiFight-3 on 2018/4/16.
 */

public class LoginBean {


    /**
     * status : true
     * msg : {"appVersion":38,"level":"5","versionComment":" ","appTimeout":"2019-12-31"}
     * data : 693F11A120C5C3804D1CA21CAD6CC3F3
     */

    private boolean status;
    private MsgBean msg;
    private String data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public static class MsgBean {
        /**
         * appVersion : 38
         * level : 5
         * versionComment :
         * appTimeout : 2019-12-31
         */

        private int appVersion;
        private String level;
        private String versionComment;
        private String appTimeout;

        public int getAppVersion() {
            return appVersion;
        }

        public void setAppVersion(int appVersion) {
            this.appVersion = appVersion;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getVersionComment() {
            return versionComment;
        }

        public void setVersionComment(String versionComment) {
            this.versionComment = versionComment;
        }

        public String getAppTimeout() {
            return appTimeout;
        }

        public void setAppTimeout(String appTimeout) {
            this.appTimeout = appTimeout;
        }
    }
}
