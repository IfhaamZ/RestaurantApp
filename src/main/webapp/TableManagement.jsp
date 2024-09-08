<%@ page import="java.util.List" %>
<%@ page import="model.Table" %>

<html>
<head>
    <title>Table Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table-management.css">
</head>
<body>
    <header>
        <h1>Table Management</h1>
    </header>

    <div class="container">
        <h2>Manage Tables</h2>
        <%-- <div class="table-list">
            <c:forEach var="table" items="${tables}">
                <div class="table-item">
                    <h3>Table ${table.id}</h3>
                    <p>Capacity: ${table.capacity}</p>
                    <p>Status: ${table.status}</p>
                    <form action="updateTableStatus" method="post">
                        <input type="hidden" name="tableId" value="${table.id}">
                        <select name="status">
                            <option value="Available" <%= (table.getStatus().equals("Available")) ? "selected" : "" %>>Available</option>
                            <option value="Occupied" <%= (table.getStatus().equals("Occupied")) ? "selected" : "" %>>Occupied</option>
                            <option value="Reserved" <%= (table.getStatus().equals("Reserved")) ? "selected" : "" %>>Reserved</option>
                        </select>
                        <button type="submit" class="btn">Update Status</button>
                    </form>
                </div>
            </c:forEach>
        </div> --%>
    </div>
</body>
</html>
<%-- Notes

- the tabel managemnt should check if all tables are free for the specified date
- set a fixed number of tables

 --%>
