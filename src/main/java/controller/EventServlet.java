// package controller;

// import model.Event;
// import DAO.DBManager;

// import javax.naming.NamingException;
// import javax.servlet.RequestDispatcher;
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import java.io.IOException;
// import java.sql.SQLException;
// import java.time.LocalDate;
// import java.util.List;

// public class EventServlet extends HttpServlet {
// private DBManager dbManager;

// // Initialize servlet
// public void init() throws ServletException {
// try {
// dbManager = new DBManager(); // Assuming DBManager is correctly set up for
// events
// } catch (Exception e) {
// throw new ServletException(e);
// }
// }

// // Handle POST requests by forwarding to doGet
// protected void doPost(HttpServletRequest request, HttpServletResponse
// response)
// throws ServletException, IOException {
// doGet(request, response);
// }

// // Handle GET requests
// protected void doGet(HttpServletRequest request, HttpServletResponse
// response)
// throws ServletException, IOException {
// String action = request.getServletPath();

// try {
// switch (action) {
// case "/eventnew":
// showNewForm(request, response);
// break;
// case "/eventinsert":
// insertEvent(request, response);
// break;
// case "/eventdelete":
// deleteEvent(request, response);
// break;
// case "/eventedit":
// showEditForm(request, response);
// break;
// case "/eventupdate":
// updateEvent(request, response);
// break;
// case "/eventsearch":
// searchEvents(request, response); // Add search event handling
// break;
// case "/staffeventsearch":
// searchEventsForStaff(request, response); // Add search event handling for
// staff
// break;
// case "/staffeventlist":
// listEventsForStaff(request, response); // Staff view
// break;
// default:
// listEventsForCustomers(request, response); // Default is customer view
// break;
// }
// } catch (SQLException ex) {
// throw new ServletException(ex);
// } catch (NamingException e) {

// e.printStackTrace();
// }
// }

// // List events for customers
// private void listEventsForCustomers(HttpServletRequest request,
// HttpServletResponse response)
// throws SQLException, IOException, ServletException {
// List<Event> listEvents = dbManager.fetchAllEvents();
// request.setAttribute("events", listEvents);
// RequestDispatcher dispatcher =
// request.getRequestDispatcher("EventCalendar.jsp");
// dispatcher.forward(request, response);
// }

// // List events for staff
// private void listEventsForStaff(HttpServletRequest request,
// HttpServletResponse response)
// throws SQLException, IOException, ServletException {
// List<Event> listEvents = dbManager.fetchAllEvents();
// request.setAttribute("events", listEvents);
// RequestDispatcher dispatcher =
// request.getRequestDispatcher("EventCalendarStaff.jsp");
// dispatcher.forward(request, response);
// }

// // Search events by type and date
// private void searchEvents(HttpServletRequest request, HttpServletResponse
// response)
// throws SQLException, IOException, ServletException {
// String type = request.getParameter("type");
// String date = request.getParameter("date");

// List<Event> listEvents = dbManager.searchEvents(type, date);
// request.setAttribute("ListOfEvents", listEvents);
// RequestDispatcher dispatcher =
// request.getRequestDispatcher("SearchEvent.jsp");
// dispatcher.forward(request, response);
// }

// // Search events for staff
// private void searchEventsForStaff(HttpServletRequest request,
// HttpServletResponse response)
// throws SQLException, IOException, ServletException {
// String type = request.getParameter("type");
// String date = request.getParameter("date");

// List<Event> listEvents = dbManager.searchEvents(type, date);
// request.setAttribute("ListOfEvents", listEvents);
// RequestDispatcher dispatcher =
// request.getRequestDispatcher("SearchEventStaff.jsp");
// dispatcher.forward(request, response);
// }

// // Show form for creating a new event
// private void showNewForm(HttpServletRequest request, HttpServletResponse
// response)
// throws ServletException, IOException {
// RequestDispatcher dispatcher = request.getRequestDispatcher("EventForm.jsp");
// dispatcher.forward(request, response);
// }

// // Show form for editing an existing event
// private void showEditForm(HttpServletRequest request, HttpServletResponse
// response)
// throws SQLException, ServletException, IOException, NamingException {
// int id = Integer.parseInt(request.getParameter("eventId"));
// Event existingEvent = dbManager.getEventById(id);
// RequestDispatcher dispatcher = request.getRequestDispatcher("EventForm.jsp");
// request.setAttribute("event", existingEvent);
// dispatcher.forward(request, response);
// }

// // Insert a new event
// private void insertEvent(HttpServletRequest request, HttpServletResponse
// response)
// throws SQLException, IOException, NamingException {
// String name = request.getParameter("name");
// String description = request.getParameter("description");
// LocalDate date = LocalDate.parse(request.getParameter("date"));
// String type = request.getParameter("type");

// Event newEvent = new Event(0, name, description, date, type); // ID 0 for new
// events
// dbManager.createEvent(newEvent);
// response.sendRedirect("staffeventlist");
// }

// // Update an existing event
// private void updateEvent(HttpServletRequest request, HttpServletResponse
// response)
// throws SQLException, IOException, NamingException {
// int id = Integer.parseInt(request.getParameter("eventId"));
// String name = request.getParameter("name");
// String description = request.getParameter("description");
// LocalDate date = LocalDate.parse(request.getParameter("date"));
// String type = request.getParameter("type");

// Event updatedEvent = new Event(id, name, description, date, type);
// dbManager.updateEvent(updatedEvent);
// response.sendRedirect("staffeventlist");
// }

// // Delete an event
// private void deleteEvent(HttpServletRequest request, HttpServletResponse
// response)
// throws SQLException, IOException, NamingException {
// int id = Integer.parseInt(request.getParameter("eventId"));
// dbManager.deleteEvent(id);
// response.sendRedirect("staffeventlist");
// }
// }
