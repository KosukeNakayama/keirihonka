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

<p>学生番号</p>
<p>学生番号入力</p>

<form action="" method=post>
<input type="number" maxlength="7" name="studentId" value="">

<p><input type="submit" value="検　索"></p>
</form>

<a href="">メニュー</a>


</body>
</html>