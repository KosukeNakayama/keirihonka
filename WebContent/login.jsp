<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Objects" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>経理本科　ログイン</title>
</head>
<body>
<header class="navigation">
	<h1 class="title">経理本科システム</h1>
</header>
<c:if test="${!empty message}">
	<p>${message}</p>
</c:if>
<form method="post" action="/keirihonka/loginExe">
	<input type="text" name="id">
	<input type="password" name="pw">
	<button type="submit">Login</button>
</form>

</body>
</html>

