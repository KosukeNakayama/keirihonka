<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String classC = request.getParameter("classC");
int seatRow = Integer.parseInt(request.getParameter("seatRow"));
int seatCol= Integer.parseInt(request.getParameter("seatCol"));

String[] classArray = {"1-1", "2-1", "3-1"};
String req = "";
%>


<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>座席情報設定</title>
</head>
<body>
<%@include file="../util/frame.jsp"%>
<!-- ここから下に各画面の要素を足していく -->
<!-- コメント追加 -->
<form method="POST" action="seatTest3.jsp">

<!-- 後程DBから取得 -->
<label>クラス</label>
<select name="classC">
	<option value="">--選択--</option>
<%
	for (int i=0; i < classArray.length; i++) {
		if (classArray[i].equals(classC)){
			req = "selected";
		} else {
			req = "";
		}
%>
		<option value="<%= classArray[i] %>" <%= req %>><%= classArray[i] %></option>
<%
	}
%>
</select>
<input type="button" value="選択">

<br><br>
<div>

<label>座席配置（縦）</label>
<select name="seatRow">
<%
	for (int i=0; i<10; i++) {
		if (seatRow == i){
			req = "selected";
		} else {
			req = "";
		}
%>
		<option value="<%= i %>" <%= req %>><%= i%></option>
<%
	}
%>
</select>

<label>座席配置（横）</label>
<select name="seatCol">
<%
	for (int i=0; i<10; i++) {
		if (seatCol == i){
			req = "selected";
		} else {
			req = "";
		}
%>
		<option value="<%= i %>" <%= req %>><%= i%></option>
<%
	}
%>
</select>
</div>

<div>
<c:set var="classC"><%= classC %></c:set>
<c:choose>
<c:when test="${!empty classC}">
    <a href=""><img src="../static/img/attend/seatSample.png" /></a>
    <input type="submit" value="登録">
</c:when>
<c:otherwise>
	<br>
	クラス未選択
</c:otherwise>
</c:choose>
</div>

<br><br>
<a href="">メニューに戻る</a>
</form>
</body>
</html>