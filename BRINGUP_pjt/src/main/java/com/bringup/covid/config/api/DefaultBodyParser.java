package com.bringup.covid.config.api;

import com.bringup.covid.config.COVID_19APIRequest.APIBodyParser;

public class DefaultBodyParser implements APIBodyParser{

	@Override
	public String pasre(String body) {
		int start = body.indexOf("items") + 15;
		int end = body.indexOf("numOfRows") - 3;
		return body.substring(start, end);
	}

}
