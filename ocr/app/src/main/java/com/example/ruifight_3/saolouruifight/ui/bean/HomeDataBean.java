package com.example.ruifight_3.saolouruifight.ui.bean;


import java.util.List;

/**
 * Created by RuiFight-3 on 2018/7/4.
 */

public class HomeDataBean {


    /**
     * status : true
     * msg : null
     * data : {"rynums":[0,0,0,0,0,0,0],"dates":["2019.04.22","2019.04.23","2019.04.24","2019.04.25","2019.04.26","2019.04.27","2019.04.28"],"user":{"userid":19,"name":"chunshuyuan","pass":null,"ip":null,"loginTime":null,"jg_dm":"1101040403007","jg_mc":"椿树园社区居委会","zzjg_mz":{"id":390,"zzjg_dm":"1101040403007","zzjg_mc":"椿树园社区居委会","parent_dm":"110102015000","path":"110100000000,110102000000,110102015000,","zzjg_type":"mz","zzjg_level":"7","is_del":"F","duizhao_dm":null,"duizhao_mc":null,"zzjg_mz_list":[],"duizhao_dm_list":[],"duizhao_mc_list":[]},"type":"mz","level":"5","realname":null,"path":null,"jwh_Dms":null}}
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
         * rynums : [0,0,0,0,0,0,0]
         * dates : ["2019.04.22","2019.04.23","2019.04.24","2019.04.25","2019.04.26","2019.04.27","2019.04.28"]
         * user : {"userid":19,"name":"chunshuyuan","pass":null,"ip":null,"loginTime":null,"jg_dm":"1101040403007","jg_mc":"椿树园社区居委会","zzjg_mz":{"id":390,"zzjg_dm":"1101040403007","zzjg_mc":"椿树园社区居委会","parent_dm":"110102015000","path":"110100000000,110102000000,110102015000,","zzjg_type":"mz","zzjg_level":"7","is_del":"F","duizhao_dm":null,"duizhao_mc":null,"zzjg_mz_list":[],"duizhao_dm_list":[],"duizhao_mc_list":[]},"type":"mz","level":"5","realname":null,"path":null,"jwh_Dms":null}
         */

        private UserBean user;
        private List<Integer> rynums;
        private List<String> dates;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public List<Integer> getRynums() {
            return rynums;
        }

        public void setRynums(List<Integer> rynums) {
            this.rynums = rynums;
        }

        public List<String> getDates() {
            return dates;
        }

        public void setDates(List<String> dates) {
            this.dates = dates;
        }

        public static class UserBean {
            /**
             * userid : 19
             * name : chunshuyuan
             * pass : null
             * ip : null
             * loginTime : null
             * jg_dm : 1101040403007
             * jg_mc : 椿树园社区居委会
             * zzjg_mz : {"id":390,"zzjg_dm":"1101040403007","zzjg_mc":"椿树园社区居委会","parent_dm":"110102015000","path":"110100000000,110102000000,110102015000,","zzjg_type":"mz","zzjg_level":"7","is_del":"F","duizhao_dm":null,"duizhao_mc":null,"zzjg_mz_list":[],"duizhao_dm_list":[],"duizhao_mc_list":[]}
             * type : mz
             * level : 5
             * realname : null
             * path : null
             * jwh_Dms : null
             */

            private int userid;
            private String name;
            private Object pass;
            private Object ip;
            private Object loginTime;
            private String jg_dm;
            private String jg_mc;
            private ZzjgMzBean zzjg_mz;
            private String type;
            private String level;
            private Object realname;
            private Object path;
            private Object jwh_Dms;

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getPass() {
                return pass;
            }

            public void setPass(Object pass) {
                this.pass = pass;
            }

            public Object getIp() {
                return ip;
            }

            public void setIp(Object ip) {
                this.ip = ip;
            }

            public Object getLoginTime() {
                return loginTime;
            }

            public void setLoginTime(Object loginTime) {
                this.loginTime = loginTime;
            }

            public String getJg_dm() {
                return jg_dm;
            }

