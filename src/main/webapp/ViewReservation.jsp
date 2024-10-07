<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>View Reservations</title>
    <link rel="stylesheet" href="css/ViewReservation.css">
    <style>
        /* Header styling */
        header {
            background-color: #ff6f61;
            color: white;
            padding: 20px 0;
            text-align: center;
        }

        header h1 {
            margin: 0;
            font-size: 2.5rem;
            font-weight: bold;
        }

        /* General Page Styling */
        body {
            background-color: #f4f4f9;
            color: #333;
            font-size: 16px;
            padding: 20px;
            margin: 0;
        }

        .container {
            max-width: 1000px;
            margin: 30px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #ff6f61;
            margin-bottom: 30px;
        }

        /* Table Styling */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        table th, table td {
            text-align: left;
            padding: 12px;
            border-bottom: 1px solid #ddd;
        }

        table th {
            background-color: #ff6f61;
            color: white;
        }

        table tr:hover {
            background-color: #f1f1f1;
        }

        /* Button styling */
        .back-btn {
            display: inline-block;
            padding: 10px 20px;
            margin-top: 20px;
            background-color: #ff6f61;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            text-align: center;
            transition: background-color 0.3s ease;
        }

        .back-btn:hover {
            background-color: #333;
        }
    </style>
</head>
<body>
    <header>
        <h1>All Reservations</h1>
    </header>

    <div class="container">
        <h2>Reservation List</h2>
        
        <!-- 예약 리스트를 표 형태로 출력 -->
        <table>
            <thead>
                <tr>
                    <th>Reservation ID</th>
                    <th>Name</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Guests</th>
                </tr>
            </thead>
            <tbody>
                <!-- JSP 스크립트릿을 사용하여 예약 데이터를 출력 -->
                <%
                    // 데이터베이스에서 가져온 예약 목록을 'listReservations'라는 속성으로 전달받음
                    java.util.List<model.Reservation> reservations = (java.util.List<model.Reservation>) request.getAttribute("listReservations");

                    // 예약 목록이 null이 아니고 비어있지 않을 때만 출력
                    if (reservations != null && !reservations.isEmpty()) {
                        for (model.Reservation reservation : reservations) {
                %>
                <tr>
                    <td><%= reservation.getId() %></td>
                    <td><%= reservation.getName() %></td>
                    <td><%= reservation.getDate() %></td>
                    <td><%= reservation.getTime() %></td>
                    <td><%= reservation.getGuests() %></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="5" style="text-align: center;">No Reservations Found</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <!-- 홈으로 돌아가기 버튼 -->
        <a href="index.jsp" class="back-btn">Back to Home</a>
    </div>
</body>
</html>
