package com.bringup.covid.config;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class JSONTypeHttpHeader implements HttpHeaderFactory {

	@Override
	public HttpHeaders get() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));
		return headers;
	}

}
