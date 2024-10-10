<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Staff Attendace Report</title>
    <link rel="stylesheet" href="css/Attendance.css" />
  </head>
  <body>
    <div class="container">
      <!-- Sidebar -->
      <div class="sidebar">
        <h1>Attendance</h1>
        <a href="StaffDashboard.jsp">Dashboard</a>
        <a href="StaffAttendance.jsp">Attendance</a>
        <a href="StaffSchedule.jsp">Schedule</a>
        <a href="StaffTask.jsp">Task</a>
        <a href="StaffNotifications.jsp">Notifications</a>
      </div>

      <div class="main-content">
        <!-- 필터 섹션 -->
        <div class="filter-section">
          <label for="employee">Filter By:</label>
          <select id="employee">
            <option>Phyllis L. Maddox</option>
            <option>John Doe</option>
            <option>Jane Smith</option>
          </select>

          <input
            type="text"
            id="date-range"
            placeholder="2020-03-01 - 2020-12-31"
          />
        </div>

        <!-- 통계 정보 섹션 -->
        <div class="statistics">
          <div class="stat-item">
            <h3>60</h3>
            <p>WORKING DAYS</p>
          </div>
          <div class="stat-item">
            <h3>66</h3>
            <p>PRESENT</p>
          </div>
          <div class="stat-item">
            <h3>-6</h3>
            <p>ABSENT</p>
          </div>
          <div class="stat-item">
            <h3>522:41:09</h3>
            <p>TOTAL WORKING HOURS</p>
          </div>
          <div class="stat-item">
            <h3>07:55:10</h3>
            <p>AVG. WORKING HOURS</p>
          </div>
          <div class="stat-item">
            <h3>387:41:40</h3>
            <p>TRACKED HOURS</p>
          </div>
        </div>

        <!-- 달력 섹션 -->
        <div class="calendar-section">
          <h4>March</h4>
          <table class="calendar">
            <tr>
              <th>MON</th>
              <th>TUE</th>
              <th>WED</th>
              <th>THU</th>
              <th>FRI</th>
              <th>SAT</th>
              <th>SUN</th>
            </tr>
            <tr>
              <td class="work-day">1</td>
              <td>2</td>
              <td class="work-day">3</td>
              <td>4</td>
              <td class="work-day">5</td>
              <td>6</td>
              <td>7</td>
            </tr>
            <!-- 나머지 행은 필요에 따라 추가 -->
          </table>

          <h4>April</h4>
          <table class="calendar">
            <tr>
              <th>MON</th>
              <th>TUE</th>
              <th>WED</th>
              <th>THU</th>
              <th>FRI</th>
              <th>SAT</th>
              <th>SUN</th>
            </tr>
            <tr>
              <td class="work-day">1</td>
              <td>2</td>
              <td>3</td>
              <td>4</td>
              <td class="work-day">5</td>
              <td>6</td>
              <td>7</td>
            </tr>
            <!-- 나머지 행은 필요에 따라 추가 -->
          </table>
        </div>
      </div>
    </div>
  </body>
</html>
