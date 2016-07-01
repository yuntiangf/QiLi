package com.example.activity;

import android.provider.BaseColumns;

public class DataBase {

	public DataBase() {
	}
	
	public static final class Detection implements BaseColumns{
		public static final String Detection_TABLE_NAME="detection";
		public static final String Detection_ID="detection_id";
		public static final String Detection_QUESTION="detection_question";
		public static final String Detection_TYPE="detection_type";
		public static final String Detection_HABITUS="detection_habitus";
		public static final String Detection_CHOOSE="detection_choose";
	}
}
