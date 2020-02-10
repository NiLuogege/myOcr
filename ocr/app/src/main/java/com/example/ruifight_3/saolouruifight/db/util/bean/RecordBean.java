package com.example.ruifight_3.saolouruifight.db.util.bean;

/**
 * Created by RuiFight-3 on 2018/7/18.
 */

public class RecordBean {
    private int id;
    private String type; // 增删改查类型  英文单词
    private String statement; //SQL 语句
    private String operateDate;     //时间
    private String operateUser; // 操作人
    private String tableName; // 表明

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(String operateDate) {
        this.operateDate = operateDate;
    }

    public String getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
