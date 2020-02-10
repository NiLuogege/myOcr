package com.example.ruifight_3.saolouruifight.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.BaseActivity;
import com.example.ruifight_3.saolouruifight.ui.adapter.ImagePickerAdapter;
import com.example.ruifight_3.saolouruifight.ui.bean.ChuLiBean;
import com.example.ruifight_3.saolouruifight.util.Base64Utils;
import com.example.ruifight_3.saolouruifight.util.DiaLogUtil;
import com.example.ruifight_3.saolouruifight.util.ImageUtil;
import com.example.ruifight_3.saolouruifight.util.JsonUtils;
import com.example.ruifight_3.saolouruifight.util.ToastUtil;
import com.example.ruifight_3.saolouruifight.util.uiutil.AndroidBug5497Workaround;
import com.example.ruifight_3.saolouruifight.widget.SelectDialog;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by RuiFight-3 on 2018/5/14.
 */

public class ChuLiAttentionActivity extends BaseActivity implements View.OnClickListener, ImagePickerAdapter.OnRecyclerViewItemClickListener {

    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 4;               //允许选择图片最大数
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private StringBuffer buf;
    private int num = 30;//限制的最大字数
    @BindView(R.id.chuli_attention_guanzhu_ed)
    EditText guanzhu_ed;
    @BindView(R.id.chuli_attention_miaoshu_ed)
    EditText miaoshu_ed;
    @BindView(R.id.chuli_attention_commit_ed)
    Button commit_bu;
    @BindView(R.id.yuanyin_tv_size)
    TextView yuanyin_tv_size;

    @BindView(R.id.miaoshu_tv_size)
    TextView miaoshu_tv_size;
    private String id;
    private String housemark; //关注原因

    @Override
    protected int setLayout() {
        return R.layout.activity_chuliattention;
    }

    @Override
    protected void initView() {
        setSteepStatusBar(true);
        setTooBarBreakImage(R.mipmap.breakimagee);
        setTooBarTitleText("填写关注任务描述");
        AndroidBug5497Workaround.assistActivity(this);
        commit_bu.setOnClickListener(this);
        initEditView();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        //状态
//        state=intent.getStringExtra("state");
        //房主关注id
        id = intent.getStringExtra("followHouseId");
        housemark = intent.getStringExtra("housemark");
        guanzhu_ed.setText(housemark);
        guanzhu_ed.setEnabled(false);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

    private void initEditView() {
        guanzhu_ed.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int selectionStart;
            private int selectionEnd;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = num - s.length();
                yuanyin_tv_size.setText(number + "/30");
                selectionStart = guanzhu_ed.getSelectionStart();
                selectionEnd = guanzhu_ed.getSelectionEnd();
                //System.out.println("start="+selectionStart+",end="+selectionEnd);
                if (temp.length() > num) {
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionStart;
                    guanzhu_ed.setText(s);
                    guanzhu_ed.setSelection(tempSelection);//设置光标在最后
                }
            }
        });


        miaoshu_ed.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int selectionStart;
            private int selectionEnd;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = num - s.length();
                miaoshu_tv_size.setText(number + "/30");
                selectionStart = miaoshu_ed.getSelectionStart();
                selectionEnd = miaoshu_ed.getSelectionEnd();
                //System.out.println("start="+selectionStart+",end="+selectionEnd);
                if (temp.length() > num) {
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionStart;
                    miaoshu_ed.setText(s);
                    miaoshu_ed.setSelection(tempSelection);//设置光标在最后
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chuli_attention_commit_ed:
                String syuan = guanzhu_ed.getText().toString();
                String miaoshu = miaoshu_ed.getText().toString();

                if (syuan.equals("")) {
                    ToastUtil.showInfo(this, "请添加原因");
                } else if (miaoshu.equals("")) {
                    ToastUtil.showInfo(this, "请添加描述");
                } else {
                    DiaLogUtil.showDiaLog(this, "请在上传");
                    putMesssage(id, miaoshu, buf);
                }

                break;
            default:
                break;
        }
    }

    /**
     * 提交
     */
    public void putMesssage(String id, String miaoshu, StringBuffer image) {
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.CHULI)
                .tag(this)
                .addParams("id", id)
                .addParams("content", miaoshu)
                .addParams("img", image+"")
//                .addFile("img","s6",new File(list.get(0)))
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Request request, Exception e) {
                        DiaLogUtil.dismissDiaLog();
                        ToastUtil.showInfo(ChuLiAttentionActivity.this, e.getMessage() + "   onError");
                    }

                    @Override
                    public void onResponse(String response) {
                        DiaLogUtil.dismissDiaLog();
                        try {
                            ChuLiBean chuLiBean = (ChuLiBean) JsonUtils.stringToObject(response, ChuLiBean.class);
                            if (chuLiBean.isStatus() == false) {
                                ToastUtil.showInfo(ChuLiAttentionActivity.this, chuLiBean.getMsg() + "");
                            } else {
                                ToastUtil.showInfo(ChuLiAttentionActivity.this, "操作成功");
                                finish();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            setLogin();
                        }
                    }
                });

    }
    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }


    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0: // 直接调起相机
                                /**
                                 * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
                                 *
                                 * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
                                 *
                                 * 如果实在有所需要，请直接下载源码引用。
                                 */
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent = new Intent(ChuLiAttentionActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(ChuLiAttentionActivity.this, ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                break;
                            default:
                                break;
                        }

                    }
                }, names);


                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    ArrayList<ImageItem> images = null;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                    buf = new StringBuffer();
                    for (int i=0;i<images.size();i++){
                        try {
                            Bitmap bitmap1 = ImageUtil.getBitmapFormUri(ChuLiAttentionActivity.this, Uri.fromFile(new File(images.get(i).path)));
                           // Bitmap bitmap1 = BitmapFactory.decodeFile(images.get(i).path);//filePath
                            buf.append(Base64Utils.bitmapToBase64(bitmap1)).append(",");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                    buf=null;
                    buf = new StringBuffer();
                    for (int i=0;i<images.size();i++){
                        try {
                            Bitmap bitmap2 = ImageUtil.getBitmapFormUri(ChuLiAttentionActivity.this, Uri.fromFile(new File(images.get(i).path)));
                            //Bitmap bitmap2 = BitmapFactory.decodeFile(images.get(i).path);//filePath
                            buf.append(Base64Utils.bitmapToBase64(bitmap2)).append(",");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
