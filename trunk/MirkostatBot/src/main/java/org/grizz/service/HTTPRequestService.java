package org.grizz.service;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

@Service
public class HTTPRequestService {
	
	public String sendGet(String url) {
		String responseContent = "";
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		
		try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
			responseContent = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return responseContent;
	}
	
//	public String sendPost(String url, Map<String, String> postContent) {
//		CloseableHttpClient httpClient = HttpClients.createDefault();
//		HttpPost httpPost = new HttpPost(url);
//		httpPost.setConfig(config);
//	}
}
