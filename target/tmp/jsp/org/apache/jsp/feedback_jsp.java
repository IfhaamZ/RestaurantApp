/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.4.51.v20230217
 * Generated at: 2024-09-25 08:49:00 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class feedback_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    <title>Submit Feedback</title>\r\n");
      out.write("    <style>\r\n");
      out.write("        /* Basic Styles */\r\n");
      out.write("        body {\r\n");
      out.write("            font-family: 'Arial', sans-serif;\r\n");
      out.write("            background-color: #f4f4f9;\r\n");
      out.write("            margin: 0;\r\n");
      out.write("            padding: 0;\r\n");
      out.write("            color: #333;\r\n");
      out.write("            display: flex;\r\n");
      out.write("            justify-content: center;\r\n");
      out.write("            align-items: center;\r\n");
      out.write("            min-height: 100vh;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .container {\r\n");
      out.write("            width: 50%;\r\n");
      out.write("            margin: 50px auto;\r\n");
      out.write("            padding: 30px;\r\n");
      out.write("            background-color: white;\r\n");
      out.write("            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);\r\n");
      out.write("            border-radius: 10px;\r\n");
      out.write("            transition: all 0.3s ease;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .container:hover {\r\n");
      out.write("            box-shadow: 0 12px 24px rgba(0, 0, 0, 0.2);\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        h2 {\r\n");
      out.write("            color: #333;\r\n");
      out.write("            font-size: 26px;\r\n");
      out.write("            font-weight: 600;\r\n");
      out.write("            text-align: center;\r\n");
      out.write("            margin-bottom: 20px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .form-group {\r\n");
      out.write("            margin-bottom: 20px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .form-group label {\r\n");
      out.write("            font-weight: bold;\r\n");
      out.write("            display: block;\r\n");
      out.write("            margin-bottom: 5px;\r\n");
      out.write("            color: #333;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .form-group input[type=\"text\"],\r\n");
      out.write("        .form-group input[type=\"email\"],\r\n");
      out.write("        .form-group textarea,\r\n");
      out.write("        .form-group select {\r\n");
      out.write("            width: 100%;\r\n");
      out.write("            padding: 12px;\r\n");
      out.write("            font-size: 16px;\r\n");
      out.write("            border: 1px solid #ccc;\r\n");
      out.write("            border-radius: 5px;\r\n");
      out.write("            box-sizing: border-box;\r\n");
      out.write("            transition: border-color 0.3s ease;\r\n");
      out.write("            background-color: #f9f9f9;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .form-group input:focus,\r\n");
      out.write("        .form-group textarea:focus,\r\n");
      out.write("        .form-group select:focus {\r\n");
      out.write("            border-color: #007bff;\r\n");
      out.write("            background-color: #fff;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .form-group textarea {\r\n");
      out.write("            height: 120px;\r\n");
      out.write("            resize: vertical;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .submit-btn {\r\n");
      out.write("            display: block;\r\n");
      out.write("            width: 100%;\r\n");
      out.write("            padding: 15px;\r\n");
      out.write("            background-color: #007bff;\r\n");
      out.write("            color: white;\r\n");
      out.write("            border: none;\r\n");
      out.write("            border-radius: 5px;\r\n");
      out.write("            font-size: 18px;\r\n");
      out.write("            font-weight: bold;\r\n");
      out.write("            cursor: pointer;\r\n");
      out.write("            transition: background-color 0.3s ease, box-shadow 0.3s ease;\r\n");
      out.write("            text-align: center;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .submit-btn:hover {\r\n");
      out.write("            background-color: #0056b3;\r\n");
      out.write("            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .submit-message {\r\n");
      out.write("            display: none;\r\n");
      out.write("            text-align: center;\r\n");
      out.write("            margin-top: 20px;\r\n");
      out.write("            color: #28a745;\r\n");
      out.write("            font-size: 16px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .view-feedback {\r\n");
      out.write("            text-align: center;\r\n");
      out.write("            margin-top: 20px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .view-feedback a {\r\n");
      out.write("            text-decoration: none;\r\n");
      out.write("            color: #007bff;\r\n");
      out.write("            font-weight: bold;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .view-feedback a:hover {\r\n");
      out.write("            color: #0056b3;\r\n");
      out.write("            text-decoration: underline;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        @media (max-width: 768px) {\r\n");
      out.write("            .container {\r\n");
      out.write("                width: 90%;\r\n");
      out.write("            }\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("\r\n");
      out.write("    <script>\r\n");
      out.write("        function validateForm() {\r\n");
      out.write("            const name = document.getElementById('name').value.trim();\r\n");
      out.write("            const email = document.getElementById('email').value.trim();\r\n");
      out.write("            const rating = document.getElementById('rating').value.trim();\r\n");
      out.write("            const comments = document.getElementById('comments').value.trim();\r\n");
      out.write("            const submitMessage = document.getElementById('submit-message');\r\n");
      out.write("            const submitButton = document.getElementById('submit-btn');\r\n");
      out.write("\r\n");
      out.write("            if (name === '' || email === '' || rating === '' || comments === '') {\r\n");
      out.write("                alert('Please fill out all required fields.');\r\n");
      out.write("                return false; // Prevent form submission\r\n");
      out.write("            }\r\n");
      out.write("\r\n");
      out.write("            submitMessage.style.display = 'block';\r\n");
      out.write("            submitButton.disabled = true;\r\n");
      out.write("            submitButton.textContent = 'Submitting...';\r\n");
      out.write("\r\n");
      out.write("            return true; // Allow form submission\r\n");
      out.write("        }\r\n");
      out.write("    </script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("        <div class=\"form-header\">\r\n");
      out.write("            <h2>Submit Feedback</h2>\r\n");
      out.write("        </div>\r\n");
      out.write("        <form action=\"/feedbackSubmit\" method=\"post\" onsubmit=\"return validateForm()\">\r\n");
      out.write("            \r\n");
      out.write("            <!-- Name -->\r\n");
      out.write("            <div class=\"form-group\">\r\n");
      out.write("                <label for=\"name\">Name <span class=\"required-field\">*</span></label>\r\n");
      out.write("                <input type=\"text\" id=\"name\" name=\"name\" required>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <!-- Email -->\r\n");
      out.write("            <div class=\"form-group\">\r\n");
      out.write("                <label for=\"email\">Email <span class=\"required-field\">*</span></label>\r\n");
      out.write("                <input type=\"email\" id=\"email\" name=\"email\" required>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <!-- Rating -->\r\n");
      out.write("            <div class=\"form-group\">\r\n");
      out.write("                <label for=\"rating\">Rating (1-5) <span class=\"required-field\">*</span></label>\r\n");
      out.write("                <select id=\"rating\" name=\"rating\" required>\r\n");
      out.write("                    <option value=\"\" disabled selected>Select rating</option>\r\n");
      out.write("                    <option value=\"1\">1 - Poor</option>\r\n");
      out.write("                    <option value=\"2\">2 - Fair</option>\r\n");
      out.write("                    <option value=\"3\">3 - Good</option>\r\n");
      out.write("                    <option value=\"4\">4 - Very Good</option>\r\n");
      out.write("                    <option value=\"5\">5 - Excellent</option>\r\n");
      out.write("                </select>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <!-- Feedback Comments -->\r\n");
      out.write("            <div class=\"form-group\">\r\n");
      out.write("                <label for=\"comments\">Comments <span class=\"required-field\">*</span></label>\r\n");
      out.write("                <textarea id=\"comments\" name=\"comments\" required></textarea>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <!-- Submit Button -->\r\n");
      out.write("            <button type=\"submit\" id=\"submit-btn\" class=\"submit-btn\">Submit Feedback</button>\r\n");
      out.write("        </form>\r\n");
      out.write("\r\n");
      out.write("        <!-- Submitting message -->\r\n");
      out.write("        <div id=\"submit-message\" class=\"submit-message\">Submitting your feedback, please wait...</div>\r\n");
      out.write("\r\n");
      out.write("        <!-- Add a section to link to customer feedback view -->\r\n");
      out.write("        <div class=\"view-feedback\">\r\n");
      out.write("            <p>Want to check the status of your feedback? <a href=\"/lookupFeedback\">View Feedback Status</a></p>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
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
