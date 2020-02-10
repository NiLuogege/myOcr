package com.example.ruifight_3.saolouruifight;


import android.os.Environment;

import okhttp3.MediaType;

/**
 * Created by RuiFight-3 on 2018/4/16.
 */

public final class MyApi {

    //公共url
    // 线上  http://115.47.147.131:8081
    // 域名http://lg.bdcarlife.com
    // 本地http://192.168.43.8:8081


    public static final String URL = "http://lg.bdcarlife.com";


    public static final MediaType CONTENT_TYPE = MediaType.parse("application/json; charset=utf-8");
    //数据库文件地址
    public static final String FileLoad = "/data"
            + Environment.getDataDirectory().getAbsolutePath() + "/"
            + "com.example.ruifight_3.saolouruifight" + "/databases/";
    //数据库名称
    public static String DBNAME = "";
    //查看大图静态变量带值
    public static String DAIMAGE = "";
    //是否是房主登录 0房主
    public static String ISHOMELOGIN = "";


    //APK更新地址
    public static final String APKUPDATE = "http://lg.bdcarlife.com:88/BJxichengqu_3.0.apk";
    //apk下载存储地址
    public static final String APLPATH = Environment.getExternalStorageDirectory() + "/ruifight/" + "BJxichengqu_3.0" + ".apk";
    /**
     * 数据库操作上传type 常量
     */
    public static final String INSERT = "insert";
    public static final String DELETE = "delete";
    public static final String UPDATE = "update";
    public static final String SELECT = "select";

    /**
     * 图片地址
     */
    public static final String IMAGEURL = "/mobile/houseMark/getImg" + "?" + "imgName=";


    /**
     * 登录
     */
    public static final String LOGIN = "/mobile/login";

    /**
     * 首页数据
     */
    public static final String HOMEDATA = "/mobile/home";

    /**
     * 辖区统计数据
     */
    public static final String XIAQU = "/mobile/getStatistics";

    /**
     * 人员搜索
     */
    public static final String SOUSUO = "/mobile/queryRyxx";


    /**
     * 修改密码
     */
    public static final String UPDATEPASSWORD = "/mobile/user/updatePassword";

    /**
     * 街路巷列表
     */
    public static final String GETADDRESS = "/mobile/area/";

    /**
     * 楼列表
     */
    public static final String ADDRESSMESSAGE = "/mobile/area/jlx/";

    /**
     * 获取号楼及门牌列表  (层户图)
     */
    public static final String UNFINISH = "/mobile/area/jzw/";

    /**
     * 获取房屋信息
     */
    public static final String GETFANGWU_MESSAGE = "/mobile/collect/listByHuid/";

    /**
     * 添加房主
     */
    public static final String ADDFANGZHU = "/mobile/collect/fzxx_save";

    /**
     * 房主信息回显
     */
    public static final String UPDATEFANGZHU = "/mobile/collect/fzxx_update";

    /**
     * 添加住户信息
     */
    public static final String ADDZHUHU = "/mobile/collect/addUserInfo";

    /**
     * 住户信息回显
     */
    public static final String UPDATEZHUHU = "/mobile/collect/editUser/";

    /**
     * 删除住户信息
     */
    public static final String DELETEZUHU = "/mobile/collect/delUserInfo";


    /**
     * 关注任务列表
     */
    public static final String GUANZHURENWU = "/mobile/houseMark/getFocusTaskList";


    /**
     * 处理关注任务
     */
    public static final String CHULI = "/mobile/houseMark/replyFocusTask";

    /**
     * 已完成任务详情查看
     */
    public static final String CHAKAN = "/mobile/houseMark/getHouseMark/";


    /**
     * 离线下载文件DB
     */
    public static final String DOWNLOAD = "/mobile/download/downloadDB";

    /**
     * 上传json
     */
    public static final String UPDA = "/mobile/uploadOfflineData/uploadDB";

    /**
     * 身份证照片提取
     */
    public static final String GEIIDCARD = "/mobile/spot/discernImg";


    /**
     * 删除单元
     */
    public static final String DELETEDY = "/mobile/jch/delUnit/";

    /**
     * 添加单元
     */
    public static final String ADDDY = "/mobile/jch/addUnit/";

    /**
     * 修改单元别名
     */
    public static final String UPDATEBIENAME = "/mobile/jch/editDybm/";


    /**
     * 修改单元层数
     */
    public static final String UPDATECENGNUM = "/mobile/jch/saveDycs/";


    /**
     * 修改层数
     */
    public static final String UPDATECENG = "/mobile/jch/editCs/";


    /**
     * 添加户数
     */
    public static final String UPDATEHU = "/mobile/jch/saveHs/";

    /**
     * 删除层
     */
    public static final String DELETECENG = "/mobile/jch/delDycs/";


    /**
     * 删除房间 huid
     */
    public static final String DELETEFANGWU = "/mobile/jch/deleteHu/";

    /**
     * 修改房间号huid
     */
    public static final String UPDATEHUID = "/mobile/jch/editHubm/";


    /**
     * 添加建筑物
     */
    public static final String ADDJZW = "/mobile/jch/addJzw";


    /**
     * 修改建筑物
     */
    public static final String UPDATEJZW = "/mobile/jch/updateJzw_mc/";

    /**
     * 删除记录
     */
    public static final String DELETEJILU = "/mobile/collect/getDelUserList";
}
