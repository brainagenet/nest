<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Title</title>
</head>
<body>

<h3>Sign In</h3>

<p>
    <a href="<c:url value="/account/password/reset/" />">Forgot your password?</a>
    | Need an account? <a href="<c:url value="/account/signup/" />">Sign up</a>.
</p>

</body>

</html>
