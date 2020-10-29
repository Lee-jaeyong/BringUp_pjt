package com.bringup.covid.config;

import java.util.Map;

import com.bringup.covid.config.COVID_19APIRequest.APIBodyParser;
import com.bringup.covid.config.COVID_19APIRequest.APIResultMapper;
import com.bringup.covid.config.COVID_19APIRequest.CustomURL;
import com.bringup.covid.config.api.APIType;

public interface APIRequester {
	Object request(Map<String, String> param, APIType type, APIBodyParser parse, APIResultMapper mapper, CustomURL url)
		throws Exception;
}
