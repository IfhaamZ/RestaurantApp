/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.4.51.v20230217
 * Generated at: 2024-10-26 07:20:38 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.User;

public final class staffIndex_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("model.User");
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
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");

// Check if a user is logged in by retrieving the session attribute "user"
User user = (User) session.getAttribute("user");

// If no user is logged in, redirect to the login page
if (user == null || !user.getRole().equals("staff")) {
    response.sendRedirect("login.jsp");
    return;
}

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>Staff Dashboard - Restaurant Management System</title>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/css/staffIndex.css\">\r\n");
      out.write("    <!-- Link to Font Awesome or any icon library for the icons -->\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <header>\r\n");
      out.write("        <h1>Staff Dashboard - Restaurant Management System</h1>\r\n");
      out.write("    </header>\r\n");
      out.write("\r\n");
      out.write("    <section class=\"staff-content\">\r\n");
      out.write("        <h2>Welcome, ");
      out.print( user.getName() );
      out.write("!</h2>\r\n");
      out.write("        <br>\r\n");
      out.write("\r\n");
      out.write("        <!-- Card links for Event Management, Table Management, and Menu Management -->\r\n");
      out.write("        <div class=\"staff-links\">\r\n");
      out.write("            <div class=\"card\">\r\n");
      out.write("                <i class=\"fas fa-calendar-alt\"></i> <!-- con for Event Management -->\r\n");
      out.write("                <a href=\"staffeventlist\">Event Management</a>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"card\">\r\n");
      out.write("                <i class=\"fas fa-chair\"></i> <!-- icon for Table Management -->\r\n");
      out.write("                <a href=\"tablelist\">Table Management</a>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"card\">\r\n");
      out.write("                <i class=\"fas fa-utensils\"></i> <!--icon for Menu Management -->\r\n");
      out.write("                <a href=\"staffMenuDisplay\">Menu Management</a>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"card\">\r\n");
      out.write("                <i class=\"fas fa-comments\"></i> <i class=\"fas fa-exclamation-circle\"></i>                \r\n");
      out.write("                <a href=\"mainStaffDashboard\">Error & Feedback Management</a>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"card\">\r\n");
      out.write("                <i class=\"fas fa-boxes\"></i> <!-- Icon for Inventory Management -->\r\n");
      out.write("                <a href=\"inventoryview\">Inventory Management</a>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"card\">\r\n");
      out.write("                <i class=\"fas fa-receipt\"></i> <!-- Icon for Order Management -->\r\n");
      out.write("                <a href=\"stafforders\">Order Management</a>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"card\">\r\n");
      out.write("                <i class=\"fas fa-calendar\"></i> <!-- Icon for Order Management -->\r\n");
      out.write("                <a href=\"ReservationServlet?action=list\">Reservation Management</a>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <!-- Logout button -->\r\n");
      out.write("        <form action=\"/userlogout\" method=\"post\">\r\n");
      out.write("            <button type=\"submit\">Logout</button>\r\n");
      out.write("        </form>\r\n");
      out.write("    </section>\r\n");
      out.write("\r\n");
      out.write("    <footer>\r\n");
      out.write("        <p>2024 Restaurant Management System - Staff Dashboard.</p>\r\n");
      out.write("    </footer>\r\n");
      out.write("</body>\r\n");
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
