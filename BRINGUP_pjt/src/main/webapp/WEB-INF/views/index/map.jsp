<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="js/index/areaDetail.js"></script>
<img src="images/map_korea.png" alt="Map" name="point" usemap="#ko_map"
	style="width: 100%;" />
<map id="ko_map" name="ko_map">
	<area shape="poly" coords="100,80,106,74,134,72,139,77,139,88,119,87,117,94,114,95,110,94,104,88" href="javascript:getAreaDeteil('서울')" alt="서울" onmouseover="if(document.images) point.src='images/seoul.png'" onmouseout="if(document.images) point.src='images/map_korea.png'"/>
    <area shape="poly" coords="55,80,68,71,77,81,80,90,90,81,104,88,105,99,98,106,84,101,76,105,51,105,49,93,66,91,64,85,57,85" href="javascript:getAreaDeteil('인천')" alt="인천" onmouseover="if(document.images) point.src='images/incheon.png'" onmouseout="if(document.images) point.src='images/map_korea.png'"/>
    <area shape="poly" coords="81,88,79,80,73,70,73,61,92,62,94,53,102,53,106,46,100,39,106,36,145,46,152,56,154,65,156,81,160,87,166,91,169,95,166,100,169,117,168,122,160,133,153,134,146,138,136,146,118,147,107,147,101,141,95,138,100,131,86,129,96,121,89,114,108,96,117,97,120,87,138,87,138,73,117,73,105,72,98,79,89,79" href="javascript:getAreaDeteil('경기')" alt="경기도" onmouseover="if(document.images) point.src='images/gyeonggido.png'" onmouseout="if(document.images) point.src='images/map_korea.png'"/>
    <area shape="poly" coords="117,34,116,28,128,29,166,21,176,22,185,24,193,28,199,26,203,10,212,4,217,8,224,26,232,50,240,58,245,64,248,72,258,80,266,91,272,98,273,107,280,118,285,122,283,127,277,132,270,132,263,132,256,136,248,137,240,136,234,132,220,133,212,126,204,128,200,122,192,117,180,122,172,122,171,93,164,86,157,84,156,62,151,55,152,48,142,42,127,37" href="javascript:getAreaDeteil('강원')" alt="강원도" onmouseover="if(document.images) point.src='images/gangwondo.png'" onmouseout="if(document.images) point.src='images/map_korea.png'"/>
    <area shape="poly" coords="69,132,82,135,85,143,106,153,113,151,133,151,139,163,138,171,131,172,128,180,133,184,139,185,138,192,134,199,135,210,143,219,152,214,159,220,159,230,150,231,142,227,136,220,128,222,122,221,116,217,112,215,106,222,99,228,92,228,82,215,83,198,73,197,73,178,67,175,58,178,54,168,47,164,51,158,49,153,54,153,55,147,60,148,59,159,62,153,67,146,73,148" href="javascript:getAreaDeteil('충남')" alt="충청남도" onmouseover="if(document.images) point.src='images/chungnam.png'" onmouseout="if(document.images) point.src='images/map_korea.png'"/>
    <area shape="poly" coords="24,418,32,416,46,405,56,412,60,412,59,405,65,406,69,401,82,402,85,406,83,423,78,428,71,430,69,436,50,438,43,434,38,440,32,440,27,433,22,430" href="javascript:getAreaDeteil('제주')" alt="제주도" onmouseover="if(document.images) point.src='images/jeju.png'" onmouseout="if(document.images) point.src='images/map_korea.png'"/>
    <area shape="poly" coords="132,183,141,184,151,187,155,181,153,172,133,172" href="javascript:getAreaDeteil('세종')" alt="세종" onmouseover="if(document.images) point.src='images/sejong.png'" onmouseout="if(document.images) point.src='images/map_korea.png'"/>
    <area shape="poly" href="javascript:getAreaDeteil('충북')" alt="충청북도" coords="138,149,152,138,159,137,172,125,180,127,187,123,198,125,201,130,215,135,223,137,228,140,222,144,216,153,213,155,202,156,188,162,182,169,183,175,175,173,173,176,175,183,176,197,180,206,188,211,189,216,181,224,176,229,167,229,158,216,174,215,173,198,156,198,147,193,155,186,158,182,155,170,143,169,143,158" onmouseover="if(document.images) point.src='images/chungbuk.png'" onmouseout="if(document.images) point.src='images/map_korea.png'"/>
    <area shape="poly" href="javascript:getAreaDeteil('대전')" alt="대전" onmouseover="if(document.images) point.src='images/daejeon.png'" onmouseout="if(document.images) point.src='images/map_korea.png'" coords="153,199,142,192,136,197,136,207,142,215,150,211,158,213,172,213,170,200"/>
    <area shape="poly" href="javascript:getAreaDeteil('경북')" alt="경상북도" onmouseover="if(document.images) point.src='images/gyeongbuk.png'" onmouseout="if(document.images) point.src='images/map_korea.png'" coords="178,177,183,179,189,173,189,165,201,159,210,159,219,157,230,143,235,143,233,135,245,143,252,143,262,137,270,138,276,139,281,133,287,139,294,164,295,173,294,184,290,230,302,219,300,258,292,252,282,250,273,228,245,224,230,226,221,252,213,252,203,240,195,239,192,235,183,237,180,230,195,214,190,207,180,201,182,195,183,192,179,186"/>
    <area shape="poly" href="javascript:getAreaDeteil('대구')" alt="대구" onmouseover="if(document.images) point.src='images/daegu.png'" onmouseout="if(document.images) point.src='images/map_korea.png'" coords="245,227,270,229,279,249,269,260,261,258,253,262,240,259,241,253,228,255,227,245,230,232" />
    <area shape="poly" href="javascript:getAreaDeteil('전북')" onmouseover="if(document.images) point.src='images/jeonbuk.png'" onmouseout="if(document.images) point.src='images/map_korea.png'" coords="93,233,102,230,111,222,140,227,145,234,156,234,162,232,175,236,169,245,156,254,160,282,153,285,142,284,123,290,102,273,96,276,88,289,81,293,82,283,73,280,76,270,71,265,83,253"/>
    <area shape="poly" href="javascript:getAreaDeteil('경남')" onmouseover="if(document.images) point.src='images/gyeongnam.png'" onmouseout="if(document.images) point.src='images/map_korea.png'" coords="172,250,180,238,186,242,196,241,203,244,209,254,221,259,227,262,234,259,248,268,261,261,268,271,269,284,257,288,249,302,250,308,249,325,237,332,236,345,233,345,228,338,220,338,216,331,208,327,201,332,193,328,193,340,182,334,173,340,170,326,173,315,166,304,158,291,165,281,162,266,162,255"/>
    <area shape="poly" href="javascript:getAreaDeteil('울산')" onmouseover="if(document.images) point.src='images/ulsan.png'" onmouseout="if(document.images) point.src='images/map_korea.png'" coords="276,254,272,266,273,284,282,287,286,280,290,280,293,275,313,275,313,260,289,260,285,253"/>
    <area shape="poly" href="javascript:getAreaDeteil('부산')" onmouseover="if(document.images) point.src='images/busan.png'" onmouseout="if(document.images) point.src='images/map_korea.png'" coords="252,326,253,303,257,301,260,292,268,289,276,290,281,294,276,302,293,302,293,318,266,318"/>
    <area shape="poly" href="javascript:getAreaDeteil('광주')" alt="광주" onmouseover="if(document.images) point.src='images/gwangju.png'" onmouseout="if(document.images) point.src='images/map_korea.png'" coords="108,298,99,293,92,298,93,305,93,312,107,312,132,312,132,298"/>
    <area shape="poly" href="javascript:getAreaDeteil('전남')" alt="전라남도" onmouseover="if(document.images) point.src='images/jeonnam.png'" onmouseout="if(document.images) point.src='images/map_korea.png'" coords="92,290,96,282,105,278,108,283,116,287,122,293,140,290,155,293,167,312,166,322,165,331,168,344,156,344,153,349,156,358,155,364,147,360,143,354,138,365,129,365,126,373,122,376,111,371,93,377,88,372,88,362,79,367,83,374,75,377,69,384,61,379,67,376,61,365,55,370,58,376,39,381,35,374,44,369,40,346,45,344,47,351,59,352,51,340,61,339,65,326,61,324,56,332,40,334,42,323,44,312,52,313,48,297,62,297,67,283,75,286,80,296,89,299,89,313,99,317,113,314,134,314,134,296,111,294,103,290"/>
</map>
<%@ include file="./mapModal.jsp"%>