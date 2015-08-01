package com.manymore13.Stretch;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.BounceInterpolator;
import android.widget.LinearLayout;

import com.example.xinxindemo.R;

public class StretchActivity extends Activity implements 
                   View.OnClickListener,
                   StretchAnimation.AnimationListener {

	private final static String TAG = "StretchActivity";
	
	private int screentWidth = 0;
	private int screentHeight = 0;
	
	// View可伸展最长的宽度
	private int maxSize;
	
	// View可伸展最小宽度
	private int minSize;
	
	// 当前点击的View
	private View currentView;
	
	// 显示最长的那个View
	private View preView;
	
	// 主布局ViewGroup
	private LinearLayout container;
	
	private StretchAnimation stretchAnimation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		container = (LinearLayout) this.findViewById(R.id.container);
		initValues(2);
	}

	/**
	 * @param index 初始化时哪一个是最长的View 从零开始
	 */
	private void initValues(int index) {
		initStretchAnimation();
		
		int sizeValue = 0;
		LayoutParams params = null;
		int childCount = container.getChildCount();
		if (index < 0 || index >= childCount) {
			throw new RuntimeException("index 超出范围");
		}
		
		for (int i = 0; i < childCount; i++) {
			View child = container.getChildAt(i);
			child.setOnClickListener(this);
			params = child.getLayoutParams();
			
			if (i == index) {
				preView = child;
				sizeValue = maxSize;
			} else {
				sizeValue = minSize;
			}
			if(stretchAnimation.getOrientation() == com.manymore13.Stretch.StretchAnimation.Orientation.horizontal){
				params.width = sizeValue;
			}else if(stretchAnimation.getOrientation() == com.manymore13.Stretch.StretchAnimation.Orientation.vertical){
				params.height = sizeValue;
			}

			child.setLayoutParams(params);
		}
		
	}
	
	private void initStretchAnimation(){
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        screentWidth = metric.widthPixels;
        screentHeight= metric.heightPixels;
        //
        measureSize(screentHeight);
        stretchAnimation = new StretchAnimation(maxSize, minSize, StretchAnimation.Orientation.vertical, 500);
        stretchAnimation.setInterpolator(new BounceInterpolator());
        stretchAnimation.setDuration(800);
        stretchAnimation.setOnAnimationListener(this);
    }


	/**
	 * 测量View 的 max min 长度  这里你可以根据你的要求设置max
	 * @param screenSize
	 * @param index 从零开始
	 */
	private void measureSize(int layoutSize) {
		int halfSize = layoutSize / 2;
		maxSize = halfSize - 50;
		minSize = (layoutSize - maxSize) / (container.getChildCount() - 1);
		Log.i(TAG, "maxSize="+maxSize+" minSize = "+minSize);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		View tempView = null;
		switch (id) {
		case R.id.btnOne:
			tempView = container.getChildAt(0);
			break;
		case R.id.btnTwo:
			tempView = container.getChildAt(1);
			break;
		case R.id.btnThree:
			tempView = container.getChildAt(2);
			break;
		case R.id.btnFour:
			tempView = container.getChildAt(3);
			break;
		}
		if(tempView == preView){
			return;
		}else{
			currentView = tempView;
		}
		clickEvent(currentView);
		setClickable(false);
		stretchAnimation.startAnimation(currentView);
	}
	
	private void clickEvent(View view) {
		int childCount = container.getChildCount();
		LinearLayout.LayoutParams params=null;
		for (int i = 0; i < childCount; i++) {
			View child = container.getChildAt(i);
			params = (android.widget.LinearLayout.LayoutParams) child.getLayoutParams();
			if (preView == child) {
				if(preView != view){
					params.weight = 1.0f;
				}
			} else {
				params.weight = 0.0f;
				if(stretchAnimation.getOrientation() == StretchAnimation.Orientation.horizontal){
					params.width = minSize;
				}else if(stretchAnimation.getOrientation() == StretchAnimation.Orientation.vertical){
					params.height = minSize;
				}
			}
			child.setLayoutParams(params);
		}
		preView = view;
	}
	
	// LinearLayout下所有childView 可点击开关
	// 当动画在播放时应该设置为不可点击，结束时设置为可点击
	private void setClickable(boolean isClickable){
		int childCount = container.getChildCount();
		for (int i = 0; i < childCount; i++) {
			container.getChildAt(i).setClickable(isClickable);
		}
	}

	@Override
	public void animationEnd(View v) {
		setClickable(true);
	}

}
