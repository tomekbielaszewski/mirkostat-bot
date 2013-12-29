package org.grizz.service;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.grizz.utils.MD5;

public class URLEncoder {
	private String secretKey;
	
	public URLEncoder(final String secretKey) {
		this.secretKey = secretKey;
	}
	
	@SuppressWarnings("unchecked")
	public String encode(String url) {
		return encode(url, MapUtils.EMPTY_MAP);
	}
	
	public String encode(String url, Map<String, String> post) {
		String postContent = preparePostContent(post);
		
		return MD5.encode(secretKey + url + postContent);
	}

	private String preparePostContent(Map<String, String> post) {
		//TODO: Implementacja odpowiedniego podpisywania zapytan z zawartoscia POST
		return "";
	}
}
