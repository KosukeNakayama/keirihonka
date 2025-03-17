<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>経理本科　クラス登録</title>
<link rel="stylesheet" href="/keirihonka/static/css/style.css">
</head>
<body>
<%@include file="../util/frame.jsp"%>
<!-- ここから下に各画面の要素を足していく -->
<div class="main">
<H2>クラス登録</H2>
<!-- コメント追加 -->
<c:if test="${!empty message}">
	<p>${message}</p>
</c:if>

<form action="ClsRegExe">
	<label>年度
	<select id="schoolYear" name="schoolYear">
		<c:forEach var="i" begin="2022" end="2026">
			<option <c:if test="${i == schoolYear}">selected</c:if>>${i}</option>
		</c:forEach>
	</select>
	</label>
	<label>学年
	<select id="grade" name="grade">
		<c:forEach var="i" begin="1" end="3">
		<option <c:if test="${i == grade}">selected</c:if>>${i}</option>
		</c:forEach>
	</select>
	</label>
	<label>クラス番号
	<select id="classNo" name="classNo">
		<c:forEach var="i" begin="1" end="3">
		<option <c:if test="${i == classNo}">selected</c:if>>${i}</option>
		</c:forEach>
	</select>
	</label>
	<label>クラス開始日
	<input id="startDate" type="date" name="startDate" value="${startDate}">
	</label>
	<input type="submit" value="登録">
</form>
<%--
<c:if test="${!Objects.isNull(stuList)}">
<form id="afterForm" action="ClsRegExe">
<h4>所属学生</h4>
<c:forEach var="stu" items="${stuList}">
	<label><input type="checkbox" name="stu" value="${stu.studentId}">${stu.studentName}</label><br>
</c:forEach>
<input type="hidden" id="AfterSchoolYear" name="schoolYear" value="">
<input type="hidden" id="AfterGrade" name="grade" value="">
<input type="hidden" id="AfterClassNo" name="classNo" value="">
<input type="hidden" id="AfterStartDate" name="startDate" value="">
<input type="button" id="AfterBtn" value="登録">
</form>
</c:if>--%>
</div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="/keirihonka/static/js/util.js"></script>
<%-- <script>
	$(function(){
	  $("#AfterBtn").click(function(){
	    $("#AfterSchoolYear").val($("#schoolYear").val());
	    $("#AfterGrade").val($("#grade").val());
	    $("#AfterClassNo").val($("#classNo").val());
	    $("#AfterStartDate").val($("#startDate").val());
		$("#afterForm").submit();
	  });
	});
</script> --%>
</body>
</html>