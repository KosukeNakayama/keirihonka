<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生更新</title>
<link rel="stylesheet" href="/keirihonka/static/css/style.css">
</head>
<body>
<%@include file="../util/frame.jsp"%>
<!-- ここから下に各画面の要素を足していく -->
<!-- メインエリア -->
<div class="main">
<p>学生番号</p>
<p>学生番号入力</p>

<form action="stuupd" method=post>
<input type="number" maxlength="7" name="studentId" value="">

<p><input type="submit" value="検　索"></p>
</form>

<a href="/keirihonka/main">メニュー</a>

</div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="/keirihonka/static/js/util.js"></script>
</body>
</html>