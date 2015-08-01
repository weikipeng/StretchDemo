package com.manymore13.Stretch;

import android.animation.LayoutTransition;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.example.xinxindemo.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by WikiPeng on 15/7/31 下午7:24.
 */
public class ChatActivity01 extends Activity implements View.OnClickListener {
    private LinearLayout mChatLayout;
    private PullToRefreshListView mListViewTop;
    private PullToRefreshListView mListViewBottom;

    private ListAdapter mAdapter;

    private Button mButton;

    private boolean isExpand = true;


//    private int screentWidth = 0;
//    private int screentHeight = 0;
//    // View可伸展最长的宽度
//    private int maxSize;
//
//    // View可伸展最小宽度
//    private int minSize;
//
//    private StretchAnimation stretchAnimation;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_01);

        initView();
        initData();
        initStretchAnimation();
    }

    private void initData() {
        Integer[] array = new Integer[12];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
        mListViewBottom.setAdapter(mAdapter);
        mListViewTop.setAdapter(mAdapter);



    }

    private void initStretchAnimation(){
//        DisplayMetrics metric = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metric);
//        screentWidth = metric.widthPixels;
//        screentHeight= metric.heightPixels;
//        //
//        measureSize(screentHeight);
//        stretchAnimation = new StretchAnimation(maxSize, minSize, StretchAnimation.Orientation.vertical, 500);
//        stretchAnimation.setInterpolator(new BounceInterpolator());
//        stretchAnimation.setDuration(800);
//        stretchAnimation.setOnAnimationListener(this);
    }

    /**
     * 测量View 的 max min 长度  这里你可以根据你的要求设置max
     */
    private void measureSize(int layoutSize) {
//        int halfSize = layoutSize / 2;
//        maxSize = halfSize - 50;
//        minSize = (layoutSize - maxSize) / (container.getChildCount() - 1);
//        Log.i(TAG, "maxSize=" + maxSize + " minSize = " + minSize);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initView() {
        mChatLayout = (LinearLayout) findViewById(R.id.chat_content_view);
        mButton = (Button) findViewById(R.id.chat_expand_button);
        mListViewTop = (PullToRefreshListView) findViewById(R.id.topList);
        mListViewBottom = (PullToRefreshListView) findViewById(R.id.bottomList);

        mButton.setOnClickListener(this);


        LayoutTransition layoutTransition = mChatLayout.getLayoutTransition();
        layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
    }

    @Override
    public void onClick(View v) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mListViewTop.getLayoutParams();
        LinearLayout.LayoutParams bottomLayoutParams = (LinearLayout.LayoutParams) mListViewBottom.getLayoutParams();

        if (isExpand) {
            layoutParams.weight = 0.3f;
            bottomLayoutParams.weight = 0.7f;
        }else{
            layoutParams.weight = 0.7f;
            bottomLayoutParams.weight = 0.3f;
        }

        isExpand = !isExpand;

        mListViewTop.setLayoutParams(layoutParams);
        mListViewBottom.setLayoutParams(bottomLayoutParams);
        mChatLayout.requestLayout();
    }
}
