package com.example.ruifight_3.saolouruifight.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by RuiFight-3 on 2018/4/16.
 */

public class ToastUtil {
    private  static Toast toast;
    //短
    public static void  showInfo(Context context, String info) {
        if (toast==null){
            toast=Toast.makeText(context, info, Toast.LENGTH_SHORT);
        }
        toast.setText(info);
        toast.show();
    }
    //长
    public static void  showInfoLong(Context context, String info) {
        if (toast==null){
         toast= Toast.makeText(context, info, Toast.LENGTH_LONG);
        }
        toast.setText(info);
        toast.show();
    }

    //通用长
    public static void shouToast(Context context, CharSequence info){
        if (toast==null){
            toast= Toast.makeText(context, info, Toast.LENGTH_LONG);
        }
        toast.setText(info);
        toast.show();
    }
}
