<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authentication var="auth" property="principal" scope="session" />

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>开发人员</title>
	</head>
	<body>
		<h2>${auth }</h2>
		<h3>${ auth.username }</h3>
		<a href="<c:url value='/logout'/>">注销</a>
	</body>
</html>
