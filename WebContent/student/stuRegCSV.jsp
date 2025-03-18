<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生登録(CSV)</title>
<link rel="stylesheet" href="/keirihonka/static/css/style.css">
</head>
<body>
<%@include file="../util/frame.jsp"%>
<!-- ここから下に各画面の要素を足していく -->

<form action="StuRegCSV" method="POST" enctype="multipart/form-data">
	<div class="tr">
		<label for="csv" class="th">CSVファイルを選択してください。</label>
		<div class="td">
			<input type="file" id="csv" name="csv" required>
		</div>
	</div>
  <input type="submit" value="表　示">



</form>

<a href="/keirihonka/main">メニュー</a>

</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="/keirihonka/static/js/util.js"></script>
</body>
</html>