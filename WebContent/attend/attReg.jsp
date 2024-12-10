<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出欠入力（当日）</title>
<!--<link rel="stylesheet" href="./modaltest.css?<?php echo date('Ymd-Hi'); ?>" type="text/css">-->
<link rel="stylesheet" href="../static/css/attReg.css" type="text/css">
<script>
	var cancelInput = function(){
		document.getElementById('js-overlay').classList.remove('is-show')
		document.querySelectorAll('.js-modal').forEach((modal) => {
			modal.style.display = 'none'
        })
    }
</script>
</head>
<body>
<%@include file="../util/frame.jsp"%>
<!-- ここから下に各画面の要素を足していく -->

<table id="seats">
<tr>
	<td class="js-modalInput" namwe="att" data-modal="att"></td>
	<td class="js-modalInput" namwe="att" data-modal="att"></td>
	<td class="js-modalInput" namwe="att" data-modal="att"></td>
</tr>
<tr>
	<td class="js-modalInput" namwe="att" data-modal="att"></td>
	<td class="js-modalInput" namwe="att" data-modal="att"></td>
	<td class="js-modalInput" namwe="att" data-modal="att"></td>
</tr>
<tr>
	<td class="js-modalInput" namwe="att" data-modal="att" value="大原 太郎">大原 太郎</td>
	<td class="js-modalInput" namwe="att" data-modal="att" value="大原 二郎">大原 二郎</td>
	<td class="js-modalInput" namwe="att" data-modal="att" value="大原 花子">大原 花子</td>
</tr>
<tr>
	<td class="js-modalInput" namwe="att" data-modal="att" value="札幌 太郎">札幌 寿限無</td>
	<td class="js-modalInput" namwe="att" data-modal="att" value="札幌 二郎">札幌 二郎</td>
	<td class="js-modalInput" namwe="att" data-modal="att" value="札幌 花子">札幌 花子</td>
</tr>
</table>

<div id="js-overlay" class="overlay">
	<div id="att" class="js-modal modal">
	<p class="modal-header"><button onclick="cancelInput()">×</button></p>
	<label><input type="radio" name="a" value="0">出席　□</label>
	<label><input type="radio" name="a" value="1">欠席　<span style="color: red">■</span></label>
	<label><input type="radio" name="a" value="2">遅刻　<span style="color: yellow">■</span></label>
	<label><input type="radio" name="a" value="3">早退　<span style="color: violet">■</span></label>
	<label><input type="radio" name="a" value="4">他欠　<span style="color: greenyellow">■</span></label>
	<label><input type="radio" name="a" value="5">遅刻欠席　<span style="color: #db8449">■</span></label>
	<!--<label>コメント<textarea name="comment" placeholder="コメントを入力"></textarea></label>-->
	<label>コメント<input type="text" name="comment" placeholder="コメントを入力"></label>
	<label><button>登録</button></label>
	</div>
	</div>

<br><br>
<table id="platform">
	<tr><td>教壇</td></tr>
</table>

<script src="../static/js/attReg.js"></script>
</body>
</html>