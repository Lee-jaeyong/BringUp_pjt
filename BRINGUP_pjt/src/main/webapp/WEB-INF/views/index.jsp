<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="./common/header.jsp"%>
<style>
@media screen and (max-width: 576px) {
	#DEATH_CNT_div, #piechart {
		margin-left: -70px;
	}
	#chart_div:first-child {
		width: 86vw;
	}
}
</style>
<body id="page-top">
	<%@include file="./common/navbar.jsp"%>
	<!--본문구역-->
	<div class="masthead">
		<div class="row" style="margin: 0rem 0rem;">
			<div class="col-xl-4 col-lg-6">
				<%@include file="./index/map.jsp"%>
			</div>
			<div class="col-xl-4 col-lg-6"
				style="background-color: white; text-align: center;">
				<%@include file="./index/dataTable.jsp"%>
			</div>
			<div class="col-xl-4 col-lg-12">
				<%@include file="./index/charts.jsp"%>
			</div>
		</div>
	</div>
	<%@include file="./common/footer.jsp"%>
	<%@include file="./common/scripts.jsp"%>
</body>
</html>