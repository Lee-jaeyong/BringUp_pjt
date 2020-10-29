<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="./common/header.jsp"%>
<body id="page-top">
	<%@include file="./common/navbar.jsp"%>
	<div class="masthead" style="background-color: #C1F2FF;">
		<div style="font-size: 20px;">
			<MARQUEE id="news"></MARQUEE>
		</div>
		<hr>
		<div class="progress">
			<div class="progress-bar progress-bar-striped progress-bar-animated bg-secondary" style="width: 0%" id="globalMapProgress"></div>
		</div>
		<div style="display: flex; justify-content: center;">
			<div id="regions_div" style="width: 90vw; height: 75vh;"></div>
		</div>
	</div>
	<div class="modal fade" id="newsModal">
		<div class="modal-dialog modal-xl">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="newsTitle"></h4>&nbsp;&nbsp;<a id="newsInto" target="_blank"><button class="btn btn-primary">기사 자세히 보기</button></a>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body" id="newsContent"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">닫 기</button>
				</div>
			</div>
		</div>
	</div>
	<%@include file="./common/footer.jsp"%>
	<%@include file="./common/scripts.jsp"%>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script src="js/global/script.js"></script>
	<script src="js/global/geoChart.js"></script>
</body>
</html>