<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includeSample.jsp"%>
<%@page import="bean.*,java.util.List"%>
<!-- メインエリア -->
<div class="main">
	<h2>アップロードする成績ファイルを選択してください。</h2>

	<!-- ファイルをアップロードするフォーム -->
	<form action="ScoRegCsv" method="post"
		enctype="multipart/form-data">
		<input type="file" name="csvFile" accept=".csv" required>
		<select name="month" id="month">
		<option value="1" selected >1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
		<option value="6">6</option>
		<option value="7">7</option>
		<option value="8">8</option>
		<option value="9">9</option>
		<option value="10">10</option>
		<option value="11">11</option>
		<option value="12">12</option>
	</select>
		<button type="submit">表示</button>
	</form>

	<hr>

	<table>
		<thead>
			<tr>
				<th scope="col">月</th>
				<th scope="col">学生番号</th>
				<th scope="col">氏名</th>
				<th scope="col">科目名</th>
				<th scope="col">点数</th>
			</tr>
		</thead>
		<tbody>
			<%
				// サーブレットから渡されたCSV内容のリストを表示
				List<String[]> csvData = (List<String[]>) request.getAttribute("csvData");
				String month=(String)request.getAttribute("month");
				if (csvData != null) {
					for (String[] row : csvData) {
						out.println("<tr>");
						out.println("<td>"+ month+"</td>");
						for (String column : row) {
							out.println("<td>" + column + "</td>");
						}
						out.println("</tr>");
					}
					out.println("<p>上記データを登録できました。</p>");
				} else {
					out.println("<tr><td colspan='6'>No data available</td></tr>");
				}
			%></tbody>
	</table>
</div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="/keirihonka/static/js/util.js"></script>
</body>
</html>