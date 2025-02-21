<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Include Sample</title>
</head>
<body>
<%@include file="../util/frame.jsp"%>
<!-- ここから下に各画面の要素を足していく -->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="" method="post">
<!-- 登録人数を送信 -->
<input type="hidden" name="Count" value="${stuCsvList.size()}">

<table border="1">
<tr><th>お名前</th><th>コースID</th><th>入学年度</th><th>学生番号</th></tr>
<c:forEach var="student" items="${stuCsvList}">
 	<% ArrayList studentList = (ArrayList)request.getAttribute("stuCsvList"); %>
    <%for(int i=1; i<=studentList.size(); i++) { %>
    <tr>
        <td><input type="text" value="${student.studentName}" name="stu<%=i%>Name"></td>
        <td><input type="text" value="${student.courseId}" name="stu<%=i%>CourseId" disabled></td>
        <td><input type="text" value="${student.enrollmentYear}" name="stu<%=i%>EnrollmentYear" disabled></td>
        <td><input type="text" value="${student.studentId}" name="stu<%=i%>StudentId" disabled></td>
    </tr>
    <%} %>
</c:forEach>
</table>

<p><input type="submit" value="登録"></p>
</form>

<a href="">メニュー</a>

</body>
</html>
