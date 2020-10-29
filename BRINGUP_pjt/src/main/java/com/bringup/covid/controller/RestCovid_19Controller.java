package com.bringup.covid.controller;

import static com.bringup.covid.covid_local.CovidSearch.dateToString;
import static com.bringup.covid.covid_local.CovidSearch.defaultSearch;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bringup.covid.config.api.APIType;
import com.bringup.covid.covid_food.FoodStoreCovidService;
import com.bringup.covid.covid_global.GlobalCovid19Service;
import com.bringup.covid.covid_local.CovidSearch;
import com.bringup.covid.covid_local.CovidSearchValidator;
import com.bringup.covid.covid_local.LocalCovidService;
import com.bringup.covid.covid_news.NewsCovid19Service;
import com.bringup.covid.covid_sex.SexAndOldCovid19Service;

@RestController
@RequestMapping("servlet/covid")
public class RestCovid_19Controller {

	@Resource(name = "local_covid_19Service")
	private LocalCovidService local_covid_19Service;

	@Resource(name = "food_covid_19Service")
	private FoodStoreCovidService foodStoreCovidService;

	@Resource(name = "sexAndOldCovid19Service")
	private SexAndOldCovid19Service sexAndOldCovid19Service;

	@Resource(name = "globalCovid19Service")
	private GlobalCovid19Service globalCovid19Service;

	@Resource(name = "news_covid_19Service")
	private NewsCovid19Service newsCovid19Service;

	@Autowired
	private CovidSearchValidator covidValidator;

	@GetMapping("news")
	public ResponseEntity<Object> newsCovid19() throws Exception {
		Map<String, String> param = new HashMap<String, String>();
		param.put("pageNo", "1");
		param.put("numOfRows", "10");
		Object result = newsCovid19Service.request(param, APIType.NEWS);
		return ResponseEntity.ok(result);
	}

	@GetMapping("global")
	public ResponseEntity<Object> globalCovid19() throws Exception {
		Map<String, String> param = new HashMap<String, String>();
		param.put("pageNo", "1");
		param.put("numOfRows", "10");
		param.put("startCreateDt", defaultSearch());
		param.put("endCreateDt", defaultSearch());
		Object result = globalCovid19Service.request(param, APIType.GLOBAL);
		return ResponseEntity.ok(result);
	}

	@GetMapping("sex-and-old")
	public ResponseEntity<Object> sexAndOldCovid19(CovidSearch search, Errors errors) throws Exception {
		covidValidator.validate(search, errors);
		if (errors.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		Map<String, String> param = new HashMap<String, String>();
		param.put("pageNo", "1");
		param.put("numOfRows", "10");
		param.put("startCreateDt", "20200410");
		param.put("endCreateDt", dateToString(search.getEnd()));
		Object result = sexAndOldCovid19Service.request(param, APIType.SEX);
		return ResponseEntity.ok(result);
	}

	@GetMapping("food")
	public ResponseEntity<Object> covid_19Area(@RequestParam String area) throws Exception {
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("RELAX_SI_NM", URLDecoder.decode(area, "UTF-8"));
		return ResponseEntity.ok(foodStoreCovidService.request(param, APIType.FOOD));
	}

	@GetMapping("total")
	public ResponseEntity<Object> covid_total(CovidSearch search, Errors errors) throws Exception {
		covidValidator.validate(search, errors);
		if (errors.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		Map<String, String> param = new HashMap<String, String>();
		param.put("pageNo", "1");
		param.put("numOfRows", "10");
		param.put("startCreateDt", dateToString(new Date(search.getEnd().getTime() - (86400 * 1000 * 8))));
		param.put("endCreateDt", dateToString(search.getEnd()));
		Object result = local_covid_19Service.totalRequest(param, APIType.LOCAL);
		return ResponseEntity.ok(result);
	}

	@GetMapping("local")
	public ResponseEntity<Object> covid_19(CovidSearch search, Errors errors) throws Exception {
		if(search.getArea() != null) {
			if (!isExistArea(URLDecoder.decode(search.getArea(), "UTF-8"))) {
				return ResponseEntity.badRequest().body(URLDecoder.decode(search.getArea(), "UTF-8"));
			}
			return ResponseEntity.ok(local_covid_19Service.requestByArea(URLDecoder.decode(search.getArea(), "UTF-8")));
		}
		
		covidValidator.validate(search, errors);
		if (errors.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		Map<String, String> param = new HashMap<String, String>();
		param.put("pageNo", "1");
		param.put("numOfRows", "10");
		param.put("startCreateDt", "20200410");
		param.put("endCreateDt", dateToString(search.getEnd()));
		Object result = local_covid_19Service.request(param, APIType.LOCAL);
		return ResponseEntity.ok(result);
	}

	private boolean isExistArea(String area) {
		if (area.equals("제주") || area.equals("경남") || area.equals("경북") || area.equals("전남") || area.equals("전북")
			|| area.equals("충남") || area.equals("충북") || area.equals("강원") || area.equals("경기") || area.equals("세종")
			|| area.equals("울산") || area.equals("대전") || area.equals("광주") || area.equals("인천") || area.equals("대구")
			|| area.equals("부산") || area.equals("서울")) {
			return true;
		}
		return false;
	}
}
