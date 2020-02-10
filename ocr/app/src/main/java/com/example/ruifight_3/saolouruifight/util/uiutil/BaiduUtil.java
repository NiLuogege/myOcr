package com.example.ruifight_3.saolouruifight.util.uiutil;

import android.content.Context;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.ruifight_3.saolouruifight.ui.bean.TianBean;
import com.example.ruifight_3.saolouruifight.util.JsonUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

/**
 * Created by RuiFight-3 on 2019/3/26.
 * Creator: 张震 (Android  zhangzhen)
 * Describe:
 */
public class BaiduUtil {
    private LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private TextView tv1, tv2, tv3;
    private Context mContext;
    public void initBaiDu(Context context,TextView tv1, TextView tv2, TextView tv3) {
        this.mContext=context;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tv3 = tv3;
        mLocationClient = new LocationClient(mContext);
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);//可选，设置是否须要地址信息，默认不须要
        option.setScanSpan(0);
        option.setOpenGps(true);
        option.setLocationNotify(false);
        option.setIgnoreKillProcess(false);
        option.SetIgnoreCacheException(false);
        option.setWifiCacheTimeOut(5 * 60 * 1000);
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    //定位回调
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            double latitude = location.getLatitude();    //获取纬度信息
            double longitude = location.getLongitude();    //获取经度信息
//            float radius = location.getRadius();    //获取定位精度，默认值为0.0f
//            String coorType = location.getCoorType();
//            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
//            int errorCode = location.getLocType();
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
            //String city = location.getCity();//城市
            String district = location.getDistrict();//区县
            try {
                if (district != null) {
                    getTian(latitude, longitude, district);
                } else {
                    tv1.setText("定位失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            mLocationClient.stop();
        }
    }

    public void getTian(double latitude, double longitude, final String district) {
        //String test = URLEncoder.encode(addre, "UTF-8");
        OkHttpUtils
                .get()
                .url("https://api.seniverse.com/v3/weather/now.json?key=ms5apsh9zfebbfih&location=" + latitude + ":" + longitude + "&language=zh-Hans&unit=c")
                .tag(mContext)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        try {
                            TianBean tianBean = (TianBean) JsonUtils.stringToObject(response, TianBean.class);
                            if (tianBean != null) {
                                tv1.setText(district);
                                tv2.setText(tianBean.getResults().get(0).getNow().getText() + "");
                                tv3.setText(tianBean.getResults().get(0).getNow().getTemperature() + "°");
                            } else {
                                tv1.setText("天气获取失败");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
