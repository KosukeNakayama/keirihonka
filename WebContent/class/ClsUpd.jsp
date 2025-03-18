<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>経理本科　クラス変更</title>
<link rel="stylesheet" href="/keirihonka/static/css/style.css">
</head>
<body>
<%@include file="../util/frame.jsp"%>
<!-- ここから下に各画面の要素を足していく -->
<div class="main">
<H2>クラス変更</H2>
<!-- コメント追加 -->
<c:if test="${!empty message}">
	<p>${message}</p>
</c:if>

<p>クラス変更は、所属する学生全員に適用されます。細心の注意を払って行って下さい。</p>

<form id="ClsGetStu" action="ClsSelect">
	<p>変更クラス選択</p>
	<c:forEach var="cls" items="${clsList}">
		<label id="${cls.getClassName()}">
		<input type="radio" name="selectClass" value="${cls.classId}" <c:if test="${cls.classId == classId}">checked</c:if>>
		${cls.getClassName()} ${cls.startDate}～
		</label><br>
	</c:forEach>
	<button id="clsSelect" type="submit">選択</button>
</form>

<c:if test="${!empty Cls}">
<form action="ClsUpdExe">
	<label>年度
	<select id="schoolYear" name="schoolYear">
		<c:forEach var="i" begin="2022" end="2026">
			<option <c:if test="${i == cls.schoolYear}">selected</c:if>>${i}</option>
		</c:forEach>
	</select>
	</label>
	<label>学年
	<select id="grade" name="grade">
		<c:forEach var="i" begin="1" end="3">
		<option <c:if test="${i == cls.grade}">selected</c:if>>${i}</option>
		</c:forEach>
	</select>
	</label>
	<label>クラス番号
	<select id="classNo" name="classNo">
		<c:forEach var="i" begin="1" end="3">
		<option <c:if test="${i == cls.classNo}">selected</c:if>>${i}</option>
		</c:forEach>
	</select>
	</label>
	<label>クラス開始日
	<input id="startDate" type="date" name="startDate" value="${cls.startDate}">
	</label>
	<input type="submit" value="更新">
</form>
</c:if>

</div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="/keirihonka/static/js/util.js"></script>
</body>
</html>