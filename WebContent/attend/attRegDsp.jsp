<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="bean.StudentExp, bean.ClassHistory, bean.ClassHistory, servlet.attend.SeatHeader" %>
<%@page import="java.util.HashMap, java.util.Map, java.util.List, java.util.Objects"%>
<%
boolean isHoliday = (boolean)request.getAttribute("isHoliday");
List<StudentExp> stuList = (List<StudentExp>)request.getAttribute("stuList");
int stuListSize = stuList.size();

//画面情報引継ぎ
SeatHeader sh = (SeatHeader)request.getAttribute("seatHeader");
int maxRow = sh.getSeatRow();
int maxCol = sh.getSeatCol();
int numOfSeats = maxRow * maxCol;
String msg = "";
String btnStatus = "";

//座席配置表示時にseatNo->studentIdeを取得するためのMap
Map<String, String>idMap = new HashMap<String, String>();
//座席配置表示時にseatNo->studentNameを取得するためのMap
Map<String, String>studentsMap = new HashMap<String, String>();
//座席配置表示時にseatNo->statusを取得するためのMap
Map<String, String>statusMap = new HashMap<String, String>();
//座席配置表示時にseatNo->memoを取得するためのMap
Map<String, String>memoMap = new HashMap<String, String>();
%>


<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-store">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../../static/css/seatSet.css" type="text/css">
<link rel="stylesheet" href="../../static/css/modal.css" type="text/css">
<link rel="stylesheet" href="../../static/css/buttons.css" type="text/css">

<title>出欠入力（当日）</title>
</head>
<body>
<%@include file="../util/frame.jsp"%>
<!-- ここから下に各画面の要素を足していく -->
<!-- コメント追加 -->

<form method="POST" action="AttRegExe"  name="attEntryForm"  onSubmit="checkEntry()">

<!-- Attributeから取得 -->
<label>クラス</label>
<select name="className" disabled>
	<option value="<%= sh.getClassName() %>"><%= sh.getClassName() %></option>
</select>
<input type="submit"  class="operation-button" id="confirmBtn" value="選択" disabled>
<input type="hidden" id="numOfSeats" value="<%= numOfSeats %>">

<br><br>
<%
if (isHoliday) {
	msg = "本日は休日設定のため登録できません";
	btnStatus = "disabled";
} else {
	msg = "出欠設定後、登録ボタンを忘れずに！";
}
%>
<div style="color: red; font-weight: bold;"><%= msg %></div>
<input type="hidden" name="attEntry" id="attEntry" value=""></input>
<button type="button" class="entry-button" id="attEntryBtn" <%= btnStatus %>>登録</button>

<br>
<p id="date">
	<span id="beforeEl" >◀</span>
	<span id="currentEl"></span>
	<span id="nextEl" >▶</span>
</p>

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

	//項目表示のためMapにデータを格納
	idMap.put(Integer.toString(stu.getClassHistoryList().getSeatNo()), stu.getStudentId());
	studentsMap.put(Integer.toString(stu.getClassHistoryList().getSeatNo()), stu.getStudentName());
	statusMap.put(Integer.toString(stu.getClassHistoryList().getSeatNo()), String.valueOf(stu.getAttendanceList().getStatus()));
	memoMap.put(Integer.toString(stu.getClassHistoryList().getSeatNo()), stu.getAttendanceList().getMemo());
}
%>


<table class="seats" id="seats" border="1">
	<caption><h3>座席位置</h3></caption>
	<tbody>
	<%
	int startNo = numOfSeats - maxCol + 1;
	for (int i=1; i<=maxRow; i++) {
	%>	<tr> <%
		for (int j=startNo; j<startNo+maxCol; j++) {
			String stringJ = String.valueOf(j);
			String cellId = "cell" + String.format("%03d", j);
			String studentId = idMap.get(stringJ);
			String memo = "";
			if(Objects.nonNull(memoMap.get(stringJ))) {
				memo = memoMap.get(stringJ);
			}
			//System.out.println(memo);

			String statusColor = "white";
			if(Objects.nonNull(studentsMap.get(stringJ))) {

			switch(statusMap.get(stringJ)){
				case "0":
		    		statusColor = "white";
		    	break;
				case "1":
			    	statusColor = "red";
			    	break;
				case "2":
				    statusColor = "yellow";
			    	break;
				case "3":
				    statusColor = "violet";
				    break;
				case "4":
					statusColor = "greenyellow";
					break;
				case "5":
					statusColor = "orange";
			}}
			%>
			<td class="js-modalInput" name="att" data-modal="att" id="<% out.print(cellId); %>"
				style="background-color: <% out.print(statusColor); %>"
				data-student ="<% out.println(studentId); %>"
				data-memo ="<% out.println(memo); %>"
			>
	<%
			//座席配置図で、表示Noに学生が登録済みの場合は氏名も表示
			//String stringJ = String.valueOf(j);
			if(Objects.isNull(studentsMap.get(stringJ))) {
				out.print(j);
			} else {
				out.print(stringJ + "<br> " + studentsMap.get(stringJ));
			}

	%>		</td>

	<% } %>
		</tr>
	<%
		startNo -= maxCol;
	}
	%>
	</tbody>
</table>
<br>
<table>
	<tr><td class="platform" colspan="<%= maxCol %>">教壇</td></tr>
</table>

<div id="js-overlay" class="overlay">
    <div id="att" class="js-modal modal">
    <p class="modal-header"><span class="modal-cancel-btn" id="cancelBtn">×</span></p>
      <div id="prompt-form">
        <label><input type="radio" name="status" value="0">出席　□</label>
        <label><input type="radio" name="status" value="1">欠席　<span style="color: red">■</span></label>
        <label><input type="radio" name="status" value="2">遅刻　<span style="color: yellow">■</span></label>
        <label><input type="radio" name="status" value="3">早退　<span style="color: violet">■</span></label>
        <label><input type="radio" name="status" value="4">他欠　<span style="color: greenyellow">■</span></label>
        <label><input type="radio" name="status" value="5">遅刻早退　<span style="color: orange">■</span></label>
        <label>メモ<input type="text" name="comment" placeholder="メモを入力" id="text-value"></label>
        <label><span class="operation-button" id="entryBtn">登録</span></label>
      </div>
    </div>
</div>



<br><br>
<a href="/keirihonka/servlet/attend/AttReg">メニューに戻る</a>
</form>

<script src="../../static/js/attConfirm.js"></script>
<script src="../../static/js/date.js"></script>
<script src="../../static/js/buttons.js"></script>
<script src="../../static/js/modal.js"></script>

</body>
</html>