<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header class="navigation">
	<a href="/keirihonka/main"><h1 class="title">経理本科システム</h1></a>
	<div class="items">
		<div class="text-wrapper">${user.userId}</div>
		<form action="/keirihonka/logout">
			<button type="submit">Logout</button>
		</form>
	</div>
</header>
