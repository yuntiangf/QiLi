package com.example.activity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageService {

	public Bitmap getImage(String url_path) throws Exception{
		if(url_path == null){
			return null;
		}
		
		URL url = new URL(url_path);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(3000);
		
		byte[] data = read(connection.getInputStream());
		Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
		return bitmap;
	}
	
	public static byte[] read(InputStream in) throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int line =0;
		while ((line = in.read(buffer)) != -1) {
			baos.write(buffer, 0, line);
		}
		baos.close();
		in.close();
		return baos.toByteArray();
	} 
}
