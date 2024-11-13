<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includeSample.jsp"%>
<%@page import="bean.*,java.util.List"%>

<div>
	<h2>アップロードする成績ファイルを選択してください。</h2>

	<!-- ファイルをアップロードするフォーム -->
	<form action="ScoRegCsv" method="post"
		enctype="multipart/form-data">
		<input type="file" name="csvFile" accept=".csv" required>
		<button type="submit">表示</button>
	</form>

	<hr>

	<table>
		<thead>
			<tr>
				<th scope="col">学年</th>
				<th scope="col">月</th>
				<th scope="col">学生番号</th>
				<th scope="col">氏名</th>
				<th scope="col">科目名</th>
				<th scope="col">点数</th>
			</tr>
		</thead>
		<tbody>

		<tbody>
			<%
				// サーブレットから渡されたCSV内容のリストを表示
				List<String[]> csvData = (List<String[]>) request.getAttribute("csvData");

				if (csvData != null) {
					for (String[] row : csvData) {
						out.println("<tr>");
						for (String column : row) {
							out.println("<td>" + column + "</td>");
						}
						out.println("</tr>");
					}
				} else {
					out.println("<tr><td colspan='6'>No data available</td></tr>");
				}
			%></tbody>


	</table>
<input type="submit" value="登録">
</body>
</html>