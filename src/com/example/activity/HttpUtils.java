package com.example.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtils {
	public static void main(String[] args) {
		HttpUtils httpurl = new HttpUtils();
		String str = httpurl.sendPost("http://192.168.1.101:8080/Attendace/userController/gettypebyid", 
				 "id=6");
		System.out.println(str);
	}
	
	//获取指定URL的响应字符串 
	public static String sendPost(String url_path,String param){
		PrintWriter pw = null;
		BufferedReader br = null;
		String result = "";
		try {
			URL url = new URL(url_path);		//URL对象
			URLConnection connect =url.openConnection();	//使用URL打开一个链接 
			connect.setDoInput(true);						//允许输入流，即允许下载
			connect.setDoOutput(true);						//允许输出流，即允许上传
//			connect.setRequestProperty("Content-type", "application/x-java-serialized-object");
//			connect.setRequestMethod("POST");
//			connect.connect();
			//此处getOutputStream会隐含的进行connect(即：如同调用上面的connect()方法，所以在开发中不调用上述的connect()也可以)
			pw = new PrintWriter(connect.getOutputStream());
			pw.print(param);
			pw.flush();
			br = new BufferedReader(new InputStreamReader(connect.getInputStream()));
			String line = "";
			while((line = br.readLine()) != null){
				result += line;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
				try {
					if (pw != null) {
						pw.close();
					}
					if (br != null) {
						br.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return result;
	}
}
