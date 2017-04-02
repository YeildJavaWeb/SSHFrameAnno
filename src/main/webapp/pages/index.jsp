<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authentication var="auth" property="principal" scope="session" />

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>登录</title>
	</head>
	<body>
		<h2>${auth }</h2>
		<c:if test="${param.error != null }">
			<p>Invalit username or password.</p>
		</c:if>
		<c:if test="${param.logout != null }">
			<p>You have been logged out!</p>
		</c:if>
		<form action="<c:url value='login_action'/>" method="post">
			Username:<input id="username" name="username" type="text"/><br/>
			Password:<input id="password" name="password" type="password"/><br/>
			<label><input type="checkbox" id="rememberme" name="rememberme"> Remember Me</label>
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/><br/>
			<input type="submit" value="登录" />
		</form>
		<a href="<c:url value='logout'/>">注销</a>
	</body>
</html>
