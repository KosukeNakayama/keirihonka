<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.ArrayList" %>
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

<form action="/keirihonka/student/sturegcsvupd" method="post">
<!-- 登録人数を送信 -->
<input type="hidden" name="Count" value="${stuList.size()}">

<table border="1">
<tr><th>お名前</th><th>コースID</th><th>入学年度</th><th>学生番号</th></tr>
<c:forEach var="student" items="${stuList}" varStatus="status">
<tr>
<td><input type="text" value="${student.studentName}" name="stu${status.index + 1}Name"></td>
<td><input type="text" value="${student.courseId}" name="stu${status.index + 1}CourseId" readonly></td>
<td><input type="text" value="${student.enrollmentYear}" name="stu${status.index + 1}EnrollmentYear" readonly></td>
<td><input type="text" value="${student.studentId}" name="stu${status.index + 1}StudentId" readonly></td>
</tr>
</c:forEach>
</table>

<p><input type="submit" value="登録"></p>
</form>

<a href="/keirihonka/main">メニュー</a>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="/keirihonka/static/js/util.js"></script>
</body>
</html>

