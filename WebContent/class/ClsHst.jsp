<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>経理本科　クラス履歴</title>
<link rel="stylesheet" href="/keirihonka/static/css/style.css">
</head>
<body>
<%@include file="../util/frame.jsp"%>
<!-- ここから下に各画面の要素を足していく -->
<div class="main">
<H2>クラス履歴</H2>
<!-- コメント追加 -->
<c:if test="${!empty message}">
	<p>${message}</p>
</c:if>

<form id="GetStu" action="ClsHstGetStu">
	<h4>学生選択</h4>
	<label>学生番号
		<input type="text" name="studentId" id="studentId" value="${stu.studentId}">
	</label>
	<input id="stuSearchBtn" type="button" value="名前検索" onclick="showPopup()">
	<button type="submit">履歴表示</button>
</form>

<input type="hidden" name="isPopup" id="isPopup" value="${isPopup}">
<div id="modal" class="popup">
<div id="popup" class="popup-content">
    <h3 class="popup-title">学生検索</h3>
    <div class="popup-content">
        <form action="StuSearch">
        	<label>お名前<input type="text" name="name" value="${name }"></label>
        	<button type="submit">検索</button>
        </form>
		<c:if test="${!empty stuList}">
		<table border="1">
			<tr>
				<th class="th">学生番号</th>
				<th class="th">学生名</th>
				<th class="th">現クラス</th>
				<th class="th">選択</th>
			</tr>
			<c:forEach var="stu" items="${stuList}">
			<tr>
				<td>${stu.studentId}</td>
				<td>${stu.studentName}</td>
				<td>${stu.classC.getClassName()}</td>
				<td><input type="radio" name="stuIds" id="${stu.studentId}" value="${stu.studentId}"></td>
			</tr>
			</c:forEach>
		</table>
		</c:if>
    </div>
    <div class="popup-buttons">
        <button id="popupSubmit" onclick="hidePopup();">選択</button>
    </div>
</div>
</div>


<c:if test="${!empty stu}">
<h4>クラス履歴</h4>
<p>学生番号：${stu.studentId }</p>
<p>学生名　：${stu.studentName }</p>

<table border="1">
<tr>
	<th class="th">クラス</th>
	<th class="thlong">在籍期間</th>
</tr>
<c:forEach var="clsHst" items="${clsHstLst}">
<tr>
	<td>${clsHst.classC.getClassName()}</td>
	<td>${clsHst.startDate} ～ ${clsHst.endDate}</td>
</tr>
</c:forEach>
</table>
</c:if>
</div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="/keirihonka/static/js/util.js"></script>
</body>
</html>