<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
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

<form action="stuUpdExe" method="post">
<!-- 登録人数を送信 -->
<input type="hidden" name="Count" value="${stuUpdList.size()}">

<table border="1">
<tr><th>お名前</th><th>コースID</th><th>入学年度</th><th>学生番号</th></tr>
    <tr>
        <td><input type="text" value="${student.studentName}" name="studentName"></td>
        <td><input type="text" value="${student.courseId}" name="stu1CourseId" disabled></td>
        <td><input type="text" value="${student.enrollmentYear}" name="stu1EnrollmentYear" disabled></td>
        <td><input type="text" value="${student.studentId}" name="studentId" readonly></td>
    </tr>

</table>

<p><input type="submit" value="更新"></p>
</form>

<a href="/keirihonka/main">メニュー</a>

</div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="/keirihonka/static/js/util.js"></script>
</body>
</html>
