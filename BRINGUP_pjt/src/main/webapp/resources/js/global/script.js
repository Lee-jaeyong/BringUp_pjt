var newsData;

$(document).ready(function() {
	getData();
})

function getData() {
	$("#globalMapProgress").css('width','0%');
	
	$.ajax({
		url : "./covid/news",
		success : function(res) {
			newsData = res;
			var text = "";
			for (var i = 0; i < res.length; i++) {
				text += "<a href='javascript:showNewsModal(\""+res[i]['id']+"\")' style='color:black; margin-right:30px;'>[ <i class='far fa-bullseye-pointer'></i> 뉴스 ] " + res[i]['title'] + "("+res[i]['wrtDt']+")</a>";
			}
			$("#news").html(text);
		}
	})

	$.ajax({
		url : "./covid/global",
		success : function(res) {
			sessionStorage.setItem("globalCovidData", JSON.stringify(res));
			google.charts.load('current', {
				'packages' : [ 'geochart' ],
				'mapsApiKey' : 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'
			});
			google.charts.setOnLoadCallback(drawRegionsMap);
		}
	})
}

function showNewsModal(id){
	for(var i =0; i<newsData.length; i++){
		if(newsData[i]['id'] === id){
			$("#newsTitle").html("[ " + newsData[i]['countryName'] + " ] " + newsData[i]['title'] + "( "+ newsData[i]['wrtDt'] +" )");
			$("#newsContent").html(newsData[i]['content']);
			$("#newsInto").attr('href','https://www.0404.go.kr/dev/newest_list.mofa?id=&pagenum=1&mst_id=MST0000000000041&ctnm=&div_cd=&st=title&stext=' + newsData[i]['title']);
			$("#newsModal").modal();
			return;
		}
	}
}
