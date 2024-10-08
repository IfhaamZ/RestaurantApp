/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.4.51.v20230217
 * Generated at: 2024-10-08 00:09:38 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class StaffAttendance_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"ko\">\r\n");
      out.write("  <head>\r\n");
      out.write("    <meta charset=\"UTF-8\" />\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n");
      out.write("    <title>Staff Attendace Report</title>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/Attendance.css\" />\r\n");
      out.write("  </head>\r\n");
      out.write("  <body>\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("      <!-- Sidebar -->\r\n");
      out.write("      <div class=\"sidebar\">\r\n");
      out.write("        <h1>Attendance</h1>\r\n");
      out.write("        <a href=\"StaffDashboard.jsp\">Dashboard</a>\r\n");
      out.write("        <a href=\"StaffAttendance.jsp\">Attendance</a>\r\n");
      out.write("        <a href=\"StaffSchedule.jsp\">Schedule</a>\r\n");
      out.write("        <a href=\"StaffTask.jsp\">Task</a>\r\n");
      out.write("        <a href=\"StaffNotifications.jsp\">Notifications</a>\r\n");
      out.write("      </div>\r\n");
      out.write("\r\n");
      out.write("      <div class=\"main-content\">\r\n");
      out.write("        <!-- íí° ì¹ì -->\r\n");
      out.write("        <div class=\"filter-section\">\r\n");
      out.write("          <label for=\"employee\">Filter By:</label>\r\n");
      out.write("          <select id=\"employee\">\r\n");
      out.write("            <option>Phyllis L. Maddox</option>\r\n");
      out.write("            <option>John Doe</option>\r\n");
      out.write("            <option>Jane Smith</option>\r\n");
      out.write("          </select>\r\n");
      out.write("\r\n");
      out.write("          <input\r\n");
      out.write("            type=\"text\"\r\n");
      out.write("            id=\"date-range\"\r\n");
      out.write("            placeholder=\"2020-03-01 - 2020-12-31\"\r\n");
      out.write("          />\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <!-- íµê³ ì ë³´ ì¹ì -->\r\n");
      out.write("        <div class=\"statistics\">\r\n");
      out.write("          <div class=\"stat-item\">\r\n");
      out.write("            <h3>60</h3>\r\n");
      out.write("            <p>WORKING DAYS</p>\r\n");
      out.write("          </div>\r\n");
      out.write("          <div class=\"stat-item\">\r\n");
      out.write("            <h3>66</h3>\r\n");
      out.write("            <p>PRESENT</p>\r\n");
      out.write("          </div>\r\n");
      out.write("          <div class=\"stat-item\">\r\n");
      out.write("            <h3>-6</h3>\r\n");
      out.write("            <p>ABSENT</p>\r\n");
      out.write("          </div>\r\n");
      out.write("          <div class=\"stat-item\">\r\n");
      out.write("            <h3>522:41:09</h3>\r\n");
      out.write("            <p>TOTAL WORKING HOURS</p>\r\n");
      out.write("          </div>\r\n");
      out.write("          <div class=\"stat-item\">\r\n");
      out.write("            <h3>07:55:10</h3>\r\n");
      out.write("            <p>AVG. WORKING HOURS</p>\r\n");
      out.write("          </div>\r\n");
      out.write("          <div class=\"stat-item\">\r\n");
      out.write("            <h3>387:41:40</h3>\r\n");
      out.write("            <p>TRACKED HOURS</p>\r\n");
      out.write("          </div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <!-- ë¬ë ¥ ì¹ì -->\r\n");
      out.write("        <div class=\"calendar-section\">\r\n");
      out.write("          <h4>March</h4>\r\n");
      out.write("          <table class=\"calendar\">\r\n");
      out.write("            <tr>\r\n");
      out.write("              <th>MON</th>\r\n");
      out.write("              <th>TUE</th>\r\n");
      out.write("              <th>WED</th>\r\n");
      out.write("              <th>THU</th>\r\n");
      out.write("              <th>FRI</th>\r\n");
      out.write("              <th>SAT</th>\r\n");
      out.write("              <th>SUN</th>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("              <td class=\"work-day\">1</td>\r\n");
      out.write("              <td>2</td>\r\n");
      out.write("              <td class=\"work-day\">3</td>\r\n");
      out.write("              <td>4</td>\r\n");
      out.write("              <td class=\"work-day\">5</td>\r\n");
      out.write("              <td>6</td>\r\n");
      out.write("              <td>7</td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <!-- ëë¨¸ì§ íì íìì ë°ë¼ ì¶ê° -->\r\n");
      out.write("          </table>\r\n");
      out.write("\r\n");
      out.write("          <h4>April</h4>\r\n");
      out.write("          <table class=\"calendar\">\r\n");
      out.write("            <tr>\r\n");
      out.write("              <th>MON</th>\r\n");
      out.write("              <th>TUE</th>\r\n");
      out.write("              <th>WED</th>\r\n");
      out.write("              <th>THU</th>\r\n");
      out.write("              <th>FRI</th>\r\n");
      out.write("              <th>SAT</th>\r\n");
      out.write("              <th>SUN</th>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("              <td class=\"work-day\">1</td>\r\n");
      out.write("              <td>2</td>\r\n");
      out.write("              <td>3</td>\r\n");
      out.write("              <td>4</td>\r\n");
      out.write("              <td class=\"work-day\">5</td>\r\n");
      out.write("              <td>6</td>\r\n");
      out.write("              <td>7</td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <!-- ëë¨¸ì§ íì íìì ë°ë¼ ì¶ê° -->\r\n");
      out.write("          </table>\r\n");
      out.write("        </div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("  </body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
