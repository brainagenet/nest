<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>:: <c:out value="${title}" /> - brainage.net ::</title>
</head>
<body>

<h3>Welcom ${title}</h3>

<p>
    <a href="<c:url value="/account/signin/?next=/" />">Log in</a>
    | Need an account? <a href="<c:url value="/account/signup/" />">Sign up</a>.
</p>

</body>

</html>
