package com.example.activity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class DetectionActivity extends Activity {
	protected DBHelper db=null;
	protected SQLiteDatabase mDB=null;
	protected Cursor mCursor=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		db=new DBHelper(this.getApplicationContext());
		mDB=db.getWritableDatabase();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(mDB!=null){
			mDB.close();
		}
		if(db!=null){
			db.close();
		}
	}
	
	
	
}
