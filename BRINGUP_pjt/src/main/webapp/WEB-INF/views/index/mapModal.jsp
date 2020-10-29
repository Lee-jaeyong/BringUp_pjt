<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script src="js/detailChart.js"></script>
<div class="modal fade" id="detailModal">
	<div class="modal-dialog modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<span style="position: relative; top: -2px;"
						class="badge badge-secondary" id="selectAreaNM"></span> 지역 상세 정보
				</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body" style="text-align: center;">
				<div id="chart_div"></div>
				<br/>
				<div class="container">
				<div class="form-check-inline">
					<div style="float: left;width: 20px; height: 20px; background: #1b9e77; border-radius: 20%;"></div>
					&nbsp;사망자
				</div>
				<div class="form-check-inline">
					<div style="float: left;width: 20px; height: 20px; background: #d95f02; border-radius: 20%;"></div>
					&nbsp;확진자
				</div>
				<div class="form-check-inline">
					<div style="float: left;width: 20px; height: 20px; background: #7570b3; border-radius: 20%;"></div>
					&nbsp;격리 해제
				</div>
				<div class="form-check-inline">
					<div style="float: left;width: 20px; height: 20px; background: #F361DC; border-radius: 20%;"></div>
					&nbsp;치료중
				</div>
				<br/>
				<br/>
					<table class="table text-center table-responsive" id="detailTableSection">
						<thead>
							<th>
								날짜
							</th>
							<th>
								확진자
							</th>
							<th>
								사망자
							</th>
							<th>
								격리 해제	
							</th>
							<th>
								치료중
							</th>
						</thead>
						<tbody id="detailTable">
						</tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">닫 기</button>
			</div>
		</div>
	</div>
</div>
<script>
	$(document).ready(function(){
		if($(window).width() < 768){
			$("#detailTableSection").addClass("table-responsive");
		}else{
			$("#detailTableSection").removeClass("table-responsive");
		}
	})
</script>
