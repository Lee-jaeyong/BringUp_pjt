function drawAreaDetailChart() {
	var res = JSON.parse(localStorage.getItem('area'));
	var selectArea = localStorage.getItem('selectArea');
	var detailTableArea = '';
	$("#selectAreaNM").text(selectArea);
	
	var array = new Array();
	array.push([ '', '', '', '','' ]);
	
	for(var i = res.length - 1;i>=0;i--){
		detailTableArea += '<tr>';
		detailTableArea += '<td><strong>' + res[res.length - 1 - i]['createDt'].substring(0,10) + "</strong>";
		detailTableArea += '</td>';
		
		detailTableArea += '<td><strong>' + numberWithCommas(res[res.length - 1 - i]['defCnt']) + "</strong>(명)";
		
		if(i !== 0){
			detailTableArea += "&nbsp;&nbsp;&nbsp;&nbsp;" + compareTo(res[res.length - 1 - i]['defCnt'],res[res.length - 1 - i + 1]['defCnt']);
		}
		detailTableArea += '</td>';
		detailTableArea += '<td><strong>' + numberWithCommas(res[res.length - 1 - i]['deathCnt']) + "</strong>(명)";
		
		if(i !== 0){
			detailTableArea += "&nbsp;&nbsp;&nbsp;&nbsp;" + compareTo(res[res.length - 1 - i]['deathCnt'],res[res.length - 1 - i + 1]['deathCnt']);
		}
		
		detailTableArea += '</td>';
		detailTableArea += '<td><strong>' + numberWithCommas(res[res.length - 1 - i]['isolClearCnt']) + "</strong>(명)";
		
		if(i !== 0){
			detailTableArea += "&nbsp;&nbsp;&nbsp;&nbsp;" + compareTo(res[res.length - 1 - i]['isolClearCnt'],res[res.length - 1 - i + 1]['isolClearCnt']);
		}
		
		detailTableArea += '</td>';
		detailTableArea += '<td><strong>' + numberWithCommas(res[res.length - 1 - i]['isolIngCnt']) + "</strong>(명)";

		if(i !== 0){
			detailTableArea += "&nbsp;&nbsp;&nbsp;&nbsp;" + compareTo(res[res.length - 1 - i]['isolIngCnt'],res[res.length - 1 - i + 1]['isolIngCnt']);
		}
		
		detailTableArea += '</td>';
		array.push([res[i]['createDt'].substring(0,10),res[i]['deathCnt'],res[i]['defCnt'],res[i]['isolClearCnt'],res[i]['isolIngCnt']]);
		detailTableArea += '</tr>';
	}
	
	$("#detailTable").html(detailTableArea);
	
	var data = google.visualization.arrayToDataTable(array);

	var options = {
		bars : 'vertical',
		vAxis : {
			format : 'decimal'
		},
		height : 350,
		colors : [ '#1b9e77', '#d95f02', '#7570b3','#F361DC' ]
	};
	
	setTimeout(function(){
		var chart = new google.charts.Bar(document.getElementById('chart_div'));
		
		chart.draw(data, google.charts.Bar.convertOptions(options));
		
		function resizeChart () {
			chart.draw(data, options);
		}
		if (document.addEventListener) {
			window.addEventListener('resize', resizeChart);
		}
	},500);
}

function compareTo(before,after){
	return parseInt(before) - parseInt(after) >= 0 ? parseInt(before) - parseInt(after) + '<i style="color:red;" class="fas fa-arrow-up"></i>' : (parseInt(before) - parseInt(after)) * -1 + '<i style="color:blue;" class="fas fa-arrow-down"></i>';
}

function numberWithCommas(x) { return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","); }
