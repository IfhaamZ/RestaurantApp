/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.4.51.v20230217
 * Generated at: 2024-09-28 02:32:46 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class viewStockLevels_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<html>\n");
      out.write("  <head>\n");
      out.write("    <title>View Stock Levels</title>\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/ViewStockLevel.css\" />\n");
      out.write("  </head>\n");
      out.write("  <body>\n");
      out.write("    <div class=\"container\">\n");
      out.write("      <h2>Stock Levels</h2>\n");
      out.write("\n");
      out.write("      <!-- Role Selection Form -->\n");
      out.write("      <form action=\"inventory\" method=\"get\" class=\"role-form\">\n");
      out.write("        <label for=\"role\">Select Role:</label>\n");
      out.write("        <select name=\"role\" id=\"role\">\n");
      out.write("          <option value=\"Manager\">Manager</option>\n");
      out.write("          <option value=\"Staff\">Staff</option>\n");
      out.write("        </select>\n");
      out.write("      </form>\n");
      out.write("\n");
      out.write("      <!-- Confirmation message -->\n");
      out.write("      <c:if test=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty message}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("        <p style=\"color: green\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${message}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</p>\n");
      out.write("      </c:if>\n");
      out.write("\n");
      out.write("        <c:forEach var=\"entry\" items=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${stockLevels}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("          <tr>\n");
      out.write("            <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${entry.key}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</td>\n");
      out.write("            <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${entry.value}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</td>\n");
      out.write("          </tr>\n");
      out.write("        </c:forEach>\n");
      out.write("      </table>\n");
      out.write("\n");
      out.write("      <!-- Only Managers can update stock levels -->\n");
      out.write("      <c:if test=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${role == 'Manager'}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("        \n");
      out.write("        <h3>Update Stock Levels</h3>\n");
      out.write("        <form action=\"inventory\" method=\"post\" class=\"update-stock-form\">\n");
      out.write("          <input type=\"hidden\" name=\"action\" value=\"update\" />\n");
      out.write("          <label>Product ID: <input type=\"text\" name=\"productID\" /></label\n");
      out.write("          ><br />\n");
      out.write("          <label>New Stock: <input type=\"number\" name=\"newStock\" /></label\n");
      out.write("          ><br />\n");
      out.write("          <a href=\"inventory?action=viewStockDetail\" class=\"form-button\">View Stock Detail</a>\n");
      out.write("\n");
      out.write("          <input type=\"submit\" value=\"Update Stock\" class=\"form-button\" />\n");
      out.write("        </form>\n");
      out.write("      </c:if>\n");
      out.write("\n");
      out.write("      <!-- Home Button -->\n");
      out.write("      <a\n");
      out.write("        href=\"inventory?action=lowStockNotification&role=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${role}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\"\n");
      out.write("        class=\"form-button low-stock-button\"\n");
      out.write("        >Check Low Stock Notification</a\n");
      out.write("      >\n");
      out.write("      <a href=\"index.jsp\" class=\"form-button home-button\">Main Page</a>\n");
      out.write("    </div>\n");
      out.write("  </body>\n");
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
