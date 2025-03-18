<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<aside class="sidebar">
	<c:if test="${user.getManaged()}">
	<h3>管理職メニュー</h3>
	<div class="list">
        <div class="menu-item">
          <img class="arrow-drop-down" src="/keirihonka/static/img/down_triangle.png" />
          学生管理
        </div>
        <div class="content">
        <ul>
	        <li><a href="/keirihonka/stureg">学生登録</a></li>
	        <li><a href="/keirihonka/stuupd">学生更新</a></li>
	        <li><a href="">学生在籍管理</a></li>
	        <li><a href="">名簿出力</a></li>
        </ul>
        </div>
	</div>
	<div class="list">
		<div class="menu-item">
          <img class="arrow-drop-down" src="/keirihonka/static/img/down_triangle.png" />
          クラス管理
        </div>
        <div class="content">
        <ul>
        <li><a href="/keirihonka/Class/ClsReg">クラス登録</a></li>
        <li><a href="/keirihonka/Class/ClsUpd">クラス変更</a></li>
        <li><a href="/keirihonka/Class/ClsStuReg">クラス学生登録</a></li>
        <li><a href="/keirihonka/Class/ClsHst">クラス履歴確認</a></li>
        <li><a href="/keirihonka/Class/ClsDle">クラス削除</a></li>
        </ul>
        </div>
	</div>
	<div class="list">
		<div class="menu-item">
			<img class="arrow-drop-down" src="/keirihonka/static/img/down_triangle.png" />
			休日設定
		</div>
		<div class="content">
		<ul>
		<li><a href="">休日設定</a></li>
		</ul>
		</div>
	</div>
	</c:if>
	<h3>一般職メニュー</h3>
	<div class="list">
		<div class="menu-item">
			<img class="arrow-drop-down" src="/keirihonka/static/img/down_triangle.png" />
			出欠
		</div>
		<div class="content">
		<ul>
		<li><a href="/keirihonka/servlet/attend/AttReg">出欠入力(当日)</a></li>
		<li><a href="">出欠更新</a></li>
		<li><a href="/keirihonka/servlet/attend/SeatSet">座席情報設定</a></li>
		</ul>
		</div>
	</div>
	<div class="list">
		<div class="menu-item">
			<img class="arrow-drop-down" src="/keirihonka/static/img/down_triangle.png" />
			成績
		</div>
		<div class="content">
		<ul>
		<li><a href="/keirihonka/score/ScoReg">成績入力・更新</a></li>
		<li><a href="/keirihonka/score/scoRegCsv.jsp">成績入力（CSVファイル）</a></li>
		<li><a href="">成績出力（未実装）</a></li>
		<li><a href="/keirihonka/score/scoCnf.jsp">個人成績出力</a></li>
		</ul>
		</div>
	</div>
</aside>


