package com.example.ruifight_3.saolouruifight.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.ruifight_3.saolouruifight.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by RuiFight-3 on 2018/6/28.
 */

public final class NetWorkUtil {
    /**
     * 这个方法是判断网络状态是否可用的
     *
     * @param context
     * @return
     */
    public static boolean isConn(Context context) {
        boolean bisConnFlag = false;
        //1.获取网络连接的管理对象
        ConnectivityManager conManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //2.通过管理者对象拿到网络的信息
        NetworkInfo network = conManager.getActiveNetworkInfo();
        if (network != null) {
            //3.网络状态是否可用的返回值
            bisConnFlag = network.isAvailable();
        }
        return bisConnFlag;
    }


    /**
     * 如果没有网络 弹出dialog对话框,,,是否进入设置网络的页面
     *
     * @param context
     */
    public static void showNoNetWorkDlg(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.important)         //
                .setTitle("警告")            //
                .setMessage("当前无网络,是否去设置?").setPositiveButton("设置", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 跳转到系统的网络设置界面
                Intent intent = null;
                // 先判断当前系统版本
                if (android.os.Build.VERSION.SDK_INT > 10) {  // 3.0以上
                    intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                } else {
                    intent = new Intent();
                    intent.setClassName("com.android.settings", "com.android.settings.WirelessSettings");
                }
                context.startActivity(intent);


            }
        }).setNegativeButton("取消", null).show();
    }

    /**
     * 获取外网的IP(要访问Url,要放到后台线程里处理)
     *
     * @return String
     */
    public static String getNetIp() {
        Thread thread = new Thread() {
            protected String doInBackground(Void... params) {
                URL infoUrl = null;
                InputStream inStream = null;
                String line = "";
                try {
                    infoUrl = new URL("http://pv.sohu.com/cityjson?ie=utf-8");
                    URLConnection connection = infoUrl.openConnection();
                    HttpURLConnection httpConnection = (HttpURLConnection) connection;
                    int responseCode = httpConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        inStream = httpConnection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
                        StringBuilder strber = new StringBuilder();
                        while ((line = reader.readLine()) != null) {
                            strber.append(line + "\n");
                        }
                        inStream.close();
                        // 从反馈的结果中提取出IP地址
                        int start = strber.indexOf("{");
                        int end = strber.indexOf("}");
                        String json = strber.substring(start, end + 1);
                        if (json != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(json);
                                line = jsonObject.optString("cip");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        return line;
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return line;
            }
        };
        thread.start();
        return null;
    }

    /**
     * 获取外网的IP(必须放到子线程里处理)
     */
    public static String getNetIp1() {
        URL infoUrl = null;
        InputStream inStream = null;
        String ipLine = "";
        HttpURLConnection httpConnection = null;
        try {
//            infoUrl = new URL("http://ip168.com/");
            infoUrl = new URL("http://pv.sohu.com/cityjson?ie=utf-8");
            URLConnection connection = infoUrl.openConnection();
            httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.getInputStream();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inStream, "utf-8"));
                StringBuilder strber = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    strber.append(line + "\n");
                }
                Pattern pattern = Pattern.compile("((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))");
                Matcher matcher = pattern.matcher(strber.toString());
                if (matcher.find()) {
                    ipLine = matcher.group();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inStream.close();
                httpConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        Log.e("getNetIp", ipLine);
        return ipLine;
    }

    /**
     * 判断当前网络是否为 Wifi 网络连接
     *
     * @param context
     * @return
     */
    public static boolean WifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }
}