            public void setJg_dm(String jg_dm) {
                this.jg_dm = jg_dm;
            }

            public String getJg_mc() {
                return jg_mc;
            }

            public void setJg_mc(String jg_mc) {
                this.jg_mc = jg_mc;
            }

            public ZzjgMzBean getZzjg_mz() {
                return zzjg_mz;
            }

            public void setZzjg_mz(ZzjgMzBean zzjg_mz) {
                this.zzjg_mz = zzjg_mz;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public Object getRealname() {
                return realname;
            }

            public void setRealname(Object realname) {
                this.realname = realname;
            }

            public Object getPath() {
                return path;
            }

            public void setPath(Object path) {
                this.path = path;
            }

            public Object getJwh_Dms() {
                return jwh_Dms;
            }

            public void setJwh_Dms(Object jwh_Dms) {
                this.jwh_Dms = jwh_Dms;
            }

            public static class ZzjgMzBean {
                /**
                 * id : 390
                 * zzjg_dm : 1101040403007
                 * zzjg_mc : 椿树园社区居委会
                 * parent_dm : 110102015000
                 * path : 110100000000,110102000000,110102015000,
                 * zzjg_type : mz
                 * zzjg_level : 7
                 * is_del : F
                 * duizhao_dm : null
                 * duizhao_mc : null
                 * zzjg_mz_list : []
                 * duizhao_dm_list : []
                 * duizhao_mc_list : []
                 */

                private int id;
                private String zzjg_dm;
                private String zzjg_mc;
                private String parent_dm;
                private String path;
                private String zzjg_type;
                private String zzjg_level;
                private String is_del;
                private Object duizhao_dm;
                private Object duizhao_mc;
                private List<?> zzjg_mz_list;
                private List<?> duizhao_dm_list;
                private List<?> duizhao_mc_list;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getZzjg_dm() {
                    return zzjg_dm;
                }

                public void setZzjg_dm(String zzjg_dm) {
                    this.zzjg_dm = zzjg_dm;
                }

                public String getZzjg_mc() {
                    return zzjg_mc;
                }

                public void setZzjg_mc(String zzjg_mc) {
                    this.zzjg_mc = zzjg_mc;
                }

                public String getParent_dm() {
                    return parent_dm;
                }

                public void setParent_dm(String parent_dm) {
                    this.parent_dm = parent_dm;
                }

                public String getPath() {
                    return path;
                }

                public void setPath(String path) {
                    this.path = path;
                }

                public String getZzjg_type() {
                    return zzjg_type;
                }

                public void setZzjg_type(String zzjg_type) {
                    this.zzjg_type = zzjg_type;
                }

                public String getZzjg_level() {
                    return zzjg_level;
                }

                public void setZzjg_level(String zzjg_level) {
                    this.zzjg_level = zzjg_level;
                }

                public String getIs_del() {
                    return is_del;
                }

                public void setIs_del(String is_del) {
                    this.is_del = is_del;
                }

                public Object getDuizhao_dm() {
                    return duizhao_dm;
                }

                public void setDuizhao_dm(Object duizhao_dm) {
                    this.duizhao_dm = duizhao_dm;
                }

                public Object getDuizhao_mc() {
                    return duizhao_mc;
                }

                public void setDuizhao_mc(Object duizhao_mc) {
                    this.duizhao_mc = duizhao_mc;
                }

                public List<?> getZzjg_mz_list() {
                    return zzjg_mz_list;
                }

                public void setZzjg_mz_list(List<?> zzjg_mz_list) {
                    this.zzjg_mz_list = zzjg_mz_list;
                }

                public List<?> getDuizhao_dm_list() {
                    return duizhao_dm_list;
                }

                public void setDuizhao_dm_list(List<?> duizhao_dm_list) {
                    this.duizhao_dm_list = duizhao_dm_list;
                }

                public List<?> getDuizhao_mc_list() {
                    return duizhao_mc_list;
                }

                public void setDuizhao_mc_list(List<?> duizhao_mc_list) {
                    this.duizhao_mc_list = duizhao_mc_list;
                }
            }
        }
    }
}
