<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="bean.StudentExp, servlet.attend.SeatHeader, java.util.List" %>
<%
List<StudentExp> stuList = (List<StudentExp>)request.getAttribute("stuList");

SeatHeader sh = (SeatHeader)request.getAttribute("seatHeader");
int maxRow = sh.getSeatRow();
int maxCol = sh.getSeatCol();
%>


<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-store">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../../static/css/seatSet.css" type="text/css">
<title>座席情報設定</title>
</head>
<body>
<%@include file="../util/frame.jsp"%>
<!-- ここから下に各画面の要素を足していく -->
<!-- コメント追加 -->

<form method="POST" action="seatSetExe.jsp">

<!-- 後程DBから取得 -->
<label>クラス</label>
<select name="classC" disabled>
	<option value="<%= sh.getClassName() %>"><%= sh.getClassName() %></option>
</select>
<input type="button" value="登録">

<br><br>
<div>
<label>座席配置（縦）</label>
<select name="seatRow" disabled>
	<option value="<%= maxRow %>"><%= maxRow %></option>
</select>
<label>座席配置（横）</label>
<select name="seatCol" disabled>
	<option value="<%= maxCol %>"><%= maxCol %></option>
</select>
</div>

<br>

<div  class="example">
<table class="test-table" border="1">
	<thead><th>学生番号</th><th>学生氏名</th><th>座席番号</th></thead>
	<tbody>
	<% for (StudentExp stu:stuList) { %>
		<tr>
			<td><%=stu.getStudentId() %></td>
			<td><%=stu.getStudentName() %></td>
			<td><input type="number" name="seatno" value="" min="1" max="9"></td></tr>
	<% } %>
	</tbody>
</table>

<br>

<table class="test-table" border="1">
	<tbody>
	<%
	int numOfSeats = maxRow * maxCol;
	System.out.println("maxRow:"+maxRow+" maxCol:"+maxCol+" NofS:"+numOfSeats);

	int startNo = numOfSeats - maxCol + 1;
	System.out.println("startNo:"+startNo);

	for (int i=1; i<=maxRow; i++) {
	%>	<tr> <%
		System.out.println("i:"+i);
		for (int j=startNo; j<startNo+maxCol; j++) {
			System.out.println("j:"+j);
	%>
				<td class="seatTable"><%= j %></td>

	<% } %>
		</tr>
	<%
		startNo -= maxCol;
		System.out.println("startNo:"+startNo+" maxRow:"+maxRow);
	}
	%>
	</tbody>
	<tr><td class="space" colspan="<%= maxCol %>"></td></tr>
	<tr><td class="platform" colspan="<%= maxCol %>">教壇</td></tr>
</table>
</div>
<br><br>
<a href="/keirihonka/servlet/attend/SeatSet">メニューに戻る</a>
</form>
</body>
</html>