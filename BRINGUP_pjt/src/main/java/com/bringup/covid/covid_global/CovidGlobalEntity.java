package com.bringup.covid.covid_global;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CovidGlobalEntity {
	private String areaNm;
	private String areaNmCn;
	private String areaNmEn;
	private String createDt;
	private int natDeathCnt;
	private double natDeathRate;
	private int natDefCnt;
	private String nationNm;
	private String nationNmCn;
	private String nationNmEn;
	private int seq;
	private String stdDay;
	private String updateDt;
}
