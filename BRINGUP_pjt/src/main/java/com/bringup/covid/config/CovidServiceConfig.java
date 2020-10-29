package com.bringup.covid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.bringup.covid.config.COVID_19APIRequest.APIBodyParser;
import com.bringup.covid.covid_food.FoodCovid_19Service;
import com.bringup.covid.covid_food.FoodStoreCovidService;
import com.bringup.covid.covid_global.DefaultGlobalCovid19Service;
import com.bringup.covid.covid_global.GlobalCovid19Service;
import com.bringup.covid.covid_local.DefaultLocalCovid19Service;
import com.bringup.covid.covid_local.LocalCovidService;
import com.bringup.covid.covid_news.DefaultNewsCovid19Service;
import com.bringup.covid.covid_news.NewsCovid19Service;
import com.bringup.covid.covid_sex.DefaultSexAndOldCovid19Service;
import com.bringup.covid.covid_sex.SexAndOldCovid19Service;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableAspectJAutoProxy
public class CovidServiceConfig {

	private final APIRequester apiRequester;
	private final APIBodyParser bodyParser;

	@Bean(name = "news_covid_19Service")
	public NewsCovid19Service news_covid_19Service() {
		return new DefaultNewsCovid19Service(apiRequester, bodyParser);
	}

	@Bean(name = "local_covid_19Service")
	public LocalCovidService local_covid_19Service() {
		return new DefaultLocalCovid19Service(apiRequester, bodyParser);
	}

	@Bean(name = "food_covid_19Service")
	public FoodStoreCovidService area_covid_19Service() {
		return new FoodCovid_19Service(apiRequester);
	}

	@Bean(name = "sexAndOldCovid19Service")
	public SexAndOldCovid19Service sexAndOldCovid19Service() {
		return new DefaultSexAndOldCovid19Service(apiRequester, bodyParser);
	}

	@Bean(name = "globalCovid19Service")
	public GlobalCovid19Service globalCovid19Service() {
		return new DefaultGlobalCovid19Service(apiRequester, bodyParser);
	}
}
