function drawChart() {
	const jsonData = JSON.parse(sessionStorage.getItem('covidOldData'));
	var array = new Array();
	array.push([ '', '' ]);
	for (var i = 0; i < jsonData.length; i++) {
		array.push([ jsonData[i]['gubun'] + "", jsonData[i]['confCase'] ]);
	}
	var data = google.visualization.arrayToDataTable(array);

	var options = {
		height : 350,
		width: $(window).width() < 768 ? 400 : 700,
		title : '연령별 확진자 현황(단위:[세])'
	};

	var chart = new google.visualization.PieChart(document
			.getElementById('piechart'));

	chart.draw(data, options);
	
	function resizeChart () {
		chart.draw(data, options);
	}
	if (document.addEventListener) {
		window.addEventListener('resize', resizeChart);
	}
}
