<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>座席情報設定</title>
</head>
<body>
<%@include file="../util/frame.jsp"%>
<!-- ここから下に各画面の要素を足していく -->
<!-- コメント追加 -->
<form action="">

<!-- 後程DBから取得 -->
<label>クラス</label>
<select name="class">
	<option value="" selected>--選択--</option>
	<option value="1-1">1-1</option>
	<option value="2-1">2-1</option>
</select>

<br><br>

<!-- 後程ループにします -->
<label>座席配置（縦）</label>
<select name="seat-row">
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5" selected>5</option>
	<option value="6">6</option>
	<option value="7">7</option>
</select>


<label>座席配置（横）</label>
<select name="seat-col">
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5" selected>5</option>
	<option value="6">6</option>
	<option value="7">7</option>
</select>

</form>


</body>
</html>