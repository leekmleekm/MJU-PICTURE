package com.remembook.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AppSqliteHandler {
	AppSqliteOpenHelper helper;
	SQLiteDatabase db;

	// 초기화 작업
	public AppSqliteHandler(Context context) {
		helper = new AppSqliteOpenHelper(context, "BookList.sqlite", null, 1);
	}

	// 테이블 열기
	public static AppSqliteHandler open(Context context) {
		return new AppSqliteHandler(context);
	}

	// 테이블 닫기
	public void close() {
		db.close();
	}

	// 저장
	public void insert(String image, String title, String author,
			String publisher, String isbn) {
		db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("_image", image);
		values.put("_title", title);
		values.put("_author", author);
		values.put("_publisher", publisher);
		values.put("_isbn", isbn);
		db.insert("BookList", null, values);
	}

	// 수정
	public void update(String image, String title, String author,
			String publisher, int isbn) {
		db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		db.update("BookList", values, "_title=?", new String[] { title });
	}

	// 삭제
	public void delete(String title) {
		db = helper.getWritableDatabase();
		db.delete("BookList", "_title=?", new String[] { title });
	}

	// 검색
	public Cursor select() {
		db = helper.getReadableDatabase();
		Cursor c = db.query("BookList", null, null, null, null, null, null);
		return c;
	}
}
