package com.yangbang.beatboxteach;

import java.util.ArrayList;
import java.util.List;

import org.apache.dd.aa.myl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;
import com.yangbang.beatboxteach.adapter.MyFragmentPageAdapter;
import com.yangbang.beatboxteach.fragment.TextTeachFragment;
import com.yangbang.beatboxteach.view.TitleBar;

public class MainActivity extends FragmentActivity implements OnClickListener {
	TitleBar titlebar;
	TextView activity_main_bar_tv0;
	TextView activity_main_bar_tv1;
	ViewPager viewpage;
	List<Fragment> fragments = new ArrayList<Fragment>();
	Animation scaleAnim;
	float tempArg2;
	float leftSize;
	float RightSize;
	String str = "张三";
	String str2 = "李四";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initPageFragment();
		UmengUpdateAgent.update(this);
		myl.init(this);
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

	private void initView() {
		titlebar = (TitleBar) this.findViewById(R.id.title_bar);
		titlebar.setTitleText("BeatboxTeach");
		titlebar.setRightImg(R.drawable.set);
		titlebar.setRightListener(this);
		activity_main_bar_tv0 = (TextView) this
				.findViewById(R.id.activity_main_bar_tv0);
		activity_main_bar_tv1 = (TextView) this
				.findViewById(R.id.activity_main_bar_tv1);
		viewpage = (ViewPager) this.findViewById(R.id.activity_main_viewpage);
		activity_main_bar_tv0.setTextSize(20);
		activity_main_bar_tv1.setTextSize(15);
		activity_main_bar_tv0.setOnClickListener(this);
		activity_main_bar_tv1.setOnClickListener(this);
	}

	private void initPageFragment() {
		TextTeachFragment fragment = new TextTeachFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("type", 1);
		fragment.setArguments(bundle);
		fragments.add(fragment);
		TextTeachFragment fragment2 = new TextTeachFragment();
		Bundle bundle2 = new Bundle();
		bundle2.putInt("type", 2);
		fragment2.setArguments(bundle2);
		fragments.add(fragment2);
		viewpage.setAdapter(new MyFragmentPageAdapter(
				getSupportFragmentManager(), fragments));
		viewpage.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	private class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

			// if ((arg2 - tempArg2) > 0 && arg2 != 0) {
			// leftSize = 20 - (arg1 * 5);
			// RightSize = 15 + (arg1 * 5);
			// activity_main_bar_tv0.setTextSize(leftSize);
			// activity_main_bar_tv1.setTextSize(RightSize);
			// } else if ((arg2 - tempArg2) < 0 && arg2 != 0) {
			// leftSize = 15 + (arg1 * 5);
			// RightSize = 20 - (arg1 * 5);
			// activity_main_bar_tv0.setTextSize(leftSize);
			// activity_main_bar_tv1.setTextSize(RightSize);
			// }
			// Log.i("onPageScrolled", "arg0-->" + arg0);
			// Log.i("onPageScrolled", "arg1-->" + arg1);
			// Log.i("onPageScrolled", "arg2-->" + arg2);
			// // activity_main_bar_tv0.setAlpha(alpha);
			// tempArg2 = arg2;
		}

		@Override
		public void onPageSelected(int arg0) {
			tempArg2 = 0;
			if (arg0 == 0) {
				leftSize = 20;
				RightSize = 15;
				activity_main_bar_tv0.setTextSize(leftSize);
				activity_main_bar_tv1.setTextSize(RightSize);
			} else {
				leftSize = 15;
				RightSize = 20;
				activity_main_bar_tv0.setTextSize(leftSize);
				activity_main_bar_tv1.setTextSize(RightSize);
			}
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.right_img:
			startActivity(new Intent(MainActivity.this, SetActivity.class));
			break;
		case R.id.activity_main_bar_tv0:
			viewpage.setCurrentItem(0);
			break;
		case R.id.activity_main_bar_tv1:
			viewpage.setCurrentItem(1);
			break;

		default:
			break;
		}
	}

}
