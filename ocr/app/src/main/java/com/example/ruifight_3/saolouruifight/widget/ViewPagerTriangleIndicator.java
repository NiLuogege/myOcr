package com.example.ruifight_3.saolouruifight.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.util.SPUtils;

import java.util.List;

/**
 * Created by RuiFight-3 on 2019/6/5.
 * Creator: 张震 (Android  zhangzhen)
 * Describe:自定义ViewPager指示器(三角形)
 */
public class ViewPagerTriangleIndicator extends LinearLayout {
    private int mTriangleWidth;//三角形底边宽
    private int mTriangleHeigh;//三角形高度
    private int mTriangleInitPos;//三角形起始点
    private int mTriangleMoveWidth;//三角形移动偏移
    private int DEFAULT_TRIANGLE_WIDTH = getScreenWidth() / 3 / 6;//最大三角形底边宽度

    private ClearEditText login_name;
    private ClearEditText login_password;
    private CheckBox password_checkbox;
    private Paint mPaint;
    private Path mPath;

    private int mVisibleTabNum;//最大可显示Tab数量值
    private static final int VISIBLE_COUNT_NUM = 2;//默认可显示的TAB数量为4个

    private List<String> mTitles;//保存指示器标题


    private ViewPager mViewPager;//次有外部ViewPager引用
    private AddPageChangeListener mAddPageChangeListener;

    private Context mContext;
    /**
     * 显示隐藏动画
     */
    private TranslateAnimation mShowAction;
    private TranslateAnimation mHiddenAction;

    public static int mPosition;

    public ViewPagerTriangleIndicator(Context context) {
        super(context, null);
        this.mContext = context;
    }

