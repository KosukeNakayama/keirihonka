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

<form action="" method="post">
<p><input type="submit" value="CSVファイルを読み込む"></p>

<p>学生番号</p>
<input type="text" name="studentId" value="${stu.getStudentId()}">

<p>名前</p>
<input type="text" name="studentName" >

<p>コース名</p>
<input type="text" name="courseId" value="システム開発コース" >

<p><input type="submit" value="登録"></p>
</form>

<a href="">メニュー</a>

</body>
</html>