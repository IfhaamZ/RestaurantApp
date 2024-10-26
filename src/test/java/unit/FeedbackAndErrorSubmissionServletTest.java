package unit;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DAO.DBManager;
import controller.ErrorSubmissionServlet;
import controller.FeedbackSubmissionServlet;
import model.feedback;

public class FeedbackAndErrorSubmissionServletTest {
private FeedbackSubmissionServlet feedbackServlet;
private ErrorSubmissionServlet errorServlet;
private HttpServletRequest request;
private HttpServletResponse response;
private HttpSession session;
private RequestDispatcher rd;

@BeforeEach
public void setUp() throws Exception {
feedbackServlet = new FeedbackSubmissionServlet();
errorServlet = new ErrorSubmissionServlet();
request = mock(HttpServletRequest.class);
response = mock(HttpServletResponse.class);
session = mock(HttpSession.class);
rd = mock(RequestDispatcher.class);

// Mock session and request setup
when(request.getSession()).thenReturn(session);
when(request.getRequestDispatcher(anyString())).thenReturn(rd);
}

<<<<<<< HEAD
    // Feedback Management Tests
    @Test
    public void testValidFeedbackSubmission()
            throws ServletException, IOException, SQLException, NoSuchFieldException, IllegalAccessException {
        // Mock servlet path to simulate the action "/feedbackSubmit"
        when(request.getServletPath()).thenReturn("/feedbackSubmit");
=======
// Feedback Management Tests
@Test
public void testValidFeedbackSubmission()
throws ServletException, IOException, SQLException, NoSuchFieldException,
IllegalAccessException {
// Mock servlet path to simulate the action "/feedbackSubmit"
when(request.getServletPath()).thenReturn("/feedbackSubmit");
>>>>>>> parent of ee544a2 (debugging)

// Mock valid parameters
when(request.getParameter("name")).thenReturn("John Doe");
when(request.getParameter("email")).thenReturn("john@example.com");
when(request.getParameter("rating")).thenReturn("5");
when(request.getParameter("comments")).thenReturn("Excellent service");
when(request.getRequestDispatcher("/feedbackConfirmation.jsp")).thenReturn(rd);

<<<<<<< HEAD
        // Mock the DBManager and inject it
        DBManager dbManager = mock(DBManager.class);
        when(dbManager.createFeedbackAndReturnID(anyString(), anyString(), anyString(), anyInt())).thenReturn(1);
=======
// Mock the DBManager and inject it
DBManager dbManager = mock(DBManager.class);
when(dbManager.createFeedbackAndReturnID(anyString(), anyString(),
anyString(), anyInt())).thenReturn(1);
>>>>>>> parent of ee544a2 (debugging)

// Inject the mocked DBManager using reflection
Field dbManagerField =
FeedbackSubmissionServlet.class.getDeclaredField("dbManager");
dbManagerField.setAccessible(true); // Make the private field accessible
dbManagerField.set(feedbackServlet, dbManager); // Set the mocked dbManager

// Call the Feedback servlet
feedbackServlet.doPost(request, response);

// Verify that the data was processed and the request was forwarded
verify(request).getRequestDispatcher("/feedbackConfirmation.jsp");
verify(rd).forward(request, response);
}

@Test
public void testMissingFeedbackFields() throws ServletException, IOException
{
// Mock servlet path to simulate the action "/feedbackSubmit"
when(request.getServletPath()).thenReturn("/feedbackSubmit");
when(request.getParameter("rating")).thenReturn("");
when(request.getParameter("comments")).thenReturn("Good service");
when(request.getRequestDispatcher("/feedback.jsp")).thenReturn(rd);

// Call the Feedback servlet
feedbackServlet.doPost(request, response);

// Verify that it stays on the same page due to validation errors
verify(request).setAttribute(eq("errorMessage"), anyString());
verify(request).getRequestDispatcher("/feedback.jsp");
verify(rd).forward(request, response);
}

<<<<<<< HEAD
    @Test
    public void testIncorrectDataTypeInFeedback() throws ServletException, IOException {
        // Mock servlet path to simulate the action "/feedbackSubmit"
        when(request.getServletPath()).thenReturn("/feedbackSubmit");
        when(request.getParameter("rating")).thenReturn("five"); // Invalid rating
        when(request.getRequestDispatcher("/feedback.jsp")).thenReturn(rd);
=======
@Test
public void testIncorrectDataTypeInFeedback() throws ServletException,
IOException {
// Mock servlet path to simulate the action "/feedbackSubmit"
when(request.getServletPath()).thenReturn("/feedbackSubmit");
when(request.getParameter("rating")).thenReturn("five"); // Invalid rating
when(request.getRequestDispatcher("/feedback.jsp")).thenReturn(rd);
>>>>>>> parent of ee544a2 (debugging)

// Call the Feedback servlet
feedbackServlet.doPost(request, response);

// Verify that validation error is triggered
verify(request).setAttribute(eq("errorMessage"), anyString());
verify(request).getRequestDispatcher("/feedback.jsp");
verify(rd).forward(request, response);
}

<<<<<<< HEAD
    @Test
    public void testRespondToCustomerFeedback()
            throws ServletException, IOException, SQLException, NoSuchFieldException, IllegalAccessException {
        // Mock servlet path to simulate the action "/submitStaffFeedbackResponse"
        when(request.getServletPath()).thenReturn("/submitStaffFeedbackResponse");

        // Mock parameters and request dispatcher
        when(request.getParameter("staffResponse_1")).thenReturn("Staff feedback response");
        when(request.getRequestDispatcher(anyString())).thenReturn(rd);
        when(request.getContextPath()).thenReturn("/restaurantApp"); // Mocking the context path

        // Mock the DBManager and inject it
        DBManager dbManager = mock(DBManager.class);
        when(dbManager.getAllFeedback()).thenReturn(
                Collections.singletonList(new feedback(1, "John", "john@example.com", "Good service", 5, null, null)));

        // Inject the mocked DBManager using reflection
        Field dbManagerField = FeedbackSubmissionServlet.class.getDeclaredField("dbManager");
        dbManagerField.setAccessible(true); // Make the private field accessible
        dbManagerField.set(feedbackServlet, dbManager); // Set the mocked dbManager

        // Call the Feedback servlet method for submitting staff feedback
        feedbackServlet.submitStaffFeedbackResponse(request, response);

        // Verify the redirect is called with the correct path
        verify(response).sendRedirect("/restaurantApp/viewFeedback");
    }

    @Test
    public void testMissingErrorFields() throws ServletException, IOException {
        // Mock servlet path to simulate the action "/submit"
        when(request.getServletPath()).thenReturn("/submit");
        when(request.getParameter("description")).thenReturn(""); // Missing description
        when(request.getParameter("steps")).thenReturn("Steps to reproduce");
        when(request.getParameter("category")).thenReturn("UI Bug");
        when(request.getParameter("severity")).thenReturn("High");

        // Call the Error servlet
        errorServlet.doPost(request, response);

        // Verify that validation failed and the request was forwarded back to the form
        verify(request).setAttribute(eq("errorMessage"), anyString());
        verify(request).getRequestDispatcher("/errorSubmission.jsp");
        verify(rd).forward(request, response);
    }

    @Test
    public void testViewError() throws ServletException, IOException {
        // Mock servlet path to simulate the action "/viewError"
        when(request.getServletPath()).thenReturn("/viewError");
=======
@Test
public void testRespondToCustomerFeedback()
throws ServletException, IOException, SQLException, NoSuchFieldException,
IllegalAccessException {
// Mock servlet path to simulate the action "/submitStaffFeedbackResponse"
when(request.getServletPath()).thenReturn("/submitStaffFeedbackResponse");

// Mock parameters and request dispatcher
when(request.getParameter("staffResponse_1")).thenReturn("Staff feedback
response");
when(request.getRequestDispatcher(anyString())).thenReturn(rd);
when(request.getContextPath()).thenReturn("/restaurantApp"); // Mocking the
context path

// Mock the DBManager and inject it
DBManager dbManager = mock(DBManager.class);
when(dbManager.getAllFeedback()).thenReturn(
Collections.singletonList(new feedback(1, "John", "john@example.com", "Good
service", 5, null, null)));

// Inject the mocked DBManager using reflection
Field dbManagerField =
FeedbackSubmissionServlet.class.getDeclaredField("dbManager");
dbManagerField.setAccessible(true); // Make the private field accessible
dbManagerField.set(feedbackServlet, dbManager); // Set the mocked dbManager

// Call the Feedback servlet method for submitting staff feedback
feedbackServlet.submitStaffFeedbackResponse(request, response);

// Verify the redirect is called with the correct path
verify(response).sendRedirect("/restaurantApp/viewFeedback");
}

// Error Management Tests
@Test
public void testValidErrorSubmission()
throws ServletException, IOException, SQLException, NoSuchFieldException,
IllegalAccessException {
// Mock servlet path to simulate the action "/submit"
when(request.getServletPath()).thenReturn("/submit");

// Mock valid parameters
when(request.getParameter("description")).thenReturn("Error description");
when(request.getParameter("steps")).thenReturn("Steps to reproduce");
when(request.getParameter("category")).thenReturn("UI Bug");
when(request.getParameter("severity")).thenReturn("High");

// Mock the DBManager and inject it
DBManager dbManager = mock(DBManager.class);
when(dbManager.createErrorReportAndReturnID(anyString(), anyString(),
anyString(), anyString(),
any(Timestamp.class))).thenReturn(1);

// Inject the mocked DBManager using reflection
Field dbManagerField =
ErrorSubmissionServlet.class.getDeclaredField("dbManager");
dbManagerField.setAccessible(true); // Make the private field accessible
dbManagerField.set(errorServlet, dbManager); // Set the mocked dbManager

// Call the Error servlet
errorServlet.doPost(request, response);

// Verify that the data was processed and the request was forwarded
verify(request).getRequestDispatcher("/confirmation.jsp");
verify(rd).forward(request, response);
}

@Test
public void testMissingErrorFields() throws ServletException, IOException {
// Mock servlet path to simulate the action "/submit"
when(request.getServletPath()).thenReturn("/submit");
when(request.getParameter("description")).thenReturn(""); // Missing
description
when(request.getParameter("steps")).thenReturn("Steps to reproduce");
when(request.getParameter("category")).thenReturn("UI Bug");
when(request.getParameter("severity")).thenReturn("High");

// Call the Error servlet
errorServlet.doPost(request, response);

// Verify that validation failed and the request was forwarded back to the
form
verify(request).setAttribute(eq("errorMessage"), anyString());
verify(request).getRequestDispatcher("/errorSubmission.jsp");
verify(rd).forward(request, response);
}

@Test
public void testViewError() throws ServletException, IOException {
// Mock servlet path to simulate the action "/viewError"
when(request.getServletPath()).thenReturn("/viewError");
>>>>>>> parent of ee544a2 (debugging)

// Call the Error servlet
errorServlet.doGet(request, response);

// Verify that the correct JSP is displayed
verify(request).getRequestDispatcher("/viewError.jsp");
verify(rd).forward(request, response);
}

    @Test
    public void testUpdateErrorReport()
            throws ServletException, IOException, SQLException, NoSuchFieldException, IllegalAccessException {
        // Mock servlet path to simulate the action "/update"
        when(request.getServletPath()).thenReturn("/update");

<<<<<<< HEAD
        // Mock valid parameters for updating the error report
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("description")).thenReturn("Updated error description");
        when(request.getParameter("steps")).thenReturn("Updated steps to reproduce");
        when(request.getParameter("category")).thenReturn("UI Bug");
        when(request.getParameter("severity")).thenReturn("Critical");
        when(request.getParameter("staffComments")).thenReturn("This issue has been fixed");
=======
// Mock valid parameters for updating the error report
when(request.getParameter("id")).thenReturn("1");
when(request.getParameter("description")).thenReturn("Updated error
description");
when(request.getParameter("steps")).thenReturn("Updated steps to reproduce");
when(request.getParameter("category")).thenReturn("UI Bug");
when(request.getParameter("severity")).thenReturn("Critical");
when(request.getParameter("staffComments")).thenReturn("This issue has been
fixed");
>>>>>>> parent of ee544a2 (debugging)

        // Mock the DBManager and inject it
        DBManager dbManager = mock(DBManager.class);
        when(dbManager.updateErrorReport(any())).thenReturn(true); // Mock success

        // Inject the mocked DBManager using reflection
        Field dbManagerField = ErrorSubmissionServlet.class.getDeclaredField("dbManager");
        dbManagerField.setAccessible(true); // Make the private field accessible
        dbManagerField.set(errorServlet, dbManager); // Set the mocked dbManager

        // Call the Error servlet
        errorServlet.doPost(request, response);

        // Verify that the request was processed and the user was redirected to the
        // dashboard
        verify(response).sendRedirect("/staffDashboard.jsp");
    }

    // Additional Test 1: Test SQL Exception during feedback submission
    @Test
    public void testFeedbackSubmissionWithSQLException() throws Exception {
        // Mock servlet path to simulate the action "/feedbackSubmit"
        when(request.getServletPath()).thenReturn("/feedbackSubmit");

        // Mock valid parameters
        when(request.getParameter("name")).thenReturn("John Doe");
        when(request.getParameter("email")).thenReturn("john@example.com");
        when(request.getParameter("rating")).thenReturn("5");
        when(request.getParameter("comments")).thenReturn("Excellent service");

        // Mock DBManager to throw SQLException
        DBManager dbManager = mock(DBManager.class);
        when(dbManager.createFeedbackAndReturnID(anyString(), anyString(), anyString(), anyInt()))
                .thenThrow(new SQLException("Database error"));

        // Inject the mocked DBManager using reflection
        Field dbManagerField = FeedbackSubmissionServlet.class.getDeclaredField("dbManager");
        dbManagerField.setAccessible(true);
        dbManagerField.set(feedbackServlet, dbManager);

        // Call the Feedback servlet
        feedbackServlet.doPost(request, response);

        // Verify that an error message is set and the request is forwarded to the form
        verify(request).setAttribute(eq("errorMessage"), eq("Failed to submit your feedback. Please try again."));
        verify(request).getRequestDispatcher("/feedback.jsp");
        verify(rd).forward(request, response);
    }

    @Test
    public void testDeleteErrorReport()
            throws ServletException, IOException, SQLException, NoSuchFieldException, IllegalAccessException {
        // Mock servlet path to simulate the action "/deleteError"
        when(request.getServletPath()).thenReturn("/deleteError");

        // Mock the ID of the error report to delete
        when(request.getParameter("errorId")).thenReturn("1");

        // Mock the DBManager and inject it
        DBManager dbManager = mock(DBManager.class);
        when(dbManager.deleteErrorById(anyInt())).thenReturn(true); // Mock successful deletion

        // Inject the mocked DBManager using reflection
        Field dbManagerField = ErrorSubmissionServlet.class.getDeclaredField("dbManager");
        dbManagerField.setAccessible(true); // Make the private field accessible
        dbManagerField.set(errorServlet, dbManager); // Set the mocked dbManager

        // Call the Error servlet to delete the error report
        errorServlet.doPost(request, response);

        // Verify that the request was processed and the user was redirected to a
        // confirmation page
        verify(request).setAttribute(eq("successMessage"), eq("Error report deleted successfully."));
        verify(request).getRequestDispatcher("/errorDeleted.jsp");
        verify(rd).forward(request, response);
    }

    @Test
    public void testFailedDeleteErrorReport()
            throws ServletException, IOException, SQLException, NoSuchFieldException, IllegalAccessException {
        // Mock servlet path to simulate the action "/deleteError"
        when(request.getServletPath()).thenReturn("/deleteError");

        // Mock the ID of the error report to delete
        when(request.getParameter("errorId")).thenReturn("1");

        // Mock the DBManager and inject it
        DBManager dbManager = mock(DBManager.class);
        when(dbManager.deleteErrorById(anyInt())).thenReturn(false); // Mock failed deletion

        // Inject the mocked DBManager using reflection
        Field dbManagerField = ErrorSubmissionServlet.class.getDeclaredField("dbManager");
        dbManagerField.setAccessible(true); // Make the private field accessible
        dbManagerField.set(errorServlet, dbManager); // Set the mocked dbManager

        // Call the Error servlet to attempt deleting the error report
        errorServlet.doPost(request, response);

        // Verify that the failure message is set and the request is forwarded to the
        // error page
        verify(request).setAttribute(eq("errorMessage"), eq("Error report deletion failed."));
        verify(request).getRequestDispatcher("/viewError.jsp");
        verify(rd).forward(request, response);
    }

    @Test
    public void testFeedbackSubmissionWithMissingName() throws ServletException, IOException {
        // Simulate the servlet path for feedback submission
        when(request.getServletPath()).thenReturn("/feedbackSubmit");

        // Provide empty name but valid other fields
        when(request.getParameter("name")).thenReturn("");
        when(request.getParameter("email")).thenReturn("john@example.com");
        when(request.getParameter("rating")).thenReturn("4");
        when(request.getParameter("comments")).thenReturn("Good service");
        when(request.getRequestDispatcher("/feedback.jsp")).thenReturn(rd);

        // Call the Feedback servlet
        feedbackServlet.doPost(request, response);

        // Verify that validation failed and the error message was set
        verify(request).setAttribute(eq("errorMessage"), eq("All fields are required and email must be valid."));
        verify(request).getRequestDispatcher("/feedback.jsp");
        verify(rd).forward(request, response);
    }

    @Test
    public void testViewCustomerFeedback()
            throws ServletException, IOException, SQLException, NoSuchFieldException, IllegalAccessException {
        // Mock servlet path to simulate the action "/viewCustomerFeedback"
        when(request.getServletPath()).thenReturn("/viewCustomerFeedback");
        when(request.getParameter("id")).thenReturn("1"); // Mock feedback ID

        // Mock DBManager and feedback
        DBManager dbManager = mock(DBManager.class);
        when(dbManager.getFeedbackById(1))
                .thenReturn(new feedback(1, "John", "john@example.com", "Good service", 5, null, null));

        // Inject the mocked DBManager using reflection
        Field dbManagerField = FeedbackSubmissionServlet.class.getDeclaredField("dbManager");
        dbManagerField.setAccessible(true);
        dbManagerField.set(feedbackServlet, dbManager);

        // Call the Feedback servlet
        feedbackServlet.doGet(request, response);

        // Verify the feedback view page is shown
        verify(request).getRequestDispatcher("/viewCustomerFeedback.jsp");
        verify(rd).forward(request, response);
    }

    @Test
    public void testErrorReportDeletionSuccess()
            throws ServletException, IOException, SQLException, NoSuchFieldException, IllegalAccessException {
        // Simulate the servlet path for deleting an error
        when(request.getServletPath()).thenReturn("/deleteError");
        when(request.getParameter("errorId")).thenReturn("1");

        // Mock the DBManager and deletion success
        DBManager dbManager = mock(DBManager.class);
        when(dbManager.deleteErrorById(1)).thenReturn(true);

        // Inject the mocked DBManager using reflection
        Field dbManagerField = ErrorSubmissionServlet.class.getDeclaredField("dbManager");
        dbManagerField.setAccessible(true);
        dbManagerField.set(errorServlet, dbManager);

        // Call the Error servlet to delete the error
        errorServlet.doPost(request, response);

        // Verify success message and forward to the confirmation page
        verify(request).setAttribute(eq("successMessage"), eq("Error report deleted successfully."));
        verify(request).getRequestDispatcher("/errorDeleted.jsp");
        verify(rd).forward(request, response);
    }

}
<<<<<<< HEAD
=======

// Test SQL Exception during feedback submission
@Test
public void testFeedbackSubmissionWithSQLException() throws Exception {
// Mock servlet path to simulate the action "/feedbackSubmit"
when(request.getServletPath()).thenReturn("/feedbackSubmit");

// Mock valid parameters
when(request.getParameter("name")).thenReturn("John Doe");
when(request.getParameter("email")).thenReturn("john@example.com");
when(request.getParameter("rating")).thenReturn("5");
when(request.getParameter("comments")).thenReturn("Excellent service");

// Mock DBManager to throw SQLException
DBManager dbManager = mock(DBManager.class);
when(dbManager.createFeedbackAndReturnID(anyString(), anyString(),
anyString(), anyInt()))
.thenThrow(new SQLException("Database error"));

// Inject the mocked DBManager using reflection
Field dbManagerField =
FeedbackSubmissionServlet.class.getDeclaredField("dbManager");
dbManagerField.setAccessible(true);
dbManagerField.set(feedbackServlet, dbManager);

// Call the Feedback servlet
feedbackServlet.doPost(request, response);

// Verify that an error message is set and the request is forwarded to the
form
verify(request).setAttribute(eq("errorMessage"), eq("Failed to submit your
feedback. Please try again."));
verify(request).getRequestDispatcher("/feedback.jsp");
verify(rd).forward(request, response);
}

// Test SQL Exception during error submission
@Test
public void testErrorSubmissionWithSQLException() throws Exception {
// Mock servlet path to simulate the action "/submit"
when(request.getServletPath()).thenReturn("/submit");

// Mock valid parameters
when(request.getParameter("description")).thenReturn("Error description");
when(request.getParameter("steps")).thenReturn("Steps to reproduce");
when(request.getParameter("category")).thenReturn("UI Bug");
when(request.getParameter("severity")).thenReturn("High");

// Mock DBManager to throw SQLException
DBManager dbManager = mock(DBManager.class);
when(dbManager.createErrorReportAndReturnID(anyString(), anyString(),
anyString(), anyString(),
any(Timestamp.class)))
.thenThrow(new SQLException("Database error"));

// Inject the mocked DBManager using reflection
Field dbManagerField =
ErrorSubmissionServlet.class.getDeclaredField("dbManager");
dbManagerField.setAccessible(true);
dbManagerField.set(errorServlet, dbManager);

// Call the Error servlet
errorServlet.doPost(request, response);

// Verify that an error message is set and the request is forwarded to the
form
verify(request).setAttribute(eq("errorMessage"), eq("Failed to submit your
error report. Please try again."));
verify(request).getRequestDispatcher("/errorSubmission.jsp");
verify(rd).forward(request, response);
}

// This test checks that when an invalid email is submitted, the system
// correctly sets an error message and redisplays the feedback form.
@Test
public void testInvalidEmailInFeedbackSubmission() throws ServletException,
IOException {
// Mock servlet path to simulate the action "/feedbackSubmit"
when(request.getServletPath()).thenReturn("/feedbackSubmit");

// Mock parameters with an invalid email
when(request.getParameter("name")).thenReturn("John Doe");
when(request.getParameter("email")).thenReturn("invalid-email");
when(request.getParameter("rating")).thenReturn("4");
when(request.getParameter("comments")).thenReturn("Great service");

// Forward back to the form in case of validation failure
when(request.getRequestDispatcher("/feedback.jsp")).thenReturn(rd);

// Call the Feedback servlet
feedbackServlet.doPost(request, response);

// Verify that an error message is set and the request is forwarded to the
form
verify(request).setAttribute(eq("errorMessage"), eq("All fields are required
and email must be valid."));
verify(request).getRequestDispatcher("/feedback.jsp");
verify(rd).forward(request, response);
}

// This test ensures that a valid error report submission is processed
// correctly, with the system forwarding the user to the confirmation page
and
// setting a success message.
@Test
public void testSuccessfulErrorSubmission()
throws ServletException, IOException, NoSuchFieldException,
IllegalAccessException, SQLException {
// Mock servlet path to simulate the action "/submit"
when(request.getServletPath()).thenReturn("/submit");

// Mock valid parameters
when(request.getParameter("description")).thenReturn("Valid error
description");
when(request.getParameter("steps")).thenReturn("Valid steps to reproduce");
when(request.getParameter("category")).thenReturn("UI Bug"); // Valid
category
when(request.getParameter("severity")).thenReturn("High");

// Mock the DBManager and inject it
DBManager dbManager = mock(DBManager.class);
when(dbManager.createErrorReportAndReturnID(anyString(), anyString(),
anyString(), anyString(),
any(Timestamp.class)))
.thenReturn(1); // Mock successful DB operation

// Inject the mocked DBManager using reflection
Field dbManagerField =
ErrorSubmissionServlet.class.getDeclaredField("dbManager");
dbManagerField.setAccessible(true);
dbManagerField.set(errorServlet, dbManager);

// Forward to confirmation page
when(request.getRequestDispatcher("/confirmation.jsp")).thenReturn(rd);

// Call the Error servlet
errorServlet.doPost(request, response);

// Verify that success message is set and request is forwarded to
confirmation
verify(request).setAttribute(eq("successMessage"), eq("Your error report was
successfully submitted."));
verify(request).getRequestDispatcher("/confirmation.jsp");
verify(rd).forward(request, response);
}

// This test checks that if a required field (e.g., description) is missing
// during error submission,
// the system sets an error message and redisplays the form for correction.
@Test
public void testMissingFieldsInErrorSubmission() throws ServletException,
IOException {
// Mock servlet path to simulate the action "/submit"
when(request.getServletPath()).thenReturn("/submit");

// Mock missing description field
when(request.getParameter("description")).thenReturn(""); // Missing
description
when(request.getParameter("steps")).thenReturn("Steps to reproduce");
when(request.getParameter("category")).thenReturn("UI Bug");
when(request.getParameter("severity")).thenReturn("High");

// Forward back to form in case of validation failure
when(request.getRequestDispatcher("/errorSubmission.jsp")).thenReturn(rd);

// Call the Error servlet
errorServlet.doPost(request, response);

// Verify that validation failed and the request was forwarded back to the
form
verify(request).setAttribute(eq("errorMessage"), eq("All fields are
required."));
verify(request).getRequestDispatcher("/errorSubmission.jsp");
verify(rd).forward(request, response);
}

// This test ensures that when valid data is provided, the error report is
// successfully updated in the system
@Test
public void testSuccessfulErrorUpdate()
throws ServletException, IOException, NoSuchFieldException,
IllegalAccessException, SQLException {
// Mock servlet path to simulate the action "/update"
when(request.getServletPath()).thenReturn("/update");

// Mock valid parameters
when(request.getParameter("id")).thenReturn("1");
when(request.getParameter("description")).thenReturn("Updated error
description");
when(request.getParameter("steps")).thenReturn("Updated steps to reproduce");
when(request.getParameter("category")).thenReturn("UI Bug");
when(request.getParameter("severity")).thenReturn("Critical");
when(request.getParameter("staffComments")).thenReturn("This issue has been
fixed");

// Mock the DBManager and inject it
DBManager dbManager = mock(DBManager.class);
when(dbManager.updateErrorReport(any())).thenReturn(true); // Mock success

// Inject the mocked DBManager using reflection
Field dbManagerField =
ErrorSubmissionServlet.class.getDeclaredField("dbManager");
dbManagerField.setAccessible(true);
dbManagerField.set(errorServlet, dbManager);

// Call the Error servlet
errorServlet.doPost(request, response);

// Verify that the request was processed and the user was redirected to the
// dashboard
verify(response).sendRedirect("/staffDashboard.jsp");
}

}
>>>>>>> parent of ee544a2 (debugging)
