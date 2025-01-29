<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../includeSample.jsp"%>
<%@ page import="bean.*,java.util.List"%>

<form method="post" action="ScoReg">
	<div>
		<%
			List<ClassC> list = (List<ClassC>) request.getAttribute("list");
		%>
		<%
			List<Subject> list2 = (List<Subject>) request.getAttribute("list2");
		%>

		<%
			int i = 0;
		%>

		<label for="class_id">クラス:</label> <select name="class_id"
			id="class_id">
			<%
				for (ClassC p : list) {
			%>
			<option value="<%=p.getClassId()%>">
				<%=p.getGrade()%> 年
				<%=p.getClassNo()%>組
			</option>
			<%
				}
			%>
		</select> <label for="month">月:</label> <select name="month" id="month">
			<option value="1">1</option>
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
		</select> <label for="subject_id">科目名:</label> <select name="subject_id"
			id="subject_id">
			<%
				for (Subject s : list2) {
			%>
			<option value="<%=s.getSubjectId()%>">
				<%=s.getSubjectName()%>
			</option>
			<%
				}
			%>
		</select> <input type="submit" value="選択">
	</div>
</form>
<form method="post" action="ScoRegExe">
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
			<%
				// リクエストから list3 を取得
				List<Student> list3 = (List<Student>) request.getAttribute("list3");

				// list3 が null でないことを確認
				if (list3 != null && !list3.isEmpty()) {
			%>

		<tbody>
			<%
				for (Student student : list3) {
						if (student != null) { // student オブジェクトが null でないことを確認
			%>
			<tr>
				<td><%=request.getAttribute("grade")%></td>
				<td><%=request.getAttribute("month")%></td>
				<td><%=student.getStudentId()%></td>
				<td><%=student.getStudentName()%></td>
				<td><%=request.getAttribute("subject")%></td>
				<td><input type="text" name="score-<%=i%>"></td>
				<input type="hidden" name="student_id-<%=i%>"
					value="<%=student.getStudentId()%>">
			</tr>
			<%
				}
						i++;
					} // for ループ終了
			%>
		</tbody>
		<%
			} else {
		%>
		<tr>
			<td colspan="6">データがありません。</td>
		</tr>
		<%
			}
		%>

		</tbody>
	</table>
	<%
		// リクエストメソッドを取得
		String method = request.getMethod();
	%>
	<%
		if ("POST".equalsIgnoreCase(method)) {
	%>
	<input type="hidden" name="subject_id"
		value='<%=request.getAttribute("subject_id")%>'>
	<input
		type="hidden" name="month" value='<%=request.getAttribute("month")%>'>
	<input type="hidden" name="class_id" value='<%=request.getAttribute("class_id")%>'>
	<input type="hidden" name="grade"
		value='<%=request.getAttribute("grade")%>'> <input
		type="hidden" name="count" value='<%=i%>'> <input
		type="submit" value="登録">
	<%
		}
	%>
</form>
