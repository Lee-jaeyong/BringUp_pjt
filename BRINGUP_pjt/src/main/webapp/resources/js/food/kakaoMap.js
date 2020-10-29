var foodData;
var init = true;
var searchData = [];

$('#myAroundBtn').click(function() {
	geolocationFuc();
})

var lang_kor = {
	"decimal" : "",
	"emptyTable" : "데이터가 없습니다.",
	"info" : "_START_ - _END_ (총 _TOTAL_ 명)",
	"infoEmpty" : "0명",
	"infoFiltered" : "(전체 _MAX_ 명 중 검색결과)",
	"infoPostFix" : "",
	"thousands" : ",",
	"lengthMenu" : "_MENU_ 개씩 보기",
	"loadingRecords" : "로딩중...",
	"processing" : "처리중...",
	"search" : "검색___",
	"zeroRecords" : "검색된 데이터가 없습니다.",
	"paginate" : {
		"first" : "첫 페이지",
		"last" : "마지막 페이지",
		"next" : "다음",
		"previous" : "이전"
	},
	"aria" : {
		"sortAscending" : " :  오름차순 정렬",
		"sortDescending" : " :  내림차순 정렬"
	}
};

$(document).ready(function() {
	$('#dataTable').DataTable({
		"dom" : '<"col-sm-12" f><"col-sm-12" t><"col-sm-12" p>',
		"pageLength" : 11,
		"language" : lang_kor
	});
});

function geolocationFuc() {
	if(!init){
		alert("내 위치로 이동합니다.");
	}
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(pos) {
			panTo(pos.coords.latitude, pos.coords.longitude);
			$("input[type='search']").val(searchData[0]);
			$("input[type='search']").keyup();
		});
	} else {
		alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.")
	}
}

var mapContainer = document.getElementById('kakaoMap'), // 지도를 표시할 div
mapOption = {
	center : new kakao.maps.LatLng(37.74618002504751, 127.02386151118968), // 지도의
	level : 3
};

// 지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

var clusterer = new kakao.maps.MarkerClusterer({
	map : map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
	averageCenter : true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
	minLevel : 7
// 클러스터 할 최소 지도 레벨
});

map.setZoomable(false);

var markers = [];

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

geolocationFuc();

function getFoodData(area){
	map.setDraggable(false);
	$("#mapProgress").css('width',0 + '%');
	$("#mapProgress").parent().show();
	$.ajax({
		url : "./covid/food",
		data : {
			area : area.region_1depth_name
		},
		success : function(res) {
			foodData = res;
			var t = $('#dataTable').DataTable();
			for (var i = 0; i < res.length; i++) {
				var data = res[i];
				var percent = i / res.length * 100;
				$("#mapProgress").css('width',percent + '%');
				t.row.add([
					"<a href='https://search.naver.com/search.naver?where=post&sm=tab_jum&query="+encodeURIComponent(data['relax_ADD1'] + " " + data['relax_RSTRNT_NM'])+"' target='_blank'>" + data['relax_RSTRNT_NM'] + "</a>", 
					"<a href='javascript:move(\""+data['relax_ADD1']+"\","+data['relax_SEQ']+")'>"+ data['relax_ADD1'] + "</a>",
					data['relax_RSTRNT_TEL'] ? "<a href='tel:" + data['relax_RSTRNT_TEL'] + "'>" + data['relax_RSTRNT_TEL'] + "</a>" : '', data['relax_RSTRNT_REG_DT'] ]);
				const nowFood = {
						relax_ADD1 : data["relax_ADD1"],
						relax_RSTRNT_NM : data["relax_RSTRNT_NM"],
						relax_SEQ : data["relax_SEQ"],
				};
				search(nowFood);
			}
			
			t.row.add([ '', '', '', '' ]).draw();
			$("input[type='search']").val(area.region_1depth_name + " " + area.region_2depth_name);
			$("input[type='search']").keyup();
			setTimeout(function(){
				init = false;
				$("#mapProgress").parent().hide();
				map.setDraggable(true);
			},2000);
		}
	})
}


function move(address,seq){
	geocoder.addressSearch(address, function(result, status) {
	     if (status === kakao.maps.services.Status.OK) {
	        panTo(result[0].y, result[0].x);
	     } 
	});    
}

kakao.maps.event.addListener(map, 'idle', function() {
	searchAddrFromCoords(map.getCenter(), function(result, status) {
		if (status === kakao.maps.services.Status.OK) {
			for (var i = 0; i < result.length; i++) {
				if (result[i].region_type === 'H') {
					if(searchData.includes(result[i].region_1depth_name)){
						$("input[type='search']").val(result[i].region_1depth_name + " " + result[i].region_2depth_name);
						$("input[type='search']").keyup();
						return;
					}
					searchData.push(result[i].region_1depth_name);
					getFoodData(result[i]);
					break;
				}
			}
		}
	});
});

function searchAddrFromCoords(coords, callback) {
	geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
}

function successSearch(kakao, data, marker, coords) {
	var infowindow = new kakao.maps.InfoWindow({
		content : '<div id="food_'+data['relax_SEQ']+'" style="width:150px;text-align:center;padding:6px 0;">'
				+ data['relax_RSTRNT_NM'] + '</div>'
	});
	clusterer.addMarkers(markers);
	markers.push(marker);

	kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
}

function search(data) {
	geocoder.addressSearch(data['relax_ADD1'], function(result, status) {
		if (status === kakao.maps.services.Status.OK) {
			var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
			var marker = new kakao.maps.Marker({
				map : map,
				position : coords
			});
			successSearch(kakao, data, marker, coords);
		}
	});
}

function makeOverListener(map, marker, infowindow) {
	return function() {
		infowindow.open(map, marker);
	};
}

// 인포윈도우를 닫는 클로저를 만드는 함수입니다
function makeOutListener(infowindow) {
	return function() {
		infowindow.close();
	};
}

function panTo(x, y) {
	// 이동할 위도 경도 위치를 생성합니다
	var moveLatLon = new kakao.maps.LatLng(x, y);

	// 지도 중심을 부드럽게 이동시킵니다
	// 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
	map.panTo(moveLatLon);
}