package com.bringup.covid.config.api;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

public class APIEntity {
	private String url;
	private String decodeServiceKey;

	public APIEntity(String url, String serviceKey) throws UnsupportedEncodingException, URISyntaxException {
		this.url = url;
		setServiceKey(serviceKey);
	}

	private void setServiceKey(String serviceKey) throws UnsupportedEncodingException, URISyntaxException {
//		this.decodeServiceKey = URLDecoder.decode(serviceKey, "UTF-8");
		this.decodeServiceKey = serviceKey;
	}

	public String getUrl() {
		return url;
	}

	public String getServiceKey() {
		return decodeServiceKey;
	}
}
