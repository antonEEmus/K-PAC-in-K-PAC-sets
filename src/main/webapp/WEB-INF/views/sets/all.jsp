<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="en">
<head>
    <title>K-PAC Sets</title>
    <link rel="stylesheet" href="<c:url value="/resources/codebase/grid.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/styles/style.css"/>">
    <script src="<c:url value="/resources/codebase/grid.js"/>"></script>
</head>
<body>
    <header>
        <div class="navigation">
            <a href="${pageContext.request.contextPath}/sets/add">
                <button class="navigation-button text">Add a K-PAC set</button>
            </a>
            <a href="${pageContext.request.contextPath}/index">
                <button class="navigation-button text">Main page</button>
            </a>
        </div>
    </header>
    <h1 class="text">K-PAC Sets</h1>

    <div class="grid-container"></div>

    <script type="text/javascript">
        const sets = JSON.parse('${sets}');
    </script>
    <script src="<c:url value="/resources/scripts/set_grid.js"/>"></script>

    <div class="filter">
        <p class="text filter-header">Filter</p>

        <p class="text">Title</p>
        <input type="text" id="title">

        <p class="text">Description</p>
        <input type="text" id="description"> <br>

        <button id="apply" class="filter-button">Apply Filter</button>
        <button id="reset" class="filter-button">Reset Filter</button>
    </div>

    <script src="<c:url value="/resources/scripts/set_filter.js"/>"></script>
</body>
</html>
