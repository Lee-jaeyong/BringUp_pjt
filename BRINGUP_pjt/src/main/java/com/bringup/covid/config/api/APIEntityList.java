package com.bringup.covid.config.api;

import java.util.HashMap;

import lombok.Getter;

@Getter
public class APIEntityList {
	private HashMap<APIType, APIEntity> apiList;

	public void add(APIType type, APIEntity entity) {
		apiList.put(type, entity);
	}

	public APIEntity get(APIType type) {
		return apiList.get(type);
	}

	public APIEntityList() {
		apiList = new HashMap<>();
	}
}
