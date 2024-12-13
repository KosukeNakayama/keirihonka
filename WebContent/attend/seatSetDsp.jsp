<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="bean.StudentExp, bean.ClassHistory, servlet.attend.SeatHeader" %>
<%@page import="java.util.HashMap, java.util.Map, java.util.List, java.util.Objects"%>
<%
List<StudentExp> stuList = (List<StudentExp>)request.getAttribute("stuList");

SeatHeader sh = (SeatHeader)request.getAttribute("seatHeader");
int maxRow = sh.getSeatRow();
int maxCol = sh.getSeatCol();
int numOfSeats = maxRow * maxCol;

Map<String, String>studentsMap = new HashMap<String, String>();
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


<button id="confirmBtn">登録</button>

<!--  <form method="POST" action="seatSetExe"> -->

<!-- Attributeから取得 -->
<label>クラス</label>
<select name="classC" disabled>
	<option value="<%= sh.getClassName() %>"><%= sh.getClassName() %></option>
</select>
<input type="button" id="confirmBtn" value="選択" disabled>

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
			<td name="studentId"><%=stu.getStudentId() %></td>
			<td><%=stu.getStudentName() %></td>
			<td><input type="number" name="seatno" value="<%=stu.getClassHistoryList().getSeatNo() %>" min="1" max="<%=numOfSeats%>"></td></tr>

	<%		studentsMap.put(Integer.toString(stu.getClassHistoryList().getSeatNo()), stu.getStudentName());
			//System.out.println("seat:"+stu.getClassHistoryList().getSeatNo()+" stu:"+stu.getStudentName());
	 } %>
	</tbody>
</table>

<br>

<table class="test-table" border="1">
	<tbody>
	<%
	//int numOfSeats = maxRow * maxCol;
	//System.out.println("maxRow:"+maxRow+" maxCol:"+maxCol+" NofS:"+numOfSeats);

	int startNo = numOfSeats - maxCol + 1;

	for (int i=1; i<=maxRow; i++) {
	%>	<tr> <%
//		System.out.println("i:"+i);
		for (int j=startNo; j<startNo+maxCol; j++) {
	%>
			<td class="seatTable">
	<%
			String stringJ = String.valueOf(j);
			if(Objects.isNull(studentsMap.get(stringJ))) {
				out.print(j);
			} else {
				int endIndex = studentsMap.get(stringJ).lastIndexOf(' ');
				String lastName = studentsMap.get(stringJ).substring(0, endIndex);
				String firstName = studentsMap.get(stringJ).substring(endIndex + 1);
				out.print(lastName + "<br>" + firstName);
			}
	%>		</td>

	<% } %>
		</tr>
	<%
		startNo -= maxCol;
//		System.out.println("startNo:"+startNo+" maxRow:"+maxRow);
	}
	%>
	</tbody>
	<tr><td class="space" colspan="<%= maxCol %>"></td></tr>
	<tr><td class="platform" colspan="<%= maxCol %>">教壇</td></tr>
</table>
</div>
<br>
<a href="/keirihonka/servlet/attend/SeatSet">メニューに戻る</a>
</form>
<script src="../../static/js/seatConfirm.js"></script>
</body>
</html>