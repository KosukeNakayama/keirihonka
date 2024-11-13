<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includeSample.jsp"%>
<%@page import="bean.*,java.util.List"%>

<div>
	<select name="classes" id="classes">
		<option value="101">1年1組</option>
		<option value="201">2年1組</option>
	</select> <select name="month" id="month">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
		<option value="6">6</option>
		<option value="7">7</option>
		<option value="8">8</option>
		<option value="9">9</option>
		<option value="10">10</option>
		<option value="11">11</option>
		<option value="12">12</option>
	</select> <select>
		<option value="1">PythonⅠ</option>
		<option value="2">PythonⅡ</option>
	</select> <input type="submit" value="選択">

</div>
<div>
	<%--
<% List<Score> list=(List<Score>)request.getAttribute("list"); %>
 --%>

</div>
<table>
	<thead>
		<tr>
			<th scope="col">学年</th>
			<th scope="col">月</th>
			<th scope="col">学生番号</th>
			<th scope="col">氏名</th>
			<th scope="col">科目名</th>
			<th scope="col">点数</th>
		</tr>
	</thead>
	<tbody>
		<%--
		<%
			for (Score p : list) {
		%>
		<tr>
			<td><%=%></td>
			<td><%=%></td>
			<td><%=%></td>
			<td><%=%></td>
			<td><%=%></td>
			<td><input type="text" name=""></td>
		</tr>

		<%
			}
		%>
        --%>
	</tbody>
</table>
<input type="submit" value="更新">
</body>
</html>