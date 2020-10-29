package com.bringup.covid.covid_food;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CovidFoodEntity {
	private String RELAX_UPDATE_DT;
	private String RELAX_RSTRNT_REPRESENT;
	private String RELAX_ADD1;
	private String RELAX_SI_NM;
	private String RELAX_SIDO_NM;
	private String RELAX_SEQ;
	private String RELAX_RSTRNT_CNCL_DT;
	private String RELAX_RSTRNT_REG_DT;
	private String RN;
	private String RELAX_RSTRNT_ETC;
	private String RELAX_GUBUN;
	private String RELAX_USE_YN;
	private String RELAX_GUBUN_DETAIL;
	private String RELAX_ZIPCODE;
	private String RELAX_RSTRNT_NM;
	private String RELAX_ADD2;
	private String RELAX_RSTRNT_TEL;
}
