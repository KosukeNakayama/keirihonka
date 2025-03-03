<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includeSample.jsp"%>
<%@page import="bean.*,java.util.List"%>

<div>
	<form action="ScoCnf" method="post">
		<label for="student_id">学生番号</label>
		<input type="text" name="student_id" id="student_id">
		<input type="submit" value="選択">
	</form>
</div>
<div>

<% List<Score> list=(List<Score>)request.getAttribute("list"); %>

</div>
<table border="1">
	<thead>
		<tr>
			<th scope="col">年</th>
			<th scope="col">月</th>
			<th scope="col">学生番号</th>
			<th scope="col">氏名</th>
			<th scope="col">科目名</th>
			<th scope="col">点数</th>
		</tr>
	</thead>
	<tbody>
<%
    if (list == null || list.isEmpty()) {
%>
    <p>データがありません。</p>
<%
    } else {
			for (Score p : list) {
		%>
		<tr>
			<td><%=p.getYear()%></td>
			<td><%=p.getMonth()%></td>
			<td><%=p.getStudentId()%></td>
			<td><%=p.getStudentName()%></td>
			<td><%=p.getSubjectName()%></td>
			<td><%=p.getScore()%></td>
		</tr>

		<%
			}
    }
		%>

	</tbody>
</table>
</body>
</html>