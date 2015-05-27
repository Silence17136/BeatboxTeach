package com.yangbang.beatboxteach.base;

import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getContentViewID() != 0) {
			setContentView(getContentViewID());
		}
		initView();
	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	protected abstract void initView();

	protected abstract int getContentViewID();

}
