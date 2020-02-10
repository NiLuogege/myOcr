package com.example.ruifight_3.saolouruifight.util;

import android.support.v4.content.FileProvider;

/**
 * Created by RuiFight-3 on 2019/4/18.
 * Creator: 张震 (Android  zhangzhen)
 * Describe:FileProvider重复
 *
 * 这个也出现在模块话开发中，或是引用的三方库中也定义了FileProvider，就会报FileProvider重复的错误。
 * 解决方法也很简单，就是定义一个我们自己的FileProvider：
 * 其他什么也不用干，直接继承FileProvider创建一个自己的FileProvider就好
 */
public class MyFileProvider extends FileProvider {

}
