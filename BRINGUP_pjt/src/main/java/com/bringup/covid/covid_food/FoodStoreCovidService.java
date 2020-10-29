package com.bringup.covid.covid_food;

import java.util.Map;

import com.bringup.covid.config.api.APIType;

public interface FoodStoreCovidService {
	public Object request(Map<String, String> param, APIType type) throws Exception;
}
