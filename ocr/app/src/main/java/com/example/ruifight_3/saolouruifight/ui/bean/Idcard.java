package com.example.ruifight_3.saolouruifight.ui.bean;

/**
 * Created by RuiFight-3 on 2018/7/23.
 */

public class Idcard {


    /**
     * Name : {"value":"任皓楠"}
     * Sex : {"value":"男"}
     * Folk : {"value":"汉"}
     * Birt : {"value":"1996年09月17日"}
     * Addr : {"value":"河北省保定市定兴县杨村乡杨村二十三区13号"}
     * Num : {"value":"130626199609177436"}
     * Issue : {"value":""}
     * Valid : {"value":""}
     * Type : {"value":"正面"}
     * Cover : {"value":"无遮挡"}
     */

    private NameBean Name;
    private SexBean Sex;
    private FolkBean Folk;
    private BirtBean Birt;
    private AddrBean Addr;
    private NumBean Num;
    private IssueBean Issue;
    private ValidBean Valid;
    private TypeBean Type;
    private CoverBean Cover;

    public NameBean getName() {
        return Name;
    }

    public void setName(NameBean Name) {
        this.Name = Name;
    }

    public SexBean getSex() {
        return Sex;
    }

    public void setSex(SexBean Sex) {
        this.Sex = Sex;
    }

    public FolkBean getFolk() {
        return Folk;
    }

    public void setFolk(FolkBean Folk) {
        this.Folk = Folk;
    }

    public BirtBean getBirt() {
        return Birt;
    }

    public void setBirt(BirtBean Birt) {
        this.Birt = Birt;
    }

    public AddrBean getAddr() {
        return Addr;
    }

    public void setAddr(AddrBean Addr) {
        this.Addr = Addr;
    }

    public NumBean getNum() {
        return Num;
    }

    public void setNum(NumBean Num) {
        this.Num = Num;
    }

    public IssueBean getIssue() {
        return Issue;
    }

    public void setIssue(IssueBean Issue) {
        this.Issue = Issue;
    }

    public ValidBean getValid() {
        return Valid;
    }

    public void setValid(ValidBean Valid) {
        this.Valid = Valid;
    }

    public TypeBean getType() {
        return Type;
    }

    public void setType(TypeBean Type) {
        this.Type = Type;
    }

    public CoverBean getCover() {
        return Cover;
    }

    public void setCover(CoverBean Cover) {
        this.Cover = Cover;
    }

    public static class NameBean {
        /**
         * value : 任皓楠
         */

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class SexBean {
        /**
         * value : 男
         */

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class FolkBean {
        /**
         * value : 汉
         */

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class BirtBean {
        /**
         * value : 1996年09月17日
         */

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class AddrBean {
        /**
         * value : 河北省保定市定兴县杨村乡杨村二十三区13号
         */

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class NumBean {
        /**
         * value : 130626199609177436
         */

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class IssueBean {
        /**
         * value :
         */

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class ValidBean {
        /**
         * value :
         */

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class TypeBean {
        /**
         * value : 正面
         */

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class CoverBean {
        /**
         * value : 无遮挡
         */

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
