function drawRegionsMap() {
	const jsonData = JSON.parse(sessionStorage.getItem("globalCovidData"));

	var array = new Array();
	array.push([ '국 가' ,'확진자(명)', '사망자' ]);
	for (var i = 0; i < jsonData.length; i++) {
		var percent = i / jsonData.length * 100;
		$("#globalMapProgress").css('width',percent + '%');
		array.push([jsonData[i]['nationNmEn'],jsonData[i]['natDefCnt'],jsonData[i]['natDeathCnt']]);
	}
	
	setTimeout(function(){
		var data = google.visualization.arrayToDataTable(array);
		
		var options = {
				colorAxis : {
					colors : [ '#FF7E7E', '#DB0000' ]
				},
				backgroundColor : '#C1F2FF'
		};
		
		var chart = new google.visualization.GeoChart(document
				.getElementById('regions_div'));
		
		chart.draw(data, options);
		
		$("#globalMapProgress").parent().hide();
		
		window.addEventListener('resize', function() {
			chart.draw(data, options);
		}, false);
	},1000);
}