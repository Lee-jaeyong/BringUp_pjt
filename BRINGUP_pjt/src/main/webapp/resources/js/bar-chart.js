function drawBasic() {
  const jsonData = JSON.parse(sessionStorage.getItem('covidSexData'));
  var data = google.visualization.arrayToDataTable([
      ['','확진자', '사망자', { role: 'annotation' }],
      ['여 성',jsonData['female']['confCase'] ,jsonData['female']['death'], ''],
      ['남 성',jsonData['male']['confCase'] ,jsonData['male']['death'], '']
    ]);
  
  var options = {
    height : 380,
	width: $(window).width() < 768 ? 350 : 500,
    bar: { groupWidth: '75%' },
    isStacked: true,
  };

	var chart = new google.visualization.BarChart(document.getElementById('DEATH_CNT_div'));

	chart.draw(data, options);
	
	function resizeChart () {
		chart.draw(data, options);
	}
	if (document.addEventListener) {
		window.addEventListener('resize', resizeChart);
	}
}
