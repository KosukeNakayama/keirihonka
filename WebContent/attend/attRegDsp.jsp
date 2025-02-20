<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="bean.StudentExp, bean.ClassHistory, servlet.attend.SeatHeader" %>
<%@page import="java.util.HashMap, java.util.Map, java.util.List, java.util.Objects"%>
<%
List<StudentExp> stuList = (List<StudentExp>)request.getAttribute("stuList");
int stuListSize = stuList.size();

//画面情報引継ぎ
SeatHeader sh = (SeatHeader)request.getAttribute("seatHeader");
int maxRow = sh.getSeatRow();
int maxCol = sh.getSeatCol();
int numOfSeats = maxRow * maxCol;

//座席配置表示時にseatNo->studentNameを取得するためのMap
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


<form method="POST" action="AttRegExe"  name="seatEntryForm"  onSubmit="checkEntry()">

<!-- Attributeから取得 -->
<label>クラス</label>
<select name="className" readonly>
	<option value="<%= sh.getClassName() %>"><%= sh.getClassName() %></option>
</select>
<input type="submit" id="confirmBtn" value="選択" disabled>
<input type="hidden" id="numOfSeats" value="<%= numOfSeats %>">

<br>

	<%
	//座席配置用Map作成
	for (StudentExp stu:stuList) {
		String seatNoDsp;
		//seatNo未入力だと一括変換時に0に置き換わるので、表示時に未入力に再変換
		if (stu.getClassHistoryList().getSeatNo()==0) {
			seatNoDsp = "";
		} else {
			seatNoDsp = Integer.toString(stu.getClassHistoryList().getSeatNo());
		}
		studentsMap.put(Integer.toString(stu.getClassHistoryList().getSeatNo()), stu.getStudentName());
	 } %>


<table class="test-table" border="1">
	<caption><h3>座席位置</h3></caption>
	<tbody>
	<%
	int startNo = numOfSeats - maxCol + 1;
	for (int i=1; i<=maxRow; i++) {
	%>	<tr> <%
		for (int j=startNo; j<startNo+maxCol; j++) {
	%>
			<td class="seatTable">
	<%
			//座席配置図で、表示Noに学生が登録済みの場合は氏名も表示
			String stringJ = String.valueOf(j);
			if(Objects.isNull(studentsMap.get(stringJ))) {
				out.print(j);
			} else {
				out.print(stringJ + "<br>" + studentsMap.get(stringJ));
			}
	%>		</td>

	<% } %>
		</tr>
	<%
		startNo -= maxCol;
	}
	%>
	</tbody>
	<tr><td class="space" colspan="<%= maxCol %>"></td></tr>
	<tr><td class="platform" colspan="<%= maxCol %>">教壇</td></tr>
</table>
</div>
<br>
<button type="button" id="seatEntryBtn">登録</button>

<br><br>
<a href="/keirihonka/servlet/attend/AttReg">メニューに戻る</a>
</form>
<script src="../../static/js/seatConfirm.js"></script>
</body>
</html>