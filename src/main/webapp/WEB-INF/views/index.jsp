<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="en">
<head>
    <link rel="stylesheet" href="<c:url value="/resources/styles/style.css"/>">
    <title>Main page</title>
</head>
<body>
    <h1 class="text">Welcome!</h1>
    <h3 class="text">K-PACs</h3>

    <a href="${pageContext.request.contextPath}/kpacs">
        <button class="navigation-button text">View all K-PACs</button>
    </a>

    <a href="${pageContext.request.contextPath}/kpacs/add">
        <button class="navigation-button text">Add a K-PAC</button>
    </a>
    <h3 class="text">K-PAC Sets</h3>

    <a href="${pageContext.request.contextPath}/sets">
        <button class="navigation-button text">View all K-PAC sets</button>
    </a>

    <a href="${pageContext.request.contextPath}/sets/add">
        <button class="navigation-button text">Add a K-PAC set</button>
    </a>
</body>
</html>
