<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="bean.ClassC, java.util.List" %>

<% String req = ""; %>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../static/css/buttons.css" type="text/css">
<title>出欠入力（当日）</title>
</head>
<body>
<%@include file="../util/frame.jsp"%>
<!-- ここから下に各画面の要素を足していく -->
<!-- コメント追加 -->
<form method="POST" action="AttRegDsp">

<!-- DBから取得 -->
<label>クラス</label>
<select name="classString">
	<option value="noSelect" selected>--選択--</option>
	<c:forEach var="classList" items="${classList}">
		<option value="${classList.className += ',' += classList.vertical += ',' += classList.horizontal}">${classList.className}</option>
	</c:forEach>
</select>
<input type="submit" class="operation-button" value="選択">

</form>

<br><br>
<a href="/keirihonka/servlet/attend/AttReg">メニューに戻る</a>

</body>
</html>