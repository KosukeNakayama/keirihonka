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
<jsp:useBean id="stu" class="bean.Student" />

<form action="stuRegCSV.jsp">
<p><input type="submit" value="CSVファイルを読み込む"></p>
</form>

<form action="sturegupd" method="post">
<p>入学年度</p>
<select name="enrollmentYear">
 <option value=2024>2024</option>
 <option value=2025>2025</option>
</select>

<p>学生番号</p>
<input type="text" name="studentId" value="<%=stu.getStudentId()%>">

<p>名前</p>
<input type="text" name="studentName" >

<p>コース名</p>
<select name="courseId">
 <option value=1>システム開発コース</option>
</select>

<p><input type="submit" value="登録"></p>
</form>

<a href="">メニュー</a>

</body>
</html>