package com.example.activity;

import com.example.activity.DataBase.Detection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME="detection.db";
	private static final int DATABASE_VERSION=1;
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE "+Detection.Detection_TABLE_NAME+"("
				+Detection.Detection_ID+" INTEGER PRIMARY KEY ,"
				+Detection.Detection_QUESTION+"	TEXT,"
				+Detection.Detection_TYPE+"	INTEGER,"
				+Detection.Detection_HABITUS+"	TEXT,"
				+Detection.Detection_CHOOSE+"	TEXT);");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		super.onOpen(db);
	}
	
}
