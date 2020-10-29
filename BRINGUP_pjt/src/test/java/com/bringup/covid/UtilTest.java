package com.bringup.covid;

import java.util.Date;

import org.junit.Test;

import com.bringup.covid.covid_local.CovidSearch;

public class UtilTest {

	int oneDay = 86400;

	@Test
	public void test_1() {
		Date now = new Date();
		Date before7Day = new Date(now.getTime() - (oneDay * 1000 * 7));
		System.out.println(CovidSearch.dateToString(before7Day));
	}
}
