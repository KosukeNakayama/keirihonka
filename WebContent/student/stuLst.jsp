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

<table border="1">
<tr><th>お名前</th><th>コースID</th><th>入学年度</th><th>学生番号</th></tr>
<c:forEach var="student" items="${stuList}">
	<tr>
	<td><input type="text" value="${student.studentName}" name="stu1Name"></td>
	<td>${student.studentId}</td>
	<td>${student.enrollmentYear}</td>
	<td>${student.courseId}</td>
	</tr>
</c:forEach>
</table>

<p><input type="submit" value="登録"></p>
</form>

<a href="">メニュー</a>

</body>
</html>
