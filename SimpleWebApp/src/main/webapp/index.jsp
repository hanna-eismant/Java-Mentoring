<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Dynamic Page.</title>
</head>
<body>
    <h2>That is dynamic page served by Tomcat.</h2>
    <p>
        <c:if test="true"> It is true </c:if>
        <c:if test="false"> False </c:if>
    </p>
    <a href="/simplewebapp_static/index.html">Go to static</a>
</body>
</html>
