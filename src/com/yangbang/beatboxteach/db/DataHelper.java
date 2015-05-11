package com.yangbang.beatboxteach.db;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.yangbang.beatboxteach.R;
import com.yangbang.beatboxteach.entity.Title;

public class DataHelper {
	// ���ݿ�����
	private static String DB_NAME = "BeatboxTeach.db";
	// ���ݿ�汾
	public static int DB_VERSION = 2;
	private SQLiteDatabase db;
	private SqliteHelper dbHelper;
	private Context context;

	public DataHelper(Context context) {
		this.context = context;
		dbHelper = new SqliteHelper(context, DB_NAME, null, DB_VERSION);
		db = dbHelper.getWritableDatabase();
	}

	public void Close() {
		db.close();
		dbHelper.close();
	}

//	public List<Title> getTextTeacList(int type) {
//		List<Title> textTeachs = new ArrayList<Title>();
//		Cursor cursor = db.query(SqliteHelper.TB_NAME_TITLE, null, "type="
//				+ type, null, null, null, null);
//		// Cursor cursor = db.query(SqliteHelper.TB_NAME_TEXT_TEACH, null,
//		// "type="
//		// + type, null, null, null, TextTeach.getFieldId() + " DESC");
//		cursor.moveToFirst();
//		while (!cursor.isAfterLast()) {
//			Title textTeach = new Title();
//			textTeach.setTitle_id(cursor.getInt(cursor
//					.getColumnIndex("title_id")));
//			textTeach.setName(cursor.getString(cursor.getColumnIndex("name")));
//			textTeach.setType(cursor.getInt(cursor.getColumnIndex("type")));
//			textTeachs.add(textTeach);
//			cursor.moveToNext();
//		}
//		cursor.close();
//		return textTeachs;
//	}

	public void insertAllData() {
		String[] textTeachs = context.getResources().getStringArray(
				R.array.text_teach);
		for (int i = 0; i < textTeachs.length; i++) {
			ContentValues values = new ContentValues();
			values.put("name", textTeachs[i]);
			values.put("type", 1);
			db.insert(SqliteHelper.TB_NAME_TITLE, null, values);
		}
		String[] videoTeachs = context.getResources().getStringArray(
				R.array.video_teach);
		for (int i = 0; i < videoTeachs.length; i++) {
			ContentValues values = new ContentValues();
			values.put("name", videoTeachs[i]);
			values.put("type", 2);
			db.insert(SqliteHelper.TB_NAME_TITLE, null, values);
		}

		String[] voices1 = context.getResources().getStringArray(
				R.array.text_base_voice_teach);
		for (int i = 0; i < voices1.length; i++) {
			ContentValues values = new ContentValues();
			values.put("title_id", 1);
			values.put("name", voices1[i]);
			values.put("type", 1);
			db.insert(SqliteHelper.TB_NAME_VOICE, null, values);
		}

		String[] voices2 = context.getResources().getStringArray(
				R.array.text_special_voice_teach);
		for (int i = 0; i < voices2.length; i++) {
			ContentValues values = new ContentValues();
			values.put("title_id", 2);
			values.put("name", voices2[i]);
			values.put("type", 1);
			db.insert(SqliteHelper.TB_NAME_VOICE, null, values);
		}

		String[] voices3 = context.getResources().getStringArray(
				R.array.text_segment_teach);
		for (int i = 0; i < voices3.length; i++) {
			ContentValues values = new ContentValues();
			values.put("title_id", 3);
			values.put("name", voices3[i]);
			values.put("type", 1);
			db.insert(SqliteHelper.TB_NAME_VOICE, null, values);
		}

		String[] voices4 = context.getResources().getStringArray(
				R.array.video_double_teach);
		for (int i = 0; i < voices4.length; i++) {
			ContentValues values = new ContentValues();
			values.put("title_id", 4);
			values.put("name", voices4[i]);
			values.put("type", 2);
			db.insert(SqliteHelper.TB_NAME_VOICE, null, values);
		}

	}

	/**
	 * ͨ��������ɸѡ������ȡ��Ӧ�Ķ����б�
	 * 
	 * @param className
	 *            ʵ�������������+������
	 * @param whereMap
	 *            selection��ɸѡ����map���ϣ�
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> List<T> getObjectList(String className,
			Map<String, String> whereMap) {
		// �õ�������
		String tableName = className.substring(className.lastIndexOf(".") + 1,
				className.length());
		// ��������������Class����
		Class c = null;
		try {
			c = Class.forName(className);

		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
		}
		// �õ�����ķ�������
		Method[] methods = c.getMethods();
		List<T> objects = new ArrayList<T>();
		StringBuilder builder = new StringBuilder();
		String selection = null;
		if (whereMap != null && whereMap.size() > 0) {
			for (String key : whereMap.keySet()) {
				builder.append(key + "=" + whereMap.get(key) + " and ");
			}
			selection = builder.toString().substring(0,
					builder.toString().length() - 5);
			Log.i("selection", selection);
		}
		Cursor cursor = db.query(tableName, null, selection, null, null, null,
				null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			// �������ʵ��
			T obj = null;
			try {
				obj = (T) c.newInstance();
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}
			for (Method method : methods) {
				String methodName = method.getName();
				// �������ķ�����set��ͷ
				if (methodName.startsWith("set")) {
					// ���ݷ������ֵõ����ݱ�����ֶε�����
					String columnName = methodName.substring(3,
							methodName.length());
					columnName = columnName.toLowerCase();
					Log.i("columnName", columnName);
					// �õ������Ĳ�������
					Class[] parmts = method.getParameterTypes();
					if (parmts[0] == String.class) {
						// �������ΪString���ͣ���ӽ�����а�������ȡ�ö�Ӧ��ֵ������ִ�и�set����
						try {
							method.invoke(obj, cursor.getString(cursor
									.getColumnIndex(columnName)));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (parmts[0] == int.class) {
						try {
							method.invoke(obj, cursor.getInt(cursor
									.getColumnIndex(columnName)));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			objects.add(obj);
			cursor.moveToNext();
		}
		cursor.close();
		return objects;

	}
}
