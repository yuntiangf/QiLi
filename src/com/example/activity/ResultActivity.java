package com.example.activity;

import com.example.qili.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {
	private String str=null;
	private StringBuffer sb =new StringBuffer();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		
		double[] data=new double[9];
		data=getIntent().getDoubleArrayExtra("data");
		TextView result=(TextView) findViewById(R.id.result);
		
		System.out.println(data[0] +" , "+data[1] +" , "+data[2] +" , "+data[3] +" , "+
				data[4] +" , "+data[5] +" , "+data[6] +" , "+data[7] +" , "+data[8]);
		
		if(judge1(data[8])){
			if(judge5(data[0]) && judge5(data[1]) && judge5(data[2]) &&
					judge5(data[3]) && judge5(data[4]) && judge5(data[5]) &&
					judge5(data[6]) && judge5(data[7])){
				result.setText("您的体质为：平和质 ");
			}else if(judge3(data[0]) && judge3(data[1]) && judge3(data[2]) && 
					 judge3(data[3]) && judge3(data[4]) && judge3(data[5]) && 
					 judge3(data[6]) && judge3(data[7])){
					for(int i = 0; i < data.length-1;i++){
						if(judge4(data[i])){
							sb.append(habitus(i));
							sb.append("、");
						}
					}
					str=sb.substring(0, sb.length()-1);
					result.setText("您的体质为：基本是平和质，有"+str+"倾向。");
			}else if(judge2(data[0]) || judge2(data[1]) || judge2(data[2]) || 
					 judge2(data[3]) || judge2(data[4]) || judge2(data[5]) || 
					 judge2(data[6]) || judge2(data[7])){
				for(int i=0; i < data.length-1;i++){
					if(judge2(data[i])){
						sb.append(habitus(i));
						sb.append("、");
					}
				}
				str=sb.substring(0, sb.length()-1);
				result.setText("您的体质为："+str);
			}
		}else if(data[8] < 60){
			if(judge2(data[0]) || judge2(data[1]) || judge2(data[2]) || 
					 judge2(data[3]) || judge2(data[4]) || judge2(data[5]) || 
					 judge2(data[6]) || judge2(data[7])){
				for(int i=0; i < data.length-1;i++){
					if(judge2(data[i])){
						sb.append(habitus(i));
						sb.append("、");
					}
				}
				str=sb.substring(0, sb.length()-1);
				result.setText("您的体质为："+str);
			}else if(judge4(data[0]) || judge4(data[1]) || judge4(data[2]) || 
				 judge4(data[3]) || judge4(data[4]) || judge4(data[5]) || 
				 judge4(data[6]) || judge4(data[7])){
			for(int i=0; i < data.length-1;i++){
				if(judge4(data[i])){
					sb.append(habitus(i));
					sb.append("、");
				}
			}
			str=sb.substring(0, sb.length()-1);
			result.setText("您的体质为：倾向是"+str);
		}else if(judge5(data[0]) && judge5(data[1]) && judge5(data[2]) &&
				 judge5(data[3]) && judge5(data[4]) && judge5(data[5]) &&
				 judge5(data[6]) && judge5(data[7])){
			result.setText("您未选择完所有题目，得不出正确的结论，请返回继续选择！");
			}
		}
	}
	
	private boolean judge1(double data){
		if(data >= 60){
			return true;
		}else{
			return false;
		}
	}
	private boolean judge2(double data){
		if(data >= 40){
			return true;
		}else{
			return false;
		}
	}
	private boolean judge3(double data){
		if(data < 40){
			return true;
		}else{
			return false;
		}
	}
	private boolean judge4(double data){
		if(data >= 30 && data < 40){
			return true;
		}else{
			return false;
		}
	}
	private boolean judge5(double data){
		if(data < 30){
			return true;
		}else{
			return false;
		}
	}
	
	private String habitus(int i){
		String habitus=null;
		if (i == 0) {
			habitus = "阳虚质";
		} else if (i == 1) {
			habitus = "阴虚质";
		} else if (i == 2) {
			habitus = "气虚质";
		} else if (i == 3) {
			habitus = "痰湿质";
		} else if (i == 4) {
			habitus = "湿热质";
		} else if (i == 5) {
			habitus = "血瘀质";
		} else if (i == 6) {
			habitus = "特禀质";
		} else if (i == 7) {
			habitus = "气郁质";
		}
		return habitus;
	}
}
