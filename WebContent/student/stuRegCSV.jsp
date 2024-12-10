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

<form method="post" enctype="multipart/form-data">
<p>CSVファイルを選択してください。</p>
  <input type="file" name="csv"><br>
  <button type="submit">登　録</button>
</form>

<a href="">メニュー</a>

</body>
</html>