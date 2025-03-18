<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生登録</title>
<link rel="stylesheet" href="/keirihonka/static/css/style.css">
</head>
<body>
<%@include file="../util/frame.jsp"%>
<!-- ここから下に各画面の要素を足していく -->
<!-- メインエリア -->
<div class="main">
<jsp:useBean id="stu" class="bean.Student" scope="request"/>

<form action="/keirihonka/student/stuRegCSV.jsp">
<p><input type="submit" value="CSVファイルを読み込む"></p>
</form>

<form action="sturegupd" method="post">
<p>入学年度</p>
<select name="enrollmentYear">
 <option value=2024>2024</option>
 <option value=2025>2025</option>
</select>

<p>学生番号</p>
<input type="text" name="studentId" value="<jsp:getProperty name="stu" property="studentId" />">

<p>名前</p>
<input type="text" name="studentName" >

<p>コース名</p>
<select name="courseId">
 <option value="1">高度システム開発コース</option>
 <option value="2">システム開発コース</option>
 <option value="3">AIシステムコース</option>
 <option value="4">ネットワークセキュリティコース</option>
</select>

<p><input type="submit" value="登録"></p>
</form>

<a href="/keirihonka/main">メニュー</a>

</div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="/keirihonka/static/js/util.js"></script>
</body>
</html>