package com.bringup.covid.covid_sex;

import java.util.Map;

import com.bringup.covid.config.api.APIType;

public interface SexAndOldCovid19Service {
	public Object request(Map<String, String> param, APIType type) throws Exception;
}
