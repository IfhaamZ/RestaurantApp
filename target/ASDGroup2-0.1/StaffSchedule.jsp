<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Schedule Management</title>
    <link rel="stylesheet" href="css/StaffSchedule.css" />
  </head>
  <body>
    <div class="container">
      <!-- Sidebar -->
      <div class="sidebar">
        <h1>Schedule</h1>
        <a href="StaffDashboard.jsp">Dashboard</a>
        <a href="StaffAttendance.jsp">Attendance</a>
        <a href="StaffSchedule.jsp">Schedule</a>
        <a href="StaffTask.jsp">Task</a>
        <a href="StaffNotifications.jsp">Notifications</a>
      </div>

      <!-- Main Content -->
      <div class="main-content">
        <!-- Schedule Header -->
        <div class="schedule-header">
          <h2>Schedule Management</h2>
          <div class="view-options">
            <button>Weekly View</button>
            <button>Monthly View</button>
          </div>
        </div>

        <!-- Schedule Calendar -->
        <div class="schedule-calendar">
          <table class="calendar-table">
            <tr>
              <th>Mon</th>
              <th>Tue</th>
              <th>Wed</th>
              <th>Thu</th>
              <th>Fri</th>
              <th>Sat</th>
              <th>Sun</th>
            </tr>
            <tr>
              <td>
                <div class="schedule-item">Team Meeting</div>
                <div class="schedule-item">Code Review</div>
              </td>
              <td></td>
              <td>
                <div class="schedule-item">Project Planning</div>
              </td>
              <td></td>
              <td>
                <div class="schedule-item">Client Presentation</div>
              </td>
              <td></td>
              <td></td>
            </tr>
            <!-- 나머지 행은 필요에 따라 추가 -->
          </table>
        </div>

        <!-- Schedule Detail Section -->
        <div class="schedule-detail">
          <h3>Schedule Details</h3>
          <p><strong>Title:</strong> Team Meeting</p>
          <p><strong>Date:</strong> 2024-10-01</p>
          <p><strong>Time:</strong> 10:00 AM - 11:00 AM</p>
          <p>
            <strong>Participants:</strong> John Doe, Jane Smith, Michael Brown
          </p>
          <p>
            <strong>Description:</strong> Discuss project milestones and review
            current issues.
          </p>
        </div>
      </div>
    </div>
  </body>
</html>
