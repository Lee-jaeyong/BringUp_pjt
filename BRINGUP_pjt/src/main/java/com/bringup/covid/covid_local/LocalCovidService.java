package com.bringup.covid.covid_local;

import java.util.ArrayList;
import java.util.Map;

import com.bringup.covid.config.api.APIType;

public interface LocalCovidService {
	public Object request(Map<String, String> param, APIType type) throws Exception;
	ArrayList<CovidEntity> requestByArea(String area) throws Exception;
	ArrayList<CovidEntity> totalRequest(Map<String, String> param, APIType type) throws Exception;
}
