package com.bringup.covid.covid_local;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CovidSearch {

	private String area;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date end;

	public static String defaultSearch() {
		Date now = new Date();
		Date calculDate = new Date(now.getTime() - (86400 * 1000));
		return dateToString(calculDate);
	}

	public static String dateToString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(date);
	}
}
