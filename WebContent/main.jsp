<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>経理本科　メイン</title>
<link rel="stylesheet" href="/keirihonka/static/css/style.css">
</head>
<body>
<%@include file="util/frame.jsp"%>
<!-- ここから下に各画面の要素を足していく -->
<!-- メインエリア -->
<div class="main">
<H2>メニュー画面</H2>
<!-- コメント追加 -->
<c:if test="${!empty message}">
	<p>${message}</p>
</c:if>



<c:if test="${user.getManaged()}">
<div class="menu">
<H3>管理職メニュー</H3>
	<div class="list-left">
        <H4 class="menu-item">
          学生管理
        </H4>
        <ul class="main-ul">
        <li><a href="/keirihonka/stureg">学生登録</a></li>
        <li><a href="/keirihonka/stuUpd.jsp">学生更新</a></li>
		</ul>
	</div>
	<div class="list-mid">
		<H4 class="menu-item">
          クラス管理
        </H4>
        <ul class="main-ul">
        <li><a href="/keirihonka/Class/ClsReg">クラス登録</a></li>
        <li><a href="/keirihonka/Class/ClsUpd">クラス変更</a></li>
        <li><a href="/keirihonka/Class/ClsStuReg">クラス学生登録</a></li>
        <li><a href="/keirihonka/Class/ClsHst">クラス履歴確認</a></li>
        <li><a href="/keirihonka/Class/ClsDle">クラス削除</a></li>
        </ul>
	</div>
</div>
</c:if>

<div class="menu">
<H3>一般メニュー</H3>
	<div class="list-left">
		<H4 class="menu-item">
			出欠
		</H4>
        <ul class="main-ul">
		<li><a href="/keirihonka/servlet/attend/AttReg">出欠入力(当日)</a></li>
		<li><a href="/keirihonka/servlet/attend/SeatSet">座席情報設定</a></li>
		</ul>
	</div>
	<div class="list-mid">
		<H4 class="menu-item">
			成績
		</H4>
		<ul class="main-ul">
		<li><a href="/keirihonka/score/ScoReg">成績入力・更新</a></li>
		<li><a href="/keirihonka/score/scoRegCsv.jsp">成績入力（CSVファイル）</a></li>
		<li><a href="/keirihonka/score/scoCnf.jsp">個人成績出力</a></li>
		</ul>
	</div>
</div>
</div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="/keirihonka/static/js/util.js"></script>
</body>
</html>
