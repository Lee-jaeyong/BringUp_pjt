package com.bringup.covid.covid_food;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.bringup.covid.config.APIRequester;
import com.bringup.covid.config.COVID_19APIRequest.CustomURL;
import com.bringup.covid.config.api.APIType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FoodCovid_19Service implements FoodStoreCovidService {

	private final APIRequester APIRequester;
	private final String customUrl = "http://211.237.50.150:7080/openapi/5946a5247a220ba4648bd56ded2ba1a512f6b6979772522e808b7badcf246a6b/json/Grid_20200713000000000605_1/";

	@Override
	@SuppressWarnings("unchecked")
	public Object request(Map<String, String> param, APIType type) throws Exception {
		int totalCount = Integer.parseInt(APIRequester.request(param, type, (body) -> {
			return getCalculTotalCount(body);
		}, null, null).toString());
		int requestCnt = (int) Math.ceil((totalCount / 1000.0));
		int request = 0;
		ArrayList<CovidFoodEntity> list = new ArrayList<>();
		for (int i = request; i <= requestCnt; i++) {
			ArrayList<CovidFoodEntity> resultList = (ArrayList<CovidFoodEntity>) APIRequester.request(param, type, (body) -> {
				return parseBody(body);
			}, (result) -> {
				ArrayList<CovidFoodEntity> entity = null;
				try {
					entity = new ObjectMapper().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
						.readValue(result.toString(), new TypeReference<ArrayList<CovidFoodEntity>>() {
						});
				} catch (IOException e) {
					e.printStackTrace();
				}
				return entity;
			}, new FoodCovidCustomUrl(customUrl, Integer.toString(i)));
			list.addAll(resultList);
		}
		return list;
	}

	private String parseBody(String body) {
		return body.substring(body.indexOf("["), body.length() - 2);
	}

	private String getCalculTotalCount(String body) {
		body = body.substring(body.indexOf("totalCnt") + 10, body.length());
		return body.substring(0, body.indexOf(","));
	}

	class FoodCovidCustomUrl implements CustomURL {

		private final String value;
		private final String origin;

		public FoodCovidCustomUrl(String origin, String value) {
			this.value = value;
			this.origin = origin;
		}

		@Override
		public String url() {
			int start = Integer.parseInt(value);
			return origin + ((start * 1000) + 1) + "/" + ((start + 1) * 1000);
		}

	}
}
