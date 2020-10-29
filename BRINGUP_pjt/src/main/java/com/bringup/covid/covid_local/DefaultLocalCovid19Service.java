package com.bringup.covid.covid_local;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.bringup.covid.config.APIRequester;
import com.bringup.covid.config.COVID_19APIRequest.APIBodyParser;
import com.bringup.covid.config.api.APIType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SuppressWarnings("unchecked")
public class DefaultLocalCovid19Service implements LocalCovidService {

	private final APIRequester APIRequester;
	private final APIBodyParser parser;

	public ArrayList<CovidEntity> totalRequest(Map<String, String> param, APIType type) throws Exception {
		ArrayList<CovidEntity> list = (ArrayList<CovidEntity>) APIRequester.request(param, type, parser, (result) -> {
			ArrayList<CovidEntity> entity = null;
			try {
				entity = new ObjectMapper().readValue(result.toString(), new TypeReference<ArrayList<CovidEntity>>() {
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
			return entity;
		},null);
		return distinctDelete(
			(ArrayList<CovidEntity>) list.stream().filter(c -> c.getGubun().equals("гу╟Х")).collect(Collectors.toList()));
	}

	@Override
	public Object request(Map<String, String> param, APIType type) throws Exception {
		ArrayList<CovidEntity> list = (ArrayList<CovidEntity>) APIRequester.request(param, type, parser, (result) -> {
			ArrayList<CovidEntity> entity = null;
			try {
				entity = new ObjectMapper().readValue(result.toString(), new TypeReference<ArrayList<CovidEntity>>() {
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
			return entity;
		},null);
		ArrayList<CovidEntity> start = new ArrayList<>();
		ArrayList<CovidEntity> before = new ArrayList<>();
		ArrayList<CovidEntity> end = new ArrayList<>();

		for (int i = 1; i <= 18; i++) {
			end.add(list.get(i));
		}

		for (int i = 20; i <= 37; i++) {
			before.add(list.get(i));
		}

		for (int i = list.size() - 18; i < list.size(); i++) {
			start.add(list.get(i));
		}
		percentCalculate(end);

		percentTotalCalculate(before, end);

		HashMap<String, Object> finalResult = new HashMap<String, Object>();
		finalResult.put("start", start);
		finalResult.put("end", end);
		finalResult.put("before", before);
		return finalResult;
	}

	public ArrayList<CovidEntity> requestByArea(String area) throws Exception {
		HashMap<String, String> param = new HashMap<>();
		int oneDay = 86400;
		Date now = new Date();
		Date before1Day = new Date(now.getTime() - (oneDay * 1000));
		Date before7Day = new Date(now.getTime() - (oneDay * 1000 * 7));

		param.put("pageNo", "1");
		param.put("numOfRows", "10");
		param.put("startCreateDt", CovidSearch.dateToString(before7Day));
		param.put("endCreateDt", CovidSearch.dateToString(before1Day));

		ArrayList<CovidEntity> list = (ArrayList<CovidEntity>) APIRequester.request(param, APIType.LOCAL, parser, (result) -> {
			ArrayList<CovidEntity> entity = null;
			try {
				entity = new ObjectMapper().readValue(result.toString(), new TypeReference<ArrayList<CovidEntity>>() {
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
			return entity;
		},null);
		return distinctDelete(
			(ArrayList<CovidEntity>) list.stream().filter(c -> c.getGubun().equals(area)).collect(Collectors.toList()));
	}

	private ArrayList<CovidEntity> distinctDelete(ArrayList<CovidEntity> list) {
		ArrayList<CovidEntity> result = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			boolean flag = true;
			for (int j = 0; j < result.size(); j++) {
				if (result.get(j).getCreateDt().substring(0, 10).equals(list.get(i).getCreateDt().substring(0, 10))) {
					flag = false;
					break;
				}
			}
			if (flag) {
				result.add(list.get(i));
			}
		}
		return result;
	}

	private void percentCalculate(ArrayList<CovidEntity> list) {
		CovidEntity total = list.get(list.size() - 1);
		for (int i = 0; i < list.size() - 1; i++) {
			CovidEntity entity = list.get(i);
			if (total.getDeathCnt() != 0) {
				entity.setTotalDeatchCntPercent(entity.getDeathCnt() * 100 / total.getDeathCnt());
			} else {
				entity.setTotalDeatchCntPercent(0);
			}
			if (total.getDefCnt() != 0) {
				entity.setTotalDefCntPercent(entity.getDefCnt() * 100 / total.getDefCnt());
			} else {
				entity.setTotalDefCntPercent(0);
			}
			if (total.getIncDec() != 0) {
				entity.setTotalIncDecPercent(entity.getIncDec() * 100 / total.getIncDec());
			} else {
				entity.setTotalIncDecPercent(0);
			}
			if (total.getIsolClearCnt() != 0) {
				entity.setTotalIsolClearCntPercent(entity.getIsolClearCnt() * 100 / total.getIsolClearCnt());
			} else {
				entity.setTotalIsolClearCntPercent(0);
			}
			if (total.getIsolIngCnt() != 0) {
				entity.setTotalIsolIngCntPercent(entity.getIsolIngCnt() * 100 / total.getIsolIngCnt());
			} else {
				entity.setTotalIsolIngCntPercent(0);
			}
			if (entity.getLocalOccCnt() != 0) {
				entity.setTotalLocalOccCntPercent(entity.getLocalOccCnt() * 100 / total.getLocalOccCnt());
			} else {
				entity.setTotalLocalOccCntPercent(0);
			}
			if (total.getOverFlowCnt() != 0) {
				entity.setTotalOverFlowCntPercent(entity.getOverFlowCnt() * 100 / total.getOverFlowCnt());
			} else {
				entity.setTotalOverFlowCntPercent(0);
			}
		}
	}

	private void percentTotalCalculate(ArrayList<CovidEntity> before, ArrayList<CovidEntity> search) {
		percentCalculate(before);

		for (int i = 0; i < before.size() - 1; i++) {
			CovidEntity beforeEntity = before.get(i);
			CovidEntity searchEntity = search.get(i);
			searchEntity.beforeInit();
			searchEntity
				.setBeforeDeatchCntPercent(searchEntity.getTotalDeatchCntPercent() - beforeEntity.getTotalDeatchCntPercent());
			searchEntity.setBeforeDefCntPercent(searchEntity.getTotalDefCntPercent() - beforeEntity.getTotalDefCntPercent());
			searchEntity.setBeforeIncDecPercent(searchEntity.getTotalIncDecPercent() - beforeEntity.getTotalIncDecPercent());
			searchEntity.setBeforeIsolClearCntPercent(
				searchEntity.getTotalIsolClearCntPercent() - beforeEntity.getTotalIsolClearCntPercent());
			searchEntity
				.setBeforeIsolIngCntPercent(searchEntity.getTotalIsolIngCntPercent() - beforeEntity.getTotalIsolIngCntPercent());
			searchEntity.setBeforeLocalOccCntPercent(
				searchEntity.getTotalLocalOccCntPercent() - beforeEntity.getTotalLocalOccCntPercent());
			searchEntity.setBeforeOverFlowCntPercent(
				searchEntity.getTotalOverFlowCntPercent() - beforeEntity.getTotalOverFlowCntPercent());
		}

		before.forEach(c -> {
			c.refresh();
		});
	}
}
