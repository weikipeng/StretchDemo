package com.manymore13.Stretch;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

public class DemoLinearLayout extends LinearLayout {
	
	@SuppressWarnings("unused")
	private static final String TAG = "demo.layout.transition.DemoLinearLayout";

	public DemoLinearLayout(Context context) {
		super(context);
	}

	public DemoLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public DemoLinearLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		LayoutTransitionUtil.checkAndSetupChangeAnimationForAllChild(this);
//        Log.e("wiki","bug -----> LayoutTransitionUtil.checkAndSetupChangeAnimationForAllChild ");
        super.onLayout(changed, l, t, r, b);
	}
}
