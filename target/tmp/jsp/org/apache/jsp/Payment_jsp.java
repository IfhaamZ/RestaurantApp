/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.4.51.v20230217
 * Generated at: 2024-10-05 04:10:22 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Payment_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("  <head>\r\n");
      out.write("    <meta charset=\"UTF-8\" />\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n");
      out.write("    <title>Manage Payment</title>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/Payment.css\" />\r\n");
      out.write("    <!-- Link to the CSS file -->\r\n");
      out.write("  </head>\r\n");
      out.write("  <body>\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("      <h2>Payment Form</h2>\r\n");
      out.write("\r\n");
      out.write("      <form action=\"paymentinsert\" method=\"post\">\r\n");
      out.write("        <div class=\"form-group\">\r\n");
      out.write("          <label for=\"method\">Payment Method:</label>\r\n");
      out.write("          <select id=\"method\" name=\"method\" required>\r\n");
      out.write("            <option value=\"card\">Credit Card</option>\r\n");
      out.write("            <option value=\"cash\">Cash</option>\r\n");
      out.write("          </select>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div id=\"card-details\" class=\"form-group\">\r\n");
      out.write("          <label for=\"cardNum\">Card Number:</label>\r\n");
      out.write("          <input\r\n");
      out.write("            type=\"text\"\r\n");
      out.write("            id=\"cardNum\"\r\n");
      out.write("            name=\"cardNum\"\r\n");
      out.write("            placeholder=\"Enter Card Number\"\r\n");
      out.write("            pattern=\"\\d{16}\"\r\n");
      out.write("            title=\"Please enter a valid 16-digit card number.\"\r\n");
      out.write("            autocomplete=\"off\"\r\n");
      out.write("          />\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div id=\"exp-details\" class=\"form-row\">\r\n");
      out.write("          <div class=\"form-group\">\r\n");
      out.write("            <label for=\"expMonth\">Expiry Month:</label>\r\n");
      out.write("            <input\r\n");
      out.write("              type=\"text\"\r\n");
      out.write("              id=\"expMonth\"\r\n");
      out.write("              name=\"expMonth\"\r\n");
      out.write("              placeholder=\"MM\"\r\n");
      out.write("              pattern=\"\\d{2}\"\r\n");
      out.write("              title=\"Please enter a valid 2-digit month.\"\r\n");
      out.write("            />\r\n");
      out.write("          </div>\r\n");
      out.write("          <div class=\"form-group\">\r\n");
      out.write("            <label for=\"expYear\">Expiry Year:</label>\r\n");
      out.write("            <input\r\n");
      out.write("              type=\"text\"\r\n");
      out.write("              id=\"expYear\"\r\n");
      out.write("              name=\"expYear\"\r\n");
      out.write("              placeholder=\"YY\"\r\n");
      out.write("              pattern=\"\\d{2}\"\r\n");
      out.write("              title=\"Please enter a valid 2-digit year.\"\r\n");
      out.write("            />\r\n");
      out.write("          </div>\r\n");
      out.write("          <div class=\"form-group\">\r\n");
      out.write("            <label for=\"cvn\">CVN:</label>\r\n");
      out.write("            <input\r\n");
      out.write("              type=\"text\"\r\n");
      out.write("              id=\"cvn\"\r\n");
      out.write("              name=\"cvn\"\r\n");
      out.write("              placeholder=\"CVN\"\r\n");
      out.write("              pattern=\"\\d{3}\"\r\n");
      out.write("              title=\"Please enter a valid 3-digit CVN.\"\r\n");
      out.write("            />\r\n");
      out.write("          </div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"form-group\">\r\n");
      out.write("          <label for=\"paymentAmount\">Payment Amount:</label>\r\n");
      out.write("          <input\r\n");
      out.write("            type=\"number\"\r\n");
      out.write("            id=\"paymentAmount\"\r\n");
      out.write("            name=\"paymentAmount\"\r\n");
      out.write("            placeholder=\"Enter Amount\"\r\n");
      out.write("            required\r\n");
      out.write("          />\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"form-group\">\r\n");
      out.write("          <label for=\"paymentDate\">Payment Date:</label>\r\n");
      out.write("          <input type=\"date\" id=\"paymentDate\" name=\"paymentDate\" value=\"");
      out.print( new
          java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date())
          );
      out.write("\" readonly>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"form-actions\">\r\n");
      out.write("          <button type=\"submit\" name=\"action\" value=\"submit\" class=\"submit-btn\">\r\n");
      out.write("            Submit Payment\r\n");
      out.write("          </button>\r\n");
      out.write("\r\n");
      out.write("          <button type=\"button\" class=\"cancel-btn\" onclick=\"cancelPayment()\">\r\n");
      out.write("            Cancel Payment\r\n");
      out.write("          </button>\r\n");
      out.write("        </div>\r\n");
      out.write("      </form>\r\n");
      out.write("\r\n");
      out.write("      ");
      out.write(' ');
 String message = (String)
      request.getAttribute("message"); if (message != null) { 
      out.write("\r\n");
      out.write("      <p class=\"message\">");
      out.print( message );
      out.write("</p>\r\n");
      out.write("      ");
 } 
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <script>\r\n");
      out.write("      // Toggle card details visibility based on the selected payment method\r\n");
      out.write("      document.getElementById(\"method\").addEventListener(\"change\", function () {\r\n");
      out.write("        const cardDetails = document.getElementById(\"card-details\");\r\n");
      out.write("        const expDetails = document.getElementById(\"exp-details\");\r\n");
      out.write("        if (this.value === \"cash\") {\r\n");
      out.write("          cardDetails.style.display = \"none\";\r\n");
      out.write("          expDetails.style.display = \"none\";\r\n");
      out.write("        } else {\r\n");
      out.write("          cardDetails.style.display = \"block\";\r\n");
      out.write("          expDetails.style.display = \"flex\";\r\n");
      out.write("        }\r\n");
      out.write("      });\r\n");
      out.write("\r\n");
      out.write("        function cancelPayment() {\r\n");
      out.write("          alert('You have canceled the payment.');\r\n");
      out.write("          window.location.href = 'index.jsp'; // Redirect to index.jsp\r\n");
      out.write("        }\r\n");
      out.write("    </script>\r\n");
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
