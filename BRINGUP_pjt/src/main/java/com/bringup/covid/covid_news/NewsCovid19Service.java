package com.bringup.covid.covid_news;

import java.util.Map;

import com.bringup.covid.config.api.APIType;

public interface NewsCovid19Service {
	public Object request(Map<String, String> param, APIType type) throws Exception;
}
