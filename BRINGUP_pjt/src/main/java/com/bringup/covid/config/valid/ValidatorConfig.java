package com.bringup.covid.config.valid;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bringup.covid.covid_local.CovidSearchValidator;

@Configuration
public class ValidatorConfig {

	@Bean
	public CovidSearchValidator covidSearchValidator() {
		return new CovidSearchValidator();
	}

}
