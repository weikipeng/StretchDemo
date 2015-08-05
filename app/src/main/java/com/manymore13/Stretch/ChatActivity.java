package com.manymore13.Stretch;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.example.xinxindemo.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by WikiPeng on 15/7/31 下午7:24.
 */
public class ChatActivity extends Activity implements View.OnClickListener{
    private DemoLinearLayout mChatLayout;
    private PullToRefreshListView mListViewTop;
    private PullToRefreshListView mListViewBottom;

    private ListAdapter mAdapter;

    private Button mButton;

    private boolean isExpand = true;

    private LinearLayout mButtonLayout;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initView();
        initData();
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

    private void initStretchAnimation() {

//        int layoutHeight = mChatLayout.getHeight();
//        int buttonLayoutSize = mButtonLayout.getHeight();
//        int totalSize = layoutHeight - buttonLayoutSize;
//
//        int maxSize = (int) (totalSize * 0.7);
//        int minSize = totalSize - maxSize;
//
//        Log.e("wiki","bug --->max:"+maxSize+"    min:"+minSize);
//
//        stretchAnimation = new StretchAnimation(maxSize, minSize, StretchAnimation.Orientation.vertical, 500);
//        stretchAnimation.setInterpolator(new LinearInterpolator());
//        stretchAnimation.setDuration(800);
//        stretchAnimation.setOnAnimationListener(this);
    }

    private void initView() {
        mChatLayout = (DemoLinearLayout) findViewById(R.id.chat_content_view);
        mButton = (Button) findViewById(R.id.chat_expand_button);
        mButtonLayout = (LinearLayout) findViewById(R.id.button_layout);
        mListViewTop = (PullToRefreshListView) findViewById(R.id.topList);
        mListViewBottom = (PullToRefreshListView) findViewById(R.id.bottomList);

        mButton.setOnClickListener(this);

        setOnClickListenerForChild(R.id.chat_content_view, true, true);
    }

    @Override
    public void onClick(View v) {
        int childCount = mChatLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = mChatLayout.getChildAt(i);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
            if (i == 0) {
                params.weight = isExpand ? 0.3f : 0.7f;
            } else if (i == childCount - 1) {
                params.weight = isExpand ? 0.7f : 0.3f;
            }

            view.setLayoutParams(params);
        }

        isExpand = !isExpand;
        mChatLayout.requestLayout();
    }

    private void setOnClickListenerForChild(int id, boolean enable, boolean needScroll) {
        ViewGroup viewGroup = (ViewGroup)findViewById(id);

        if(enable){
            if(needScroll){
                LayoutTransitionUtil.enableChangeTransition(viewGroup.getLayoutTransition());
                Log.e("wiki", "bug -----> LayoutTransitionUtil.checkAndSetupChangeAnimationForAllChild ");
            }else{
                LayoutTransitionUtil.setupChangeAnimationOneTime(viewGroup);
            }
        }
//        for(int i = 0;i<viewGroup.getChildCount();i++){
//            viewGroup.getChildAt(i).setOnClickListener(this);
//        }
    }
}
