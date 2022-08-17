<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="en">
<head>
    <title>Add K-PAC Set</title>
    <link rel="stylesheet" href="<c:url value="/resources/styles/style.css"/>">
</head>
<body>
    <header>
        <div class="navigation">
            <a href="${pageContext.request.contextPath}/sets">
                <button class="navigation-button">All K-PAC Sets</button>
            </a>
            <a href="${pageContext.request.contextPath}/index">
                <button class="navigation-button">Main page</button>
            </a>
        </div>
    </header>
    <h1 class="text">Add K-PAC Set</h1>
    <form id="add-form">
        <span class="text">Title </span>
        <input id="title" type="text" maxlength="250" required>
        <br>
        <span class="text">Choose K-PACs:</span>
        <br>
        <c:forEach items="${kpacs}" var="kpac">
            <label>
                <input class="kpac-checkbox" id="${kpac.id}" type="checkbox">
                <c:out value="${kpac.title}" />
                <br>
            </label>
        </c:forEach>
        <button class="text submit-button" type="submit">Submit</button>
    </form>
    <script src="<c:url value="/resources/scripts/set_create.js"/>"></script>
</body>
</html>
