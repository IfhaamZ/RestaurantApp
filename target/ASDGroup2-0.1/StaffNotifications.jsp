<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Notifications</title>
    <link rel="stylesheet" href="css/StaffNotifications.css" />
  </head>
  <body>
    <div class="container">
      <!-- Sidebar -->
      <div class="sidebar">
        <h1>Notifications</h1>
        <a href="StaffDashboard.jsp">Dashboard</a>
        <a href="StaffAttendance.jsp">Attendance</a>
        <a href="StaffSchedule.jsp">Schedule</a>
        <a href="StaffTask.jsp">Task</a>
        <a href="StaffNotifications.jsp">Notifications</a>
      </div>

      <!-- Main Content -->
      <div class="main-content">
        <!-- Notifications Header -->
        <div class="notification-header">
          <h2>Notification Center</h2>
          <select class="filter-dropdown">
            <option value="all">All Notifications</option>
            <option value="unread">Unread</option>
            <option value="important">Important</option>
          </select>
        </div>

        <!-- Notifications List -->
        <div class="notification-list">
          <h3>Recent Notifications</h3>
          <div class="notification-item unread">
            <p>New Company Policy Update</p>
            <span class="notification-date">2024-10-01</span>
          </div>
          <div class="notification-item read">
            <p>Team Meeting Scheduled</p>
            <span class="notification-date">2024-09-28</span>
          </div>
          <div class="notification-item unread">
            <p>Annual Company Workshop</p>
            <span class="notification-date">2024-09-15</span>
          </div>
          <div class="notification-item read">
            <p>New Benefits Package Announced</p>
            <span class="notification-date">2024-08-30</span>
          </div>
        </div>

        <!-- Notification Detail Section -->
        <div class="notification-detail">
          <h3>Notification Details</h3>
          <p><strong>Title:</strong> New Company Policy Update</p>
          <p><strong>Date:</strong> 2024-10-01</p>
          <p>
            <strong>Message:</strong> A new company policy has been released.
            Please review the policy document available in the company portal
            for more details.
          </p>
        </div>
      </div>
    </div>
  </body>
</html>
