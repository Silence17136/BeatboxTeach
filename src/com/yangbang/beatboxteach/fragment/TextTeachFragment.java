package com.yangbang.beatboxteach.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.yangbang.beatboxteach.R;
import com.yangbang.beatboxteach.VoiceActivity;
import com.yangbang.beatboxteach.adapter.TextTeachAdapter;
import com.yangbang.beatboxteach.base.MyApplication;
import com.yangbang.beatboxteach.entity.Title;

public class TextTeachFragment extends Fragment implements OnItemClickListener {
	int type;
	View view;
	ListView fragment_text_teach_lv;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		type = getArguments().getInt("type");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (type == 1) {
			view = LayoutInflater.from(getActivity()).inflate(
					R.layout.fragment_text_teach, null);
			fragment_text_teach_lv = (ListView) view
					.findViewById(R.id.fragment_text_teach_lv);
			// Class<Title> class1 = Title.class;
			// List<Title> textTeachs = new ArrayList<Title>();
			// Map<String, String> whereMap = new HashMap<String, String>();
			// whereMap.put("type", type + "");
			// textTeachs = MyApplication.dataHelper.getObjectList(
			// class1.getName(), whereMap);
			List<Title> textTeachs = null;
			try {
				textTeachs = MyApplication.dbUtils.findAll(Selector.from(Title.class).where(
						"type", "=", type));
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fragment_text_teach_lv.setAdapter(new TextTeachAdapter(
					getActivity(), textTeachs));
		} else if (type == 2) {
			view = LayoutInflater.from(getActivity()).inflate(
					R.layout.fragment_text_teach, null);
			fragment_text_teach_lv = (ListView) view
					.findViewById(R.id.fragment_text_teach_lv);

			// Class<Title> class1 = Title.class;
			// List<Title> textTeachs = new ArrayList<Title>();
			// Map<String, String> whereMap = new HashMap<String, String>();
			// whereMap.put("type", type + "");
			// textTeachs = MyApplication.dataHelper.getObjectList(
			// class1.getName(), whereMap);
			List<Title> textTeachs = null;
			try {
				textTeachs = MyApplication.dbUtils.findAll(Selector.from(Title.class).where(
						"type", "=", type));
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fragment_text_teach_lv.setAdapter(new TextTeachAdapter(
					getActivity(), textTeachs));
			//
			// fragment_text_teach_lv.setAdapter(new TextTeachAdapter(
			// getActivity(), getActivity().getResources().getStringArray(
			// R.array.video_teach)));
		}
		fragment_text_teach_lv.setOnItemClickListener(this);
		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Title title = (Title) parent.getAdapter().getItem(position);
		Intent intent = new Intent(getActivity(), VoiceActivity.class);
		intent.putExtra("title", title);
		startActivity(intent);
	}

}
