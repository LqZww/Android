package com.example.httpclientdemo;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView iv;
	public Handler handler = new Handler() {
		 
		public void handleMessage(Message msg) {
			if (msg.what == RESULT_OK) {
				byte[] data = (byte[]) msg.obj;
				Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,data.length);
				iv.setImageBitmap(bitmap);
			}
 
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
		new Thread(new Runnable() { 
			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet("http://192.168.1.100:8080/testHttp/tomcat.gif");
				try {
					HttpResponse httpResponse = httpClient.execute(httpGet);
					if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						byte[] data = EntityUtils.toByteArray(httpResponse.getEntity());
						Message message = Message.obtain();
						message.obj = data;
						message.what = RESULT_OK;
						handler.sendMessage(message);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if (httpClient != null && httpClient.getConnectionManager() != null) {
						httpClient.getConnectionManager().shutdown();
					}
				}
 
			}
		}).start();
	}

}
