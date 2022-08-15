<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="en">
<head>
    <title>Add K-PAC</title>
    <link rel="stylesheet" href="<c:url value="/resources/styles/style.css"/>">
</head>
<body>
    <header>
        <div class="navigation">
            <a href="${pageContext.request.contextPath}/kpacs">
                <button class="navigation-button text">All K-PACs</button>
            </a>
            <a href="${pageContext.request.contextPath}/index">
                <button class="navigation-button text">Main page</button>
            </a>
        </div>
    </header>
    <h1 class="text">Add K-PAC</h1>
    <form id="add-form">
        <span class="text">Title</span> <br>
        <input id="title" type="text" required> <br>
        <span class="text">Description</span> <br>
        <input id="description" type="text" required> <br>
        <button class="text submit-button" type="submit">Submit</button>
    </form>
    <script src="<c:url value="/resources/scripts/kpac_create.js"/>"></script>
</body>
</html>
