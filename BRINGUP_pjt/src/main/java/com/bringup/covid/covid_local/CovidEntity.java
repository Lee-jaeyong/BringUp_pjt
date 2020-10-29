package com.bringup.covid.covid_local;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CovidEntity {
	private String createDt;
	private int deathCnt;
	private int defCnt;
	private String gubun;
	private String gubunCn;
	private String gubunEn;
	private int incDec;
	private int isolClearCnt;
	private int isolIngCnt;
	private int localOccCnt;
	private int overFlowCnt;
	private String qurRate;
	private int seq;
	private String stdDay;

	@JsonInclude(value = Include.NON_NULL)
	private String updateDt;

	@JsonInclude(value = Include.NON_NULL)
	private Integer totalDeatchCntPercent;
	@JsonInclude(value = Include.NON_NULL)
	private Integer totalDefCntPercent;
	@JsonInclude(value = Include.NON_NULL)
	private Integer totalIncDecPercent;
	@JsonInclude(value = Include.NON_NULL)
	private Integer totalIsolClearCntPercent;
	@JsonInclude(value = Include.NON_NULL)
	private Integer totalIsolIngCntPercent;
	@JsonInclude(value = Include.NON_NULL)
	private Integer totalLocalOccCntPercent;
	@JsonInclude(value = Include.NON_NULL)
	private Integer totalOverFlowCntPercent;

	@JsonInclude(value = Include.NON_NULL)
	private Integer beforeDeatchCntPercent;
	@JsonInclude(value = Include.NON_NULL)
	private Integer beforeDefCntPercent;
	@JsonInclude(value = Include.NON_NULL)
	private Integer beforeIncDecPercent;
	@JsonInclude(value = Include.NON_NULL)
	private Integer beforeIsolClearCntPercent;
	@JsonInclude(value = Include.NON_NULL)
	private Integer beforeIsolIngCntPercent;
	@JsonInclude(value = Include.NON_NULL)
	private Integer beforeLocalOccCntPercent;
	@JsonInclude(value = Include.NON_NULL)
	private Integer beforeOverFlowCntPercent;

	public void beforeInit() {
		beforeDeatchCntPercent = 0;
		beforeDefCntPercent = 0;
		beforeIncDecPercent = 0;
		beforeIsolClearCntPercent = 0;
		beforeIsolIngCntPercent = 0;
		beforeLocalOccCntPercent = 0;
		beforeOverFlowCntPercent = 0;
	}

	public void refresh() {
		this.totalDeatchCntPercent = null;
		this.totalDefCntPercent = null;
		this.totalIncDecPercent = null;
		this.totalIsolClearCntPercent = null;
		this.totalIsolIngCntPercent = null;
		this.totalLocalOccCntPercent = null;
		this.totalOverFlowCntPercent = null;
	}
}
