package com.example.ruifight_3.saolouruifight.ui.bean;

/**
 * Created by RuiFight-3 on 2018/5/17.
 */

public class AddUserBean {


    /**
     * status : true
     * msg : 添加成功
     * data : null
     */

    private boolean status;
    private String msg;
    private Object data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
