$(document).ready(
		function(e) {
			var now = new Date();
			var parseNow = new Date(now.format('yyyy-MM-dd')).getTime()
					- (86400 * 1000);
			getData(new Date(parseNow).format('yyyy-MM-dd'));

			$("#searchBtn").click(function() {
				if ($("#datePicker").val().trim() === '클릭하여 날짜를 선택하세요') {
					alert('날짜를 선택해주세요.');
					$("#datePicker").focus();
					return;
				}

				var selectDate = new Date($("#datePicker").val());
				var nowDate = new Date(new Date().format('yyyy-MM-dd'));
				var check = 1586530800000;

				if (check > selectDate.getTime()) {
					alert('날짜는 4월 11일 이후로 선택해주세요.');
					$("#datePicker").focus();
					return;
				}

				if (selectDate.getTime() > nowDate.getTime() - 86400000) {
					alert('현재날짜 이전일 부터 검색 가능합니다.');
					$("#datePicker").focus();
					return;
				}
				getData(selectDate.format('yyyy-MM-dd'));
			})
		});

function getData(date) {
	$("#dataTableProgress").show();
	$.ajax({
		url : "./covid/sex-and-old",
		data : {
			end : date
		},
		success : function(res) {
			const covidSexData = {
				female : res[9],
				male : res[10]
			}
			var covidOldData = new Array();
			for (var i = 0; i < 9; i++) {
				covidOldData.push(res[i]);
			}
			sessionStorage
					.setItem('covidSexData', JSON.stringify(covidSexData));
			sessionStorage
					.setItem('covidOldData', JSON.stringify(covidOldData));

			google.charts.load('current', {
				packages : [ 'corechart', 'bar' ]
			});
			google.charts.setOnLoadCallback(drawBasic);

			google.charts.load('current', {
				'packages' : [ 'corechart' ]
			});
			
			google.charts.setOnLoadCallback(drawChart);
			$("#datePicker").val(date);
		}
	})
	
	$.ajax({
		url : "./covid/total",
		data : {
			end : date
		},
		success : function(res) {
			sessionStorage.setItem("totalCovidData", JSON.stringify(res));
			google.charts.load('current', {'packages':['corechart']});
			google.charts.setOnLoadCallback(drawlineChart);
		}
	})

	$.ajax({
		url : "./covid/local",
		data : {
			end : date
		},
		success : function(res) {
			var start = res['start'];
			var end = res['end'];
			var before = res['before'];

			var defCntPercent = end[17]['defCnt'] - before[17]['defCnt'];
			var isolIngCntPercent = end[17]['isolIngCnt']
					- before[17]['isolIngCnt'];
			var isolClearCntPercent = end[17]['isolClearCnt']
					- before[17]['isolClearCnt'];
			var deathCntPercent = end[17]['deathCnt'] - before[17]['deathCnt'];

			$("#DEF_CNT_td").html(
					"<strong>" + numberWithCommas(end[17]['defCnt'])
							+ "</strong> (명)");
			$("#ISOL_ING_CNT_td").html(
					"<strong>" + numberWithCommas(end[17]['isolIngCnt'])
							+ "</strong> (명)");
			$("#ISOL_CLEAR_CNT_td").html(
					"<strong>" + numberWithCommas(end[17]['isolClearCnt'])
							+ "</strong> (명)");
			$("#DEATH_CNT_td").html(
					"<strong>" + numberWithCommas(end[17]['deathCnt'])
							+ "</strong> (명)");

			$("#sub_DEF_CNT_td").html(compare(defCntPercent));
			$("#sub_ISOL_ING_CNT_td").html(compare(isolIngCntPercent));
			$("#sub_ISOL_CLEAR_CNT_td").html(compare(isolClearCntPercent));
			$("#sub_DEATH_CNT_td").html(compare(deathCntPercent));

			$("#checkDate").text(date);
			$("#dataTableProgress").hide();
		}
	})
}

function compare(data) {
	return data >= 0 ? data
			+ "<i style='color:red; margin-left:5px' class='fas fa-arrow-up'></i>"
			: (data * -1)
					+ "<i style='color:blue; margin-left:5px' class='fas fa-arrow-down'></i>";
}

function numberWithCommas(x) {
	return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

Date.prototype.format = function(f) {
	if (!this.valueOf())
		return " ";
	var weekKorName = [ "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일" ];
	var weekKorShortName = [ "일", "월", "화", "수", "목", "금", "토" ];
	var weekEngName = [ "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
			"Friday", "Saturday" ];
	var weekEngShortName = [ "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" ];
	var d = this;
	return f.replace(/(yyyy|yy|MM|dd|KS|KL|ES|EL|HH|hh|mm|ss|a\/p)/gi,
			function($1) {
				switch ($1) {
				case "yyyy":
					return d.getFullYear(); // 년 (4자리)
				case "yy":
					return (d.getFullYear() % 1000).zf(2); // 년 (2자리)
				case "MM":
					return (d.getMonth() + 1).zf(2); // 월 (2자리)
				case "dd":
					return d.getDate().zf(2); // 일 (2자리)
				case "KS":
					return weekKorShortName[d.getDay()]; // 요일 (짧은 한글)
				case "KL":
					return weekKorName[d.getDay()]; // 요일 (긴 한글)
				case "ES":
					return weekEngShortName[d.getDay()]; // 요일 (짧은 영어)
				case "EL":
					return weekEngName[d.getDay()]; // 요일 (긴 영어)
				case "HH":
					return d.getHours().zf(2); // 시간 (24시간 기준, 2자리)
				case "hh":
					return ((h = d.getHours() % 12) ? h : 12).zf(2); // 시간 (12시간
																		// 기준,
																		// 2자리)
				case "mm":
					return d.getMinutes().zf(2); // 분 (2자리)
				case "ss":
					return d.getSeconds().zf(2); // 초 (2자리)
				case "a/p":
					return d.getHours() < 12 ? "오전" : "오후"; // 오전/오후 구분
				default:
					return $1;
				}
			});
};

String.prototype.string = function(len) {
	var s = '', i = 0;
	while (i++ < len) {
		s += this;
	}
	return s;
};

String.prototype.zf = function(len) {
	return "0".string(len - this.length) + this;
};

Number.prototype.zf = function(len) {
	return this.toString().zf(len);
};
