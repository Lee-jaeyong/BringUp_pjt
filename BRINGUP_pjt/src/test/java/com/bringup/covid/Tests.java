package com.bringup.covid;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bringup.covid.config.CovidServiceConfig;
import com.bringup.covid.config.api.APIType;
import com.bringup.covid.config.api.Config;
import com.bringup.covid.covid_food.CovidFoodEntity;
import com.bringup.covid.covid_food.FoodStoreCovidService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Config.class, CovidServiceConfig.class })
public class Tests {

	@Autowired
	private FoodStoreCovidService service;

	@Test
	public void test() throws Exception {
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("RELAX_SI_NM", "서울특별시");
		@SuppressWarnings("unchecked")
		ArrayList<CovidFoodEntity> list = (ArrayList<CovidFoodEntity>) service.request(param, APIType.FOOD);
		System.out.println(list.size());
	}
}
