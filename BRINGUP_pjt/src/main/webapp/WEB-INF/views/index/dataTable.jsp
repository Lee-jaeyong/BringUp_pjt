<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/ab0f93c3bf.js"></script>
<script src="js/bar-chart.js"></script>
<script src="js/index/dataTable.js"></script>
<script src="js/datepicker.js"></script>
<script src="js/index/lineChart.js"></script>
<hr />
<input type="date" id="datePicker" class="form-control" value="클릭하여 날짜를 선택하세요">
<br />
<div class="row">
	<div class="col-sm-12 mb-3">
		<button id="searchBtn" class="btn btn-success btn-block" type="button">찾 기</button>
	</div>
</div>
<div id="dataTableProgress" class="spinner-border text-primary" style="margin-bottom: 30px;"></div>
<div>
	<div>
		<div style="background-color: #1abc9c; border-radius: 20px 20px 20px 20px; color: white; height: 2.5rem; margin-bottom: 0.5rem;">
			<h6 style="position: relative; top: 11px;"><span id="checkDate" class="badge badge-warning"></span> 일별 확진환자 발생 및 완치</h6>
		</div>
		<div
			style="width: 100%; border-radius: 20px 20px 20px 20px; background-color: #F6F6F6; margin-bottom: 1rem;">
			<table id="detailTable" class="table table-borderless">
				<thead>
					<tr>
						<th><span class="badge badge-danger">확진환자</span></th>
						<th><span class="badge badge-info">치료중</span></th>
						<th><span class="badge badge-success">격리해제</span></th>
						<th><span class="badge badge-dark">사망</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td id="DEF_CNT_td"></td>
						<td id="ISOL_ING_CNT_td"></td>
						<td id="ISOL_CLEAR_CNT_td"></td>
						<td id="DEATH_CNT_td"></td>
					</tr>
					<tr>
						<td id="sub_DEF_CNT_td"></td>
						<td id="sub_ISOL_ING_CNT_td"></td>
						<td id="sub_ISOL_CLEAR_CNT_td"></td>
						<td id="sub_DEATH_CNT_td"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div style="background-color: #1abc9c; border-radius: 20px 20px 20px 20px; color: white; height: 2.5rem; margin-bottom: 0.5rem;">
			<h6 style="position: relative; top: 11px;">일별 확진환자 발생 및 완치 추세</h6>
		</div>
		<div style="width: 100%; border-radius: 20px 20px 20px 20px; background-color: #F6F6F6; display: flex; justify-content: center;">
			<div id="curve_chart" style="width: 100%; padding: 10px;"></div>
		</div>
	</div>
</div>
<br />
<br />
<br />
<br />
