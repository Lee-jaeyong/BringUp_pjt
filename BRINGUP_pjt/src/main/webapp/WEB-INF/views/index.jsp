<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="./common/header.jsp" %>
<style>
	@media screen and (max-width: 576px){
	  #DEATH_CNT_div, #piechart {
		margin-left: -70px;
	  }
	}
</style>
<body id="page-top">
	<%@include file="./common/navbar.jsp" %>
	<!--본문구역-->
	<div class="masthead">
		<div class="row" style="margin: 0rem 2rem;">
			<div class="col-sm-4">
			<%@include file="./index/map.jsp" %>
			</div>
			<div class="col-sm-4" style="background-color: white; text-align: center;">
				<%@include file="./index/dataTable.jsp" %>
			</div>
			<div class="col-sm-4">
			<%@include file="./index/charts.jsp" %>
			</div>
		</div>

	</div>
	<%@include file="./common/footer.jsp" %>
	<%@include file="./common/scripts.jsp" %>
</body>
</html>