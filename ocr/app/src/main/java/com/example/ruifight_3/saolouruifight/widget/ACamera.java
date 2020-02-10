package com.example.ruifight_3.saolouruifight.widget;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.BaseIdCard;
import com.example.ruifight_3.saolouruifight.ui.idcard.ARecognize;
import com.yunmai.android.other.CameraManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RuiFight-3 on 2018/7/23.
 */

public class ACamera extends BaseIdCard implements SurfaceHolder.Callback {

    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private Button camera_shutter_a;
    private Button camera_recog;
    private Button camera_flash;
    private CameraManager mCameraManager;
    private List<String> flashList;
    private int flashPostion = 0;
    private byte[] idcardA = null;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) { // 抓取图像成功
                idcardA = msg.getData().getByteArray("picData");
            } else {
                Toast.makeText(ACamera.this,"sss", Toast.LENGTH_SHORT).show();
            }
            camera_shutter_a.setEnabled(true);
            mCameraManager.initDisplay();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_idcard);


        initView();
        mCameraManager = new CameraManager(ACamera.this, mHandler);
    }

    public void initView() {
        camera_shutter_a = (Button) findViewById(R.id.camera_shutter_a);
        camera_recog = (Button) findViewById(R.id.camera_recog);
        camera_flash = (Button) findViewById(R.id.camera_flash);
        camera_shutter_a.setOnClickListener(mLsnClick);
        camera_recog.setOnClickListener(mLsnClick);
        camera_flash.setOnClickListener(mLsnClick);

        mSurfaceView = (SurfaceView) findViewById(R.id.camera_preview);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(ACamera.this);
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    protected void onResume() {
        idcardA = null;
        super.onResume();
    }
    private View.OnClickListener mLsnClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.camera_shutter_a:
                    camera_shutter_a.setEnabled(false);
                    mCameraManager.setTakeIdcardA();
                    mCameraManager.requestFocuse();
                    break;
                case R.id.camera_recog:
                    if (idcardA == null) {
                        Toast.makeText(ACamera.this, "请拍摄证件正面", Toast.LENGTH_LONG).show();
                        return;
                    }
                    Intent aRecognize2 = new Intent(ACamera.this, ARecognize.class);
                    aRecognize2.putExtra("idcardA", idcardA);
                    startActivityForResult(aRecognize2, REQUEST_CODE_RECOG);
                    break;
                case R.id.camera_flash:
                    flashPostion ++ ;
                    if(flashPostion < flashList.size()){
                        setFlash(flashList.get(flashPostion));
                    }else{
                        flashPostion = 0;
                        setFlash(flashList.get(flashPostion));
                    }
                    break;
                    default:
                        break;
            }
        }

    };

    private void setFlash(String flashModel){
        mCameraManager.setCameraFlashMode(flashModel);
        if(flashModel.equals(Camera.Parameters.FLASH_MODE_ON)){
            camera_flash.setText("闪光灯开");
        }else if(flashModel.equals(Camera.Parameters.FLASH_MODE_OFF)){
            camera_flash.setText("闪光灯关");
        }else{
            camera_flash.setText("闪光灯自动");
        }
    }

    private List<String> getSupportFlashModel(){
        List<String> list = new ArrayList<String>();
        if(mCameraManager.isSupportFlash(Camera.Parameters.FLASH_MODE_OFF)){
            list.add(Camera.Parameters.FLASH_MODE_OFF);
        }
        if (mCameraManager.isSupportFlash(Camera.Parameters.FLASH_MODE_ON)) {
            list.add(Camera.Parameters.FLASH_MODE_ON);
        }
        if (mCameraManager.isSupportFlash(Camera.Parameters.FLASH_MODE_AUTO)) {
            list.add(Camera.Parameters.FLASH_MODE_AUTO);
        }
        return list;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Debug.i(TAG, "surfaceCreated");
        try {
            mCameraManager.openCamera(holder);
            flashList = getSupportFlashModel();
            if (flashList == null || flashList.size() == 0) {
                camera_flash.setText("闪光灯无法设置");
                camera_flash.setEnabled(false);
            }else{
                setFlash(flashList.get(0));
            }
            if(!mCameraManager.isSupportAutoFocus()){
                Toast.makeText(getBaseContext(), "不支持自动对焦！", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(ACamera.this, R.string.camera_open_error,
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (width > height) {
            mCameraManager.setPreviewSize(width, height);
        } else {
            mCameraManager.setPreviewSize(height, width);
        }
        mCameraManager.initDisplay();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mCameraManager.closeCamera();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        mCameraManager.initDisplay();
    }
}
