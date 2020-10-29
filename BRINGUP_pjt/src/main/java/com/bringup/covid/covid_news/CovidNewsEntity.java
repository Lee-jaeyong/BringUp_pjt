package com.bringup.covid.covid_news;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CovidNewsEntity {
	private String content;
	private String countryEnName;
	private String countryName;
	private String fileUrl;
	private String id;
	private String title;
	private String wrtDt;
}