    public ViewPagerTriangleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initAnim();
        initAttr(context, attrs);
        initPaint();
    }

    /**
     * 获取自定义属性值(获取xml设置最大可见Tab数量)
     *
     * @param context
     * @param attrs
     */
    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerTriangleIndicator);
        if (typedArray != null) {
            mVisibleTabNum = typedArray.getInt(R.styleable.ViewPagerTriangleIndicator_visible_tab_num, VISIBLE_COUNT_NUM);
        }
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#FFFFFF"));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * 初始化三角形
     */
    private void initTriangle() {
        mPath = new Path();
        mPath.moveTo(0, 0);
        mPath.lineTo(mTriangleWidth, 0);
        mPath.lineTo(mTriangleWidth / 2, -mTriangleHeigh);
        mPath.close();
    }


    /**
     * 当布局大小发生变化时回调
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTriangleWidth = w / mVisibleTabNum / 6;
        if (mTriangleWidth > DEFAULT_TRIANGLE_WIDTH) {
            mTriangleWidth = DEFAULT_TRIANGLE_WIDTH;
        }
        mTriangleHeigh = mTriangleWidth / 2 - 8;
        mTriangleInitPos = w / mVisibleTabNum / 2 - mTriangleWidth / 2;
        initTriangle();
    }

    /**
     * 在XML布局加载完毕后回调
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //根据可显示的Tab数量动态去改变Tab的宽度
        int totalTabNum = getChildCount();
        if (mVisibleTabNum != 0 && totalTabNum != 0) {
            for (int i = 0; i < totalTabNum; i++) {
                View view = getChildAt(i);
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                layoutParams.weight = 0;
                layoutParams.width = getScreenWidth() / mVisibleTabNum;
                view.setLayoutParams(layoutParams);
            }
        }
    }

    /**
     * 绘制子View
     *
     * @param canvas
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.translate(mTriangleInitPos + mTriangleMoveWidth, getHeight());
        canvas.drawPath(mPath, mPaint);
    }

    /**
     * 监听ViewPager滑动,联动Indicator
     *
     * @param position
     * @param positionOffset
     */
    public void scroll(int position, float positionOffset) {
        int tabWidth = getScreenWidth() / mVisibleTabNum;
        mTriangleMoveWidth = (int) (tabWidth * position + tabWidth * positionOffset);

        if ((mVisibleTabNum - 2) <= position && positionOffset > 0 && getChildCount() > mVisibleTabNum) {
            this.scrollTo((int) ((position - (mVisibleTabNum - 2)) * tabWidth + tabWidth * positionOffset), 0);
        }

        invalidate();
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    private int getScreenWidth() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * 设置指示器标题并给标题添加监听事件
     *
     * @param titles
     */
    public void setPageTitle(List<String> titles, ClearEditText login_name, ClearEditText login_password, CheckBox password_checkbox) {
        this.mTitles = titles;
        this.login_name = login_name;
        this.login_password = login_password;
        this.password_checkbox = password_checkbox;
        if (mTitles != null && mTitles.size() > 0) {
            removeAllViews();
            for (int i = 0; i < mTitles.size(); i++) {
                final int pageIndex = i;
                TextView textView = new TextView(getContext());
                LayoutParams layoutParams = new LayoutParams(getScreenWidth() / mVisibleTabNum, LayoutParams.MATCH_PARENT);
                layoutParams.width = getScreenWidth() / mVisibleTabNum;
                textView.setText(mTitles.get(i));
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textView.setTextColor(Color.parseColor("#FFFFFF"));
                textView.setLayoutParams(layoutParams);
                textView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(pageIndex);
                    }
                });
                addView(textView);
            }
        }
        setInitPageTitlesColor();
        setPageTitleHighColor(0);
    }

    /**
     * 把所有标题颜色置为不高亮
     */
    private void setInitPageTitlesColor() {
        int totalView = getChildCount();
        for (int i = 0; i < totalView; i++) {
            View view = getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(Color.parseColor("#70FFFFFF"));
                ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            }
        }
    }

    /**
     * 设置标题高亮
     *
     * @param pos
     */
    private void setPageTitleHighColor(int pos) {
        View view = getChildAt(pos);
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(Color.parseColor("#FFFFFF"));
            ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
        }
    }


    /**
     * 绑定ViewPager
     *
     * @param viewPager
     */
    public void setViewPagerWithIndicator(ViewPager viewPager) {
        this.mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (mAddPageChangeListener != null) {
                    mAddPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
                mPosition = position;
                setData(position);
            }

            @Override
            public void onPageSelected(int position) {
                setInitPageTitlesColor();
                setPageTitleHighColor(position);
                mViewPager.setCurrentItem(position);
                if (mAddPageChangeListener != null) {
                    mAddPageChangeListener.onPageSelected(position);
                }
                mPosition = position;
                scroll(position, 0);
                setData(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mAddPageChangeListener != null) {
                    mAddPageChangeListener.onPageScrollStateChanged(state);
                }
            }
        });
    }

    /**
     * 避免用户监听ViewPager复写了addOnPageChangeListener使得三角形滑动效果失效
     *
     * @param addPageChangeListener
     */
    public void addPageChangeListener(AddPageChangeListener addPageChangeListener) {
        this.mAddPageChangeListener = addPageChangeListener;
    }

    /**
     * 复制官方addOnPageChangeListener对应的接口方法
     * Callback interface for responding to changing state of the selected page.
     */
    public interface AddPageChangeListener {

        /**
         * This method will be invoked when the current page is scrolled, either as part
         * of a programmatically initiated smooth scroll or a user initiated touch scroll.
         *
         * @param position             Position index of the first page currently being displayed.
         *                             Page position+1 will be visible if positionOffset is nonzero.
         * @param positionOffset       Value from [0, 1) indicating the offset from the page at position.
         * @param positionOffsetPixels Value in pixels indicating the offset from position.
         */
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        /**
         * This method will be invoked when a new page becomes selected. Animation is not
         * necessarily complete.
         *
         * @param position Position index of the new selected page.
         */
        void onPageSelected(int position);

        /**
         * Called when the scroll state changes. Useful for discovering when the user
         * begins dragging, when the pager is automatically settling to the current page,
         * or when it is fully stopped/idle.
         *
         * @param state The new scroll state.
         * @see ViewPager#SCROLL_STATE_IDLE
         * @see ViewPager#SCROLL_STATE_DRAGGING
         * @see ViewPager#SCROLL_STATE_SETTLING
         */
        void onPageScrollStateChanged(int state);
    }

    /**
     * 设置首页数据
     *
     * @param position
     */
    public void setData(int position) {
        if (position == 1) {
            if (password_checkbox != null) {
                password_checkbox.startAnimation(mHiddenAction);
                password_checkbox.setVisibility(View.INVISIBLE);
            }
            login_name.setText("");
            login_name.setHint("输入房主账号");
            login_password.setText("");
        }
        if (position == 0) {
            if (!TextUtils.isEmpty(SPUtils.get(mContext, "loginName", "").toString())) {
                login_name.setText(SPUtils.get(mContext, "loginName", "").toString());
            }
            login_name.setHint("输入流管员账号");

            boolean ischeck = (boolean) SPUtils.get(mContext, "isChecked_check", false);
            if (ischeck == true) {
                password_checkbox.setChecked(true);
                String pass = (String) SPUtils.get(mContext, login_name.getText().toString().trim() + "-LoginPassword", "");
                if (!pass.equals("")) {
                    login_password.setText(pass);
                }
            } else {
                password_checkbox.setChecked(false);
                SPUtils.remove(mContext, login_name.getText().toString().trim() + "-LoginPassword");
            }

            if (password_checkbox != null) {
                password_checkbox.startAnimation(mShowAction);
                password_checkbox.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 初始化动画
     */
    public void initAnim() {
        mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(500);

        mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f);
        mHiddenAction.setDuration(500);
    }

    public void onDelete() {
        if (mHiddenAction != null) {
            mHiddenAction = null;
        }
        if (mShowAction != null) {
            mShowAction = null;
        }
    }
}
