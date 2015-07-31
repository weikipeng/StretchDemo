package com.manymore13.Stretch;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.example.xinxindemo.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by WikiPeng on 15/7/31 下午7:24.
 */
public class ChatActivity extends Activity {
    private PullToRefreshListView mListViewTop;
    private PullToRefreshListView mListViewBottom;

    private ListAdapter mAdapter;

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

    private void initView() {
        mListViewTop = (PullToRefreshListView) findViewById(R.id.topList);
        mListViewBottom = (PullToRefreshListView) findViewById(R.id.bottomList);
    }
}
