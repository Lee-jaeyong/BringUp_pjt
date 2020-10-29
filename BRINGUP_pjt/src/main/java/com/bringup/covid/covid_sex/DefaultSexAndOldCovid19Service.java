package com.bringup.covid.covid_sex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.bringup.covid.config.APIRequester;
import com.bringup.covid.config.COVID_19APIRequest.APIBodyParser;
import com.bringup.covid.config.api.APIType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DefaultSexAndOldCovid19Service implements SexAndOldCovid19Service {

	private final APIRequester APIRequester;
	private final APIBodyParser parser;

	@Override
	public Object request(Map<String, String> param, APIType type) throws Exception {
		return APIRequester.request(param, type, parser,(result)->{
			ArrayList<CovidSexAndOldEntity> entity = null;
			try {
				entity = new ObjectMapper().readValue(result.toString(),
					new TypeReference<ArrayList<CovidSexAndOldEntity>>() {
					});
			} catch (IOException e) {
				e.printStackTrace();
			}
			return entity;
		},null);
	}

}
