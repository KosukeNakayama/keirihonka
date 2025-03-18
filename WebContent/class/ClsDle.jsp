<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>経理本科　クラス削除</title>
<link rel="stylesheet" href="/keirihonka/static/css/style.css">
</head>
<body>
<%@include file="../util/frame.jsp"%>
<!-- ここから下に各画面の要素を足していく -->
<div class="main">
<H2>クラス削除</H2>
<!-- コメント追加 -->
<c:if test="${!empty message}">
	<p>${message}</p>
</c:if>

<c:if  test="${!empty clsList}">
<form id="ClsGetStu" action="ClsDleExe">
	<label>クラス終了日<input type="date" name="dayOfDelete"></label>
	<p>削除クラス選択</p>
	<c:forEach var="cls" items="${clsList}">
		<label id="${cls.getClassName()}">
		<input type="radio" name="selectClass" value="${cls.classId}" <c:if test="${cls.classId == classId}">checked</c:if>>
		${cls.getClassName()} ${cls.startDate}～
		</label><br>
	</c:forEach>
	<button id="clsSelect" type="submit">削除</button>
</form>
</c:if>

</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="/keirihonka/static/js/util.js"></script>
</body>
</html>