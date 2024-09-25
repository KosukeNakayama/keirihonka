<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<form method="POST" action="attTest2.jsp">

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
<jsp:useBean id="today" class="java.util.Date" />
&ltrif;
<fmt:formatDate value="${today}" type="DATE" dateStyle="FULL" />
&rtrif;
<br><br>


</form>

<br><br>
<a href="">メニューに戻る</a>

</body>
</html>