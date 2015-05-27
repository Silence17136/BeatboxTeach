package com.yangbang.beatboxteach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.sso.UMSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.yangbang.beatboxteach.base.BaseActivity;
import com.yangbang.beatboxteach.view.TitleBar;

public class SetActivity extends BaseActivity implements OnClickListener {
	RelativeLayout set_app_recommend;// 应用推荐
	RelativeLayout set_app_update;// 检测更新
	RelativeLayout set_share;// 应用分享
	RelativeLayout set_about;// 关于我们
	RelativeLayout set_exit;// 退出
	TitleBar titlebar;

	final UMSocialService mController = UMServiceFactory
			.getUMSocialService("com.umeng.share");
	String appID = "wx283e5a5adc6f7bb8";
	String appSecret = "c40656ded226f9a9b08ed7ba867f3b36";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setShare();
	}

	@Override
	protected void initView() {
		set_app_recommend = (RelativeLayout) this
				.findViewById(R.id.set_app_recommend);
		set_app_update = (RelativeLayout) this
				.findViewById(R.id.set_app_update);
		set_share = (RelativeLayout) this.findViewById(R.id.set_share);
		set_about = (RelativeLayout) this.findViewById(R.id.set_about);
		set_exit = (RelativeLayout) this.findViewById(R.id.set_exit);
		titlebar = (TitleBar) this.findViewById(R.id.titlebar);
		titlebar.setTitleText("设置");
		titlebar.setLeftImg(R.drawable.back);
		titlebar.setLeftListener(this);
		set_app_recommend.setOnClickListener(this);
		set_app_update.setOnClickListener(this);
		set_share.setOnClickListener(this);
		set_about.setOnClickListener(this);
		set_exit.setOnClickListener(this);
	}

	private void setShare() {
		// 设置分享内容
		mController
				.setShareContent("友盟社会化组件（SDK）让移动应用快速整合社交分享功能，http://www.umeng.com/social");
		// 设置分享图片, 参数2为图片的url地址
		mController.setShareMedia(new UMImage(this, R.drawable.ic_launcher));
		// 设置分享图片，参数2为本地图片的资源引用
		// mController.setShareMedia(new UMImage(getActivity(),
		// R.drawable.icon));
		// 设置分享图片，参数2为本地图片的路径(绝对路径)
		// mController.setShareMedia(new UMImage(getActivity(),
		// BitmapFactory.decodeFile("/mnt/sdcard/icon.png")));

		// 设置分享音乐
		// UMusic uMusic = new
		// UMusic("http://sns.whalecloud.com/test_music.mp3");
		// uMusic.setAuthor("GuGu");
		// uMusic.setTitle("天籁之音");
		// 设置音乐缩略图
		// uMusic.setThumb("http://www.umeng.com/images/pic/banner_module_social.png");
		// mController.setShareMedia(uMusic);

		// 设置分享视频
		// UMVideo umVideo = new UMVideo(
		// "http://v.youku.com/v_show/id_XNTE5ODAwMDM2.html?f=19001023");
		// 设置视频缩略图
		// umVideo.setThumb("http://www.umeng.com/images/pic/banner_module_social.png");
		// umVideo.setTitle("友盟社会化分享!");
		// mController.setShareMedia(umVideo);

		// 添加微信平台
		UMWXHandler wxHandler = new UMWXHandler(this, appID, appSecret);
		wxHandler.addToSocialSDK();
		// 添加微信朋友圈
		UMWXHandler wxCircleHandler = new UMWXHandler(this, appID, appSecret);
		wxCircleHandler.setToCircle(true);
		wxCircleHandler.addToSocialSDK();

		// 参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(this, "100424468",
				"c7394704798a158208a74ab60104f0ba");
		qqSsoHandler.addToSocialSDK();

		// 参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(this,
				"100424468", "c7394704798a158208a74ab60104f0ba");
		qZoneSsoHandler.addToSocialSDK();

		// 设置新浪SSO handler
		mController.getConfig().setSsoHandler(new SinaSsoHandler());
	}

	@Override
	protected int getContentViewID() {
		return R.layout.activity_set;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.set_app_recommend:// 应用推荐

			break;
		case R.id.set_app_update:// 检测更新

			break;
		case R.id.set_share:// 应用分享
			mController.openShare(SetActivity.this, false);
			break;
		case R.id.set_about:// 关于我们

			break;
		case R.id.set_exit:// 退出
			// android.os.Process.killProcess(android.os.Process.myPid());
			// System.exit(0);
			break;

		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		/** 使用SSO授权必须添加如下代码 */
		UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(
				requestCode);
		if (ssoHandler != null) {
			ssoHandler.authorizeCallBack(requestCode, resultCode, data);
		}
	}

}
