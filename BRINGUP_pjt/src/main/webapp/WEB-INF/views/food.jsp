<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="./common/header.jsp"%>
<body id="page-top">
	<%@include file="./common/navbar.jsp"%>
	<div class="masthead">
		<div class="info"
			style="margin: -5px 33px 18px 33px; border: 1px solid #2924BD; background-color: #F0FAFF; padding: 20px 30px 10px 30px;">
			<h5>안심식당이란?</h5>
			<div class="row">
				<div class="col-lg-6">
					<ul>
						<li>코로나19 확산에 따라 생활방역 수칙을 준수하는 식당을 안심식당으로 지정하였습니다.</li>
						<li>안심식당 지정업소는 안심식당 스티커가 배부됩니다.</li>
						<li>지정요건 : (1)덜어먹기 가능한 도구 비치 및 제공 (2)위생적인 수저 관리 (3)종사자 마스크 착용</li>
					</ul>
				</div>
				<div class="col-lg-6"
					style="display: flex; justify-content: flex-end;">
					<button id="myAroundBtn" type="button" class="btn btn-primary"
						style="height: 3.5rem">내 주변 안심식당 보기</button>
				</div>
			</div>
		</div>
		<div style="margin: -5px 33px 18px 33px; padding: 20px 30px 10px 30px;">
			<div class="progress">
				<div class="progress-bar progress-bar-striped progress-bar-animated" id="mapProgress" style="width: 0%"></div>
			</div>
		</div>
		<div class="row" style="margin: 0rem 2rem;">
			<div class="col-lg-7 col-md-12 mb-4" id="kakaoMap" style="width: 100%; height: 45vw; border: 1px solid #2924BD;"></div>
			<div id="infoTable" class="col-lg-5 col-md-12">
				<table id="dataTable"
					class="table table-striped table-bordered table-sm"
					style="width: 100%;">
					<thead style="background-color: #F0FAFF;">
						<tr>
							<th>상호명</th>
							<th>주소</th>
							<th>연락처</th>
							<th>지정일</th>
						</tr>
					</thead>
					<tbody id="dataTableBody">
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<%@include file="./common/footer.jsp"%>
	<%@include file="./common/scripts.jsp"%>
	<script type="text/javascript"
		src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=5d93b06493c0fa86c9dc8f0bdb86181d&libraries=services,clusterer"></script>
	<script
		src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap4.min.js"></script>
	<script src="js/food/kakaoMap.js"></script>
	<script>
		
	</script>
</body>
</html>