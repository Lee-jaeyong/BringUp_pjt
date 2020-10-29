package com.bringup.covid.config.api;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.bringup.covid.config.APIRequester;
import com.bringup.covid.config.COVID_19APIRequest;
import com.bringup.covid.config.HttpHeaderFactory;
import com.bringup.covid.config.JSONTypeHttpHeader;
import com.bringup.covid.config.COVID_19APIRequest.APIBodyParser;

@Configuration
@PropertySource("classpath:config/api-key.properties")
public class Config {

	@Value("${covid_local.api.url}")
	private String local_url;

	@Value("${covid_local.api.serviceKey}")
	private String local_service;

	@Value("${covid_sex.api.url}")
	private String sex_url;

	@Value("${covid_sex.api.serviceKey}")
	private String sex_service;

	@Value("${covid_global.api.url}")
	private String global_url;

	@Value("${covid_global.api.serviceKey}")
	private String global_service;

	@Value("${covid_news.api.url}")
	private String news_url;

	@Value("${covid_news.api.serviceKey}")
	private String news_service;

	@Value("${covid_food.api.url}")
	private String food_url;

	@Value("${covid_food.api.serviceKey}")
	private String food_service;

	@Bean
	public APIEntityList APIEntityList() throws UnsupportedEncodingException, URISyntaxException {
		APIEntityList entityList = new APIEntityList();
		entityList.add(APIType.LOCAL, new APIEntity(local_url, local_service));
		entityList.add(APIType.SEX, new APIEntity(sex_url, sex_service));
		entityList.add(APIType.GLOBAL, new APIEntity(global_url, global_service));
		entityList.add(APIType.NEWS, new APIEntity(news_url, news_service));
		entityList.add(APIType.FOOD, new APIEntity(food_url, food_service));
		return entityList;
	}

	@Bean
	public HttpHeaderFactory jsonTypeHttpHeader() {
		return new JSONTypeHttpHeader();
	}

	@Bean
	public APIRequester apiRequester() throws UnsupportedEncodingException, URISyntaxException {
		return new COVID_19APIRequest(APIEntityList(), jsonTypeHttpHeader());
	}
	
	@Bean
	public APIBodyParser bodyParser() {
		return new DefaultBodyParser();
	}
}
