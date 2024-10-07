<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Task Management</title>
    <link rel="stylesheet" href="css/StaffTask.css" />
  </head>
  <body>
    <div class="container">
      <!-- Sidebar -->
      <div class="sidebar">
        <h1>Task Dashboard</h1>
        <a href="StaffDashboard.jsp">Dashboard</a>
        <a href="StaffAttendance.jsp">Attendance</a>
        <a href="StaffSchedule.jsp">Schedule</a>
        <a href="StaffTask.jsp">Task</a>
        <a href="StaffNotifications.jsp">Notifications</a>
      </div>

      <!-- Main Content -->
      <div class="main-content">
        <!-- Task Header -->
        <div class="task-header">
          <h2>Task Management</h2>
          <button class="add-task">+ Add New Task</button>
        </div>

        <!-- Task List -->
        <div class="task-list">
          <h3>Current Tasks</h3>
          <div class="task-item">
            <p>Create project plan</p>
            <span class="task-status status-pending">Pending</span>
          </div>
          <div class="task-item">
            <p>Team meeting preparation</p>
            <span class="task-status status-complete">Complete</span>
          </div>
          <div class="task-item">
            <p>Review project report</p>
            <span class="task-status status-onhold">On Hold</span>
          </div>
          <div class="task-item">
            <p>New employee training</p>
            <span class="task-status status-pending">Pending</span>
          </div>
        </div>

        <!-- Task Detail Section -->
        <div class="task-detail">
          <h3>Task Details</h3>
          <p><strong>Task:</strong> Review project report</p>
          <p><strong>Assigned to:</strong> John Doe</p>
          <p><strong>Due Date:</strong> 2024-10-20</p>
          <p><strong>Status:</strong> On Hold</p>
          <p>
            <strong>Description:</strong> Review the entire project report for
            accuracy and completeness before the next meeting.
          </p>
        </div>
      </div>
    </div>
  </body>
</html>
