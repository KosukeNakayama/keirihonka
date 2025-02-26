<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Include Sample</title>
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

<a href="">メニュー</a>


</body>
</html>