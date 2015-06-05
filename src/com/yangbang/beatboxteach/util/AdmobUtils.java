package com.yangbang.beatboxteach.util;

import net.youmi.android.AdManager;
import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import net.youmi.android.banner.AdViewListener;
import net.youmi.android.listener.Interface_ActivityListener;
import net.youmi.android.offers.OffersManager;
import net.youmi.android.spot.SpotManager;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.umeng.socialize.utils.Log;
import com.yangbang.beatboxteach.R;

public class AdmobUtils {
	public static final String youmiAppId = "c2733c350ae80bf1";
	public static final String youmiAppSecret = "a1b0fef0dcfde3b7";

	/**
	 * 初始化有米广告
	 * 
	 * @param activity
	 */
	public static void initYoumiAdmob(Activity activity) {
		AdManager.getInstance(activity).init(youmiAppId, youmiAppSecret, false);
	}

	/**
	 * 初始化有米插屏广告
	 * 
	 * @param activity
	 */
	public static void initYoumiInsertScreenAdmob(Activity activity) {
		SpotManager.getInstance(activity).loadSpotAds();
		SpotManager.getInstance(activity).setSpotOrientation(
				SpotManager.ORIENTATION_PORTRAIT);
		SpotManager.getInstance(activity).setAnimationType(
				SpotManager.ANIM_ADVANCE);
	}

	/**
	 * 显示有米插屏广告
	 * 
	 * @param activity
	 */
	public static void showYoumiInsertScreenAdmob(Activity activity) {
		SpotManager.getInstance(activity).showSpotAds(activity);
	}

	/**
	 * 初始化有米积分墙
	 * 
	 * @param activity
	 */
	public static void initYoumiIntegralWall(Activity activity) {
		OffersManager.getInstance(activity).onAppLaunch();
	}

	/**
	 * 释放有米积分墙资源
	 * 
	 * @param activity
	 */
	public static void releaseYoumiIntegralWall(Activity activity) {
		OffersManager.getInstance(activity).onAppExit();
	}

	/**
	 * 释放有米积分墙资源
	 * 
	 * @param context
	 */
	public static void releaseYoumiIntegralWall(Context context) {
		OffersManager.getInstance(context).onAppExit();
	}

	/**
	 * 显示有米积分墙
	 * 
	 * @param activity
	 */
	public static void showYoumiIntegralWall(Activity activity) {
		OffersManager.getInstance(activity).showOffersWall();

		// 同时本方法还支持以下重载

		// 自 Youmi Android OfferWall SDK v5.0.0 起, 支持全屏积分墙退出监听回调
		OffersManager.getInstance(activity).showOffersWall(
				new Interface_ActivityListener() {

					@Override
					public void onActivityDestroy(Context context) {
						// releaseYoumiIntegralWall(context);
					}
				});
	}

	/**
	 * 初始化有米广告条
	 * 
	 * @param activity
	 */
	public static void initYoumiAdmobBanner(final Activity activity) {
		// 实例化广告条
		final AdView adView = new AdView(activity, AdSize.FIT_SCREEN);

		// 获取要嵌入广告条的布局
		final LinearLayout adLayout = (LinearLayout) activity
				.findViewById(R.id.adLayout);

		// 将广告条加入到布局中
		adLayout.addView(adView);
		final ImageView ad_img_delete_banner = (ImageView) activity
				.findViewById(R.id.ad_img_delete_banner);
		ad_img_delete_banner.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i("YoumiAdmobBanner", "banner被用户手动关闭");
				adLayout.removeView(adView);
				ad_img_delete_banner.setVisibility(View.GONE);
				// 60秒后重启启动有米banner广告
				restartBanner(activity, adView, adLayout, ad_img_delete_banner,
						1000 * 30);
			}
		});

		adView.setAdListener(new AdViewListener() {

			@Override
			public void onSwitchedAd(AdView adView) {
				// 切换广告并展示
				ad_img_delete_banner.setVisibility(View.VISIBLE);
			}

			@Override
			public void onReceivedAd(AdView adView) {
				// 请求广告成功
				Log.i("YoumiAdmobBanner", "banner请求成功");
			}

			@Override
			public void onFailedToReceivedAd(AdView adView) {
				// 请求广告失败
				Log.i("YoumiAdmobBanner", "banner请求失败");
				adLayout.removeView(adView);
				ad_img_delete_banner.setVisibility(View.GONE);
				restartBanner(activity, adView, adLayout, ad_img_delete_banner,
						1000 * 30);
			}
		});
	}

	private static void restartBanner(final Activity activity,
			final AdView adView, final LinearLayout bannerView,
			final ImageView deleteView, int delayMillis) {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				if (activity != null) {
					Log.i("YoumiAdmobBanner", "activityIsFinishing-->"
							+ activity.isFinishing());
				} else {
					Log.i("YoumiAdmobBanner", "activityIsFinishing-->null");
				}
				if (activity != null && activity.isFinishing() == false) {
					bannerView.addView(adView);
					deleteView.setVisibility(View.VISIBLE);
					Log.i("YoumiAdmobBanner", "banner已重新启动");
				}
			}
		}, delayMillis);
	}
}
