<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="bean.ClassC, java.util.List" %>

<% String req = ""; %>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../static/css/seatSet.css" type="text/css">
<link rel="stylesheet" href="../../static/css/buttons.css" type="text/css">
<title>座席情報設定</title>
</head>
<body>
<%@include file="../util/frame.jsp"%>
<!-- ここから下に各画面の要素を足していく -->
<!-- コメント追加 -->
<form method="POST" action="SeatSetDsp">

<!-- DBから取得 -->
<label>クラス</label>
<select name="className">
	<option value="" selected>--選択--</option>
	<c:forEach var="classList" items="${classList}">
		<option value="${classList.className}">${classList.className}</option>
	</c:forEach>
</select>
<input type="submit" class="operation-button" value="選択">

<br>

<label>座席配置（縦）</label>
<select name="seatRow">
<%
	for (int i=3; i<10; i++) {
		if (i == 5){
			req = "selected";
		} else {
			req = "";
		}
%>
		<option value="<%= i %>" <%= req %>><%= i %></option>
<%
	}
%>
</select>

<label>座席配置（横）</label>
<select name="seatCol">
<%
	for (int i=3; i<10; i++) {
		if (i == 4){
			req = "selected";
		} else {
			req = "";
		}
%>
		<option value="<%= i %>" <%= req %>><%= i %></option>
<%
	}
%>
</select>
</form>

<h3>登録が完了しました</h3>

<br><br>
<a href="/keirihonka/servlet/attend/SeatSet">メニューに戻る</a>

</body>
</html>