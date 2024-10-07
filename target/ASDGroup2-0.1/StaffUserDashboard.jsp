<!-- <%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Staff Dashboard</title>
  </head>
  <body>

  </body>
</html> -->

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="refresh" content="1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Staff Management Dashboard</title>
    <link rel="stylesheet" href="css/StaffDashboard.css" />
  </head>
  <body>
    <div class="container">
      <!-- Sidebar -->
      <div class="sidebar">
        <h1>Staff Dashboard</h1>
        <a href="StaffUserDashboard.jsp">Dashboard</a>
        <a href="StaffAttendance.jsp">Attendance</a>
        <a href="StaffSchedule.jsp">Schedule</a>
        <a href="StaffTask.jsp">Task</a>
        <a href="StaffNotifications.jsp">Notifications</a>
      </div>

      <!-- Main Content -->
      <div class="main-content">
        <!-- Staff Info & Calendar -->
        <div class="dashboard">
          <div class="section">
            <h2>Welcome John Doe</h2>
            <p>Staff ID: 101</p>
            <p>Role: Project Manager</p>
          </div>
          <div class="section calendar">
            <table>
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
                <td>1</td>
                <td class="work-day">2</td>
                <td>3</td>
                <td class="work-day">4</td>
                <td class="work-day">5</td>
                <td>6</td>
                <td>7</td>
              </tr>
              <tr>
                <td>8</td>
                <td>9</td>
                <td>10</td>
                <td>11</td>
                <td>12</td>
                <td>13</td>
                <td>14</td>
              </tr>
              <tr>
                <td>15</td>
                <td>16</td>
                <td class="work-day">17</td>
                <td class="work-day">18</td>
                <td class="work-day">19</td>
                <td>20</td>
                <td>21</td>
              </tr>
              <tr>
                <td class="work-day">22</td>
                <td class="work-day">23</td>
                <td class="work-day">24</td>
                <td class="work-day">25</td>
                <td>26</td>
                <td>27</td>
                <td>28</td>
              </tr>
              <tr>
                <td>29</td>
                <td>30</td>
                <td>31</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
            </table>
          </div>
        </div>

        <!-- Task List & Notifications -->
        <div class="dashboard">
          <div class="section task-list">
            <h3>To Do List</h3>
            <ul>
              <li>Creating a Project Plan</li>
              <li>Team meeting preparation</li>
              <li>Report Reveiw</li>
              <li>Training new employees</li>
            </ul>
          </div>
          <div class="section notifications">
            <h3>Notifications</h3>
            <ul>
              <li>Company Workshop: 15th October</li>
              <li>End date of apply annual leave: 30th October</li>
              <li>Annual Party: 24th December</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
