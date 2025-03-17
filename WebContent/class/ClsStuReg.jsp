<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>経理本科　クラス学生登録</title>
<link rel="stylesheet" href="/keirihonka/static/css/style.css">
</head>
<body>
<%@include file="../util/frame.jsp"%>
<!-- ここから下に各画面の要素を足していく -->
<div class="main">
<H2>クラス学生登録</H2>
<!-- コメント追加 -->
<c:if test="${!empty message}">
	<p>${message}</p>
</c:if>

<c:if  test="${!empty clsList}">
<form id="ClsGetStu" action="ClsGetStu">
	<p>学生登録クラス選択</p>
	<c:forEach var="cls" items="${clsList}">
		<label id="${cls.getClassName()}">
		<input type="radio" name="selectClass" value="${cls.classId}" <c:if test="${cls.classId == classId}">checked</c:if>>
		${cls.getClassName()} ${cls.startDate}～
		</label><br>
	</c:forEach>
	<input id="className" type="hidden" name="className" value="">
	<button id="clsSelect" type="submit">選択</button>
</form>
</c:if>

<c:if test="${!empty stuList}">
<form id="afterForm" action="ClsStuRegExe">
<h4>候補学生</h4>
<label>変更日<input type="date" name="dayOfChange"></label>
<table border="1">
<tr>
	<th class="th">学生番号</th>
	<th class="th">学生名</th>
	<th class="th">現クラス</th>
	<th class="th half">選択</th>
	<th class="th half">全<input type="checkbox" id="allCheck"></th>
</tr>
<c:forEach var="stu" items="${stuList}">
<tr>
	<td>${stu.studentId}</td>
	<td>${stu.studentName}</td>
	<td>${stu.classC.getClassName()}</td>
	<td colspan="2"><input type="checkbox" name="stuIds" value="${stu.studentId}"></td>
</tr>
</c:forEach>
</table>
<input type="hidden" name="classId" value="${classId}">
<input type="submit" id="AfterBtn" value="登録">
</form>
</c:if>
</div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="/keirihonka/static/js/util.js"></script>
</body>
</html>