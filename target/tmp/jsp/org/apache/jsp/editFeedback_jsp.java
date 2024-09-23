/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.4.51.v20230217
 * Generated at: 2024-09-23 10:53:00 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.feedback;
import DAO.DBManager;

public final class editFeedback_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_classes.add("model.feedback");
    _jspx_imports_classes.add("DAO.DBManager");
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

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>Edit Feedback</title>\n");
      out.write("    <style>\n");
      out.write("        /* Basic Styling */\n");
      out.write("        body {\n");
      out.write("            font-family: 'Arial', sans-serif;\n");
      out.write("            background-color: #f4f4f9;\n");
      out.write("            color: #333;\n");
      out.write("            display: flex;\n");
      out.write("            justify-content: center;\n");
      out.write("            align-items: center;\n");
      out.write("            min-height: 100vh;\n");
      out.write("            margin: 0;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .container {\n");
      out.write("            width: 60%;\n");
      out.write("            background-color: white;\n");
      out.write("            padding: 30px;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        h1 {\n");
      out.write("            color: #007bff;\n");
      out.write("            font-size: 28px;\n");
      out.write("            text-align: center;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        label {\n");
      out.write("            font-size: 16px;\n");
      out.write("            font-weight: bold;\n");
      out.write("            color: #333;\n");
      out.write("            display: block;\n");
      out.write("            margin-bottom: 10px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        textarea, input[type=\"text\"] {\n");
      out.write("            width: 100%;\n");
      out.write("            padding: 12px;\n");
      out.write("            font-size: 16px;\n");
      out.write("            border: 1px solid #ccc;\n");
      out.write("            border-radius: 5px;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("            box-sizing: border-box;\n");
      out.write("            background-color: #f9f9f9;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        textarea {\n");
      out.write("            height: 100px;\n");
      out.write("            resize: vertical;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        input[type=\"submit\"] {\n");
      out.write("            background-color: #007bff;\n");
      out.write("            color: white;\n");
      out.write("            padding: 12px 20px;\n");
      out.write("            font-size: 18px;\n");
      out.write("            border: none;\n");
      out.write("            border-radius: 5px;\n");
      out.write("            cursor: pointer;\n");
      out.write("            width: 100%;\n");
      out.write("            transition: background-color 0.3s ease;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        input[type=\"submit\"]:hover {\n");
      out.write("            background-color: #0056b3;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        a {\n");
      out.write("            text-decoration: none;\n");
      out.write("            color: #007bff;\n");
      out.write("            display: block;\n");
      out.write("            margin-top: 20px;\n");
      out.write("            text-align: center;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        a:hover {\n");
      out.write("            text-decoration: underline;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .error-message {\n");
      out.write("            color: #dc3545;\n");
      out.write("            font-size: 18px;\n");
      out.write("            text-align: center;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        /* Responsive Styling */\n");
      out.write("        @media (max-width: 768px) {\n");
      out.write("            .container {\n");
      out.write("                width: 90%;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            h1 {\n");
      out.write("                font-size: 24px;\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("    ");

        int id = Integer.parseInt(request.getParameter("id"));
        DBManager dbManager = new DBManager();
        feedback fb = dbManager.getFeedbackById(id);

        if (fb == null) {  // Handle case where feedback is not found
    
      out.write("\n");
      out.write("        <h2>Feedback not found with ID: ");
      out.print( id );
      out.write("</h2>\n");
      out.write("        <a href=\"viewFeedback.jsp\">Back to Feedback Dashboard</a>\n");
      out.write("    ");

        } else {
    
      out.write("\n");
      out.write("\n");
      out.write("    <h1>Edit Feedback</h1>\n");
      out.write("\n");
      out.write("    <form action=\"/updateFeedback\" method=\"post\">\n");
      out.write("        <input type=\"hidden\" name=\"id\" value=\"");
      out.print( fb.getFeedbackId() );
      out.write("\">\n");
      out.write("\n");
      out.write("        <label for=\"customerName\">Customer Name:</label>\n");
      out.write("        <input id=\"customerName\" type=\"text\" name=\"customerName\" value=\"");
      out.print( fb.getCustomerName() );
      out.write("\" readonly>\n");
      out.write("\n");
      out.write("        <label for=\"feedbackText\">Feedback:</label>\n");
      out.write("        <textarea id=\"feedbackText\" name=\"feedbackText\" readonly>");
      out.print( fb.getFeedbackText() );
      out.write("</textarea>\n");
      out.write("\n");
      out.write("        <label for=\"rating\">Rating:</label>\n");
      out.write("        <input id=\"rating\" type=\"text\" name=\"rating\" value=\"");
      out.print( fb.getRating() );
      out.write("\" readonly>\n");
      out.write("\n");
      out.write("        <label for=\"staffResponse\">Staff Response:</label>\n");
      out.write("        <textarea id=\"staffResponse\" name=\"staffResponse\">");
      out.print( fb.getStaffResponse() != null ? fb.getStaffResponse() : "" );
      out.write("</textarea>\n");
      out.write("\n");
      out.write("        <input type=\"submit\" value=\"Update Feedback\">\n");
      out.write("    </form>\n");
      out.write("\n");
      out.write("    <a href=\"viewFeedback.jsp\">Back to Feedback Dashboard</a>\n");
      out.write("\n");
      out.write("    ");
 } 
      out.write("  <!-- End of the if-else block -->\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
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
