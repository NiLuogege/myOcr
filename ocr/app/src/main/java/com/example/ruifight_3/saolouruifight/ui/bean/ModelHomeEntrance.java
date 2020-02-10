package com.example.ruifight_3.saolouruifight.ui.bean;

/**
 * Created by RuiFight-3 on 2019/6/6.
 * Creator: 张震 (Android  zhangzhen)
 * Describe:
 */
public class ModelHomeEntrance {

    private String name;
    private int image;

    public ModelHomeEntrance(String name, int image) {
        this.image = image;
        this.name = name;
    }


    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
