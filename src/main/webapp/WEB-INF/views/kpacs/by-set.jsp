<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="en">
<head>
    <title>${title} K-PACs</title>
    <link rel="stylesheet" href="<c:url value="/resources/styles/style.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/codebase/grid.css"/>">
    <script src="<c:url value="/resources/codebase/grid.js"/>"></script>
</head>
<body>
    <header>
        <div class="navigation">
            <a href="${pageContext.request.contextPath}/kpacs">
                <button class="navigation-button text">All K-PACs</button>
            </a>
            <a href="${pageContext.request.contextPath}/kpacs/add">
                <button class="navigation-button text">Add a K-PAC</button>
            </a>
            <a href="${pageContext.request.contextPath}/index">
                <button class="navigation-button text">Main page</button>
            </a>
        </div>
    </header>
    <h1 class="text">${title} K-PACs</h1>
    <div class="grid-container"></div>
    <script>
        const setKpacs = JSON.parse('${kpacs}');
    </script>
    <script src="<c:url value="/resources/scripts/by_set_grid.js"/>"></script>
</body>
</html>
