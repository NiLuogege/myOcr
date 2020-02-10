package com.example.ruifight_3.saolouruifight.util;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.widget.CircleLoadingView;

/**
 * Created by RuiFight-3 on 2018/4/16.
 */

public class DiaLogUtil {
    private static Dialog progressDialog;
    private static CircleLoadingView circleLoadingView;

    public static void showDiaLog(Context context, String title) {
        try {
            if (progressDialog == null) {
                progressDialog = new Dialog(context, R.style.progress_dialog);
                progressDialog.setContentView(R.layout.dialog_layout);
                progressDialog.setCancelable(true);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                TextView msg = progressDialog.findViewById(R.id.id_tv_loadingmsg);
                circleLoadingView = progressDialog.findViewById(R.id.clv);
                msg.setText(title);
                progressDialog.show();
            } else {
                dismissDiaLog();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dismissDiaLog() {
        try {
            if (progressDialog != null) {
                if (circleLoadingView != null) {
                    circleLoadingView.stopDu();
                    circleLoadingView = null;
                }
                if (progressDialog.isShowing()) {
                    progressDialog.cancel();
                }
                progressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
