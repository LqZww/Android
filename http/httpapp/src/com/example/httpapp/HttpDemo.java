package com.example.httpapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class HttpDemo {
	static String result = "";

	public static String getResult(final String url) {

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		try {
			// ��Ӧ��� = ��������
			final HttpResponse response = httpClient.execute(httpGet);
			// ����һ��˽�з���������Ӧ�����ʾ����
			result = showResponseResult(response);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String postResult(final String baseURL, final String name,
			final String pwd, final String tel, final String qq) {

		NameValuePair pair1 = new BasicNameValuePair("name", name);
		NameValuePair pair2 = new BasicNameValuePair("pwd1", pwd);
		NameValuePair pair3 = new BasicNameValuePair("tel", tel);
		NameValuePair pair4 = new BasicNameValuePair("qq", qq);
		List<NameValuePair> pairList = new ArrayList<NameValuePair>();
		pairList.add(pair1);
		pairList.add(pair2);
		pairList.add(pair3);
		pairList.add(pair4);
		try {
			// ��Ҫ�ͻ��˶�������������
			HttpClient httpClient = new DefaultHttpClient();
			// URLʹ�û���baseURL���ɣ����в���Ҫ�Ӳ���
			HttpPost httpPost = new HttpPost(baseURL);
			// ��������HttpEntity������
			HttpEntity requestHttpEntity = new UrlEncodedFormEntity(pairList);
			// ��HttpEntity������������У�����������������
			httpPost.setEntity(requestHttpEntity);
			// ��Ӧ��� = ��������
			HttpResponse response = httpClient.execute(httpPost);
			// ����һ��˽�з���������Ӧ�����ʾ����
			result = showResponseResult(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String showResponseResult(HttpResponse response) {
		result = "";
		if (null == response) {
			return "error";
		}
		
		HttpEntity httpEntity = response.getEntity();
		try {
			InputStream inputStream = httpEntity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String line = "";
			while (null != (line = reader.readLine())) {
				result += line;
			}
			// System.out.println("================="+result);

			// mResult.setText("������"+method+"�����ύ������\n���������Է���������Ӧ���: \n"
			// +result+"\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
