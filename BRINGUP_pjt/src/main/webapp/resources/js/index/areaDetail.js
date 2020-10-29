$(document).ready(function(e) {
});

function getAreaDeteil(area){
	$.ajax({
		url : "./covid/local/" + area,
		success : function(res){
			$("#chart_div").html('<div class="spinner-border text-primary"></div></br></br></br></br></br>');
			
			localStorage.setItem('area', JSON.stringify(res));
			localStorage.setItem('selectArea', area);
			
			google.charts.load('current', {'packages':['bar']});
    	    google.charts.setOnLoadCallback(drawAreaDetailChart);
			$("#detailModal").modal();
		}
	})
}

