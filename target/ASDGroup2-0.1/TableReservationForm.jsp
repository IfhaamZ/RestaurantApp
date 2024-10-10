<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Reserve Table</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/TableReservationForm.css">
    <script>
        // JavaScript function to validate the form before submission
        function validateForm() {
            const reservationTime = document.getElementById("reservationTime").value;
            const currentDateTime = new Date();
            const selectedDateTime = new Date(reservationTime);

            if (selectedDateTime < currentDateTime) {
                alert("Reservation time must be in the future.");
                return false; // Prevent form submission
            }

            const phonePattern = /^[0-9\-\+]{9,15}$/;
            const phone = document.getElementById("phone").value;
            if (!phonePattern.test(phone)) {
                alert("Please enter a valid phone number.");
                return false;
            }

            return true; // Allow form submission if everything is valid
        }
    </script>
</head>
<body>
    <header>
        <h1>Reserve Table</h1>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="TableManagement.jsp">Back to Table Management</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <h2>Reserve Table ID: ${table.id}</h2>
        <form action="tablereserve" method="post" onsubmit="return validateForm()">
            <input type="hidden" name="tableId" value="${table.id}">

            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required pattern="[A-Za-z\s]+" title="Please enter a valid name.">

            <label for="phone">Phone Number:</label>
            <input type="tel" id="phone" name="phone" required pattern="[0-9\-\+]{9,15}" 
                   title="Please enter a valid phone number. Example: +123456789">

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required title="Please enter a valid email address.">

            <label for="reservationTime">Reservation Time:</label>
            <input type="datetime-local" id="reservationTime" name="reservationTime" required title="Please select a valid reservation time.">

            <button type="submit">Reserve Table</button>
            <a href="index.jsp" class="cancel-btn">Cancel</a>
        </form>
    </div>
</body>
</html>
