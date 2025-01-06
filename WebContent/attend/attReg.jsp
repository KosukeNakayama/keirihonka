<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="bean.ClassC, java.util.List" %>

<% String req = ""; %>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出欠入力</title>
</head>
<body>
<%@include file="../util/frame.jsp"%>
<!-- ここから下に各画面の要素を足していく -->
<!-- コメント追加 -->
<form method="POST" action="AttRegDsp">

<!-- DBから取得 -->
<label>クラス</label>
<select name="className">
	<option value="" selected>--選択--</option>
	<c:forEach var="classList" items="${studentList}">
		<option value="${studentList.className}">${studentList.className}</option>
	</c:forEach>
</select>
<input type="submit" value="選択">


</form>

<br><br>
<a href="/keirihonka/servlet/attend/AttSet">メニューに戻る</a>

</body>
</html>