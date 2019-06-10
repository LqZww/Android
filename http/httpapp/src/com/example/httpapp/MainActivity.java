package com.example.httpapp;

import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	private String TAG = "http";
	private EditText name = null;
	private EditText pwd = null;
	private EditText tel = null;
	private EditText qq = null;
	private Button getButton = null;
	private Button postButton = null;
	private TextView mResult = null;
	private String method = "";
	String result = "";
	// private String baseURL = "http://192.168.1.100:8080/testHttp/servlet/he";
	private String baseURL = "http://192.168.23.1:8080/testHttp/servlet/he";
	//private String baseURL = "http://192.168.23.1:8080/testHttp/img/tomcat.gif";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// �ı����̲߳��ܷ�������Ĳ���
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		name = (EditText) findViewById(R.id.editText1);
		pwd = (EditText) findViewById(R.id.editText4);
		tel = (EditText) findViewById(R.id.editText3);
		qq = (EditText) findViewById(R.id.editText2);

		getButton = (Button) findViewById(R.id.button1);
		postButton = (Button) findViewById(R.id.button2);
		getButton.setOnClickListener(mGetClickListener);
		
		postButton.setOnClickListener(mPostClickListener);

		mResult = (TextView) findViewById(R.id.textView5);
	}

	private OnClickListener mGetClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Log.i(TAG, "GET request");
			String sname = name.getText().toString().trim();
			String stel = tel.getText().toString();
			String sqq = qq.getText().toString();

			String spwd = pwd.getText().toString().trim();
			// �ύ֮ǰ���
			if (sname.equals("") || spwd.equals("")) {
				mResult.setText("���ֺ����벻��Ϊ��");
			} else {
				// ʹ��GET������������,��Ҫ�Ѳ�������URL���棬�ã����ӣ�����֮����&�ָ�
				String url = baseURL + "?name=" + sname + "&pwd1=" + spwd
						+ "&tel=" + stel + "&qq=" + sqq;
				method = "GET";
				// Toast.makeText(MainActivity.this, "" + url,
				// Toast.LENGTH_SHORT).show();
				// getResult(url);
				result = HttpDemo.getResult(url);
				// Toast.makeText(MainActivity.this, "===" +
				// result,Toast.LENGTH_SHORT).show();
				// mResult.setText("\n ���õ���"+method+"���� \n ���������Է�����������: \n");
				System.out.println("--------" + result);
				mResult.setText("");
				mResult.setText("\n" + result + "\n ���õ���" + method + "����\n");
			}
		}
	};
	private OnClickListener mPostClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Log.i(TAG, "POST request");
			// �Ȼ�ȡ�û��ύ�Ĳ���
			String sname = name.getText().toString().trim();
			String stel = tel.getText().toString();
			String sqq = qq.getText().toString();

			String spwd = pwd.getText().toString().trim();
			// �ύ֮ǰ���
			if (sname.equals("") || spwd.equals("")) {
				mResult.setText("���ֺ����벻��Ϊ��");
			} else {
				method = "POST";
				// postResult(baseURL, name, hobby);
				result = HttpDemo.postResult(baseURL, sname, spwd, stel, sqq);
				// mResult.setText("\n ���õ���"+method+"����\n ���������Է�����������: \n");
				mResult.setText("\n" + result + "\n ���õ���" + method + "����\n");
			}
		}
	};

}
