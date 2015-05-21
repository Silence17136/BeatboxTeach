package com.yangbang.beatboxteach.util;

import java.util.HashMap;
import java.util.Map;

public class DataFactory {
	//文字教学系列
	public String dataWeb1="http://zhidao.baidu.com/link?url=hMJzPNDslglS_h2r26b4IJ1Yl8h6VAyRUCoNQ2lbtq0hnpiyRMfW54QhGttYD-9oAXinRqPAojdsaSHT7n3W2q";
	public String dataWeb2="http://wenku.baidu.com/link?url=A8P2ZKEz01tG_zpSoZOXZg_oL8QTf9Iyhifs-tqq3DdisX_ZimOtoJzfZ03vcOpSMeweH8kMeNtxjeRkQrmeUzVouM2Z_GLPYNErryQRR_G";
	public String dataWeb3="http://tieba.baidu.com/p/2979373855";
	//double教学系列
	public String dataWeb4="http://www.youku.com/playlist_show/id_22597454_ascending_1_mode_detail_page_1.html";
	//Krnfx教学系列
	public String dataWeb5="http://www.youku.com/playlist_show/id_23213969.html?sf=10100";
	
	public static Map<String, Map<String, String>> mapVoice = new HashMap<String, Map<String, String>>();
	public static String text_title_str[] = { "基础音篇", "特殊音篇", "Beat篇" };

	public static String video_title_str[] = { "double教学篇", "飞机教学篇", "肥king教学篇" };
	public static String text_voice_str[] = { "大鼓(kick)", "小鼓(snare)",
			"镲(hi-hit)" };

	public static String video_voice_str[] = {};

	public static String text_voice_teach_str[] = {};

	public static String video_voice_teach_str[] = {};
	
	public static void initData(){
		for(String str:text_title_str){
		}
	}
}
