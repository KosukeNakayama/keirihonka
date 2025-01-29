<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
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
<form method="POST" action="seatTest2.jsp">

<!-- 後程DBから取得 -->
<label>クラス</label>
<select name="classC">
	<option value="" selected>--選択--</option>
<%
	for (int i=0; i < classArray.length; i++) {

%>
		<option value="<%= classArray[i] %>"><%= classArray[i] %></option>
<%
	}
%>
</select>
<input type="submit" value="選択">

<br><br>

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

<br><br>
<a href="">メニューに戻る</a>

</body>
</html>