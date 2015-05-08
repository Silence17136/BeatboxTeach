package com.yangbang.beatboxteach.base;

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

	protected abstract void initView();

	protected abstract int getContentViewID();

}
