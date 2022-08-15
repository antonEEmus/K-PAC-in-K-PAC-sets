<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <title>K-PACs</title>
    <link rel="stylesheet" href="<c:url value="/resources/codebase/grid.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/styles/style.css"/>">
    <script src="<c:url value="/resources/codebase/grid.js"/>"></script>
</head>
<body>
    <header>
        <div class="navigation">
            <a href="${pageContext.request.contextPath}/kpacs/add">
                <button class="navigation-button">Add a K-PAC</button>
            </a>
            <a href="${pageContext.request.contextPath}/index">
                <button class="navigation-button">Main page</button>
            </a>
        </div>
    </header>
    <h1 class="text">K-PACs</h1>

    <div class="grid-container"></div>

    <script type="text/javascript">
        const kpacs = JSON.parse('${kpacs}');
    </script>
    <script src="<c:url value="/resources/scripts/kpac_grid.js"/>"></script>

    <div class="filter">
        <p class="text filter-header">Filter</p>

        <p class="text">Title</p>
        <input type="text" id="title">

        <p class="text">Description</p>
        <input type="text" id="description">

        <p class="text">Creation date</p>
        <label class="text">From: <input type="date" id="date-from"></label>
        <label class="text">To: <input type="date" id="date-to"></label> <br>

        <button id="apply" class="filter-button">Apply Filter</button>
        <button id="reset" class="filter-button">Reset Filter</button>
    </div>

    <script src="<c:url value="/resources/scripts/kpac_filter.js"/>"></script>
</body>
</html>
