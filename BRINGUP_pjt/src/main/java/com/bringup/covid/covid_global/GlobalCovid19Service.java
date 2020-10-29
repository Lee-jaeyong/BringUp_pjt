package com.bringup.covid.covid_global;

import java.util.Map;

import com.bringup.covid.config.api.APIType;

public interface GlobalCovid19Service {
	public Object request(Map<String, String> param, APIType type) throws Exception;
}
