$(document).ready(function(e) {
	window.addEventListener('resize', function() {
	     if(window.innerWidth <= 576){
	        $("#detailTable").addClass('table-responsive');
	     }
    }, false);
});

function drawlineChart() {
  const jsonData = JSON.parse(sessionStorage.getItem("totalCovidData"));
  
  var array = new Array();
  array.push(['', '확진', '격리 해제','치료중']);
  
  for(var i =jsonData.length-2;i>=0;i--){
	  var parseDate = jsonData[i]['createDt'].substring(0,10).replace(/-/gi, ".").substring(5);
	  const isolClearCnt = jsonData[i]['isolClearCnt'] - jsonData[i + 1]['isolClearCnt'];
	  const isolIngCnt = jsonData[i]['isolIngCnt'] - jsonData[i + 1]['isolIngCnt'];
	  array.push([parseDate,jsonData[i]['incDec'],isolClearCnt,isolIngCnt]);
  }
  
  var data = google.visualization.arrayToDataTable(array);

  var options = {
   width : '100%',
    title: '',
    curveType: 'function',
    legend: { position: 'bottom' },
    pointSize: 10,
    pointShape: 'circle',
    colors: ['#BA2B2B', '#6B9900', '#4FC9DE'],
    vAxis : {
           gridlines : {
                 color : '#FFFFFF'
        }
    },
    backgroundColor :'#F6F6F6',
    borderRadius : '20px 20px 20px 20px'
  };

  var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

  chart.draw(data, options);
  window.addEventListener('resize', function() { chart.draw(data, options); }, false);
}