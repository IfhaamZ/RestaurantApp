package unit;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Timestamp;

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

    // Feedback Management Tests
    @Test
    public void testValidFeedbackSubmission()
            throws ServletException, IOException, SQLException, NoSuchFieldException, IllegalAccessException {
        // Mock servlet path to simulate the action "/feedbackSubmit"
        when(request.getServletPath()).thenReturn("/feedbackSubmit");

        // Mock valid parameters
        when(request.getParameter("name")).thenReturn("John Doe");
        when(request.getParameter("email")).thenReturn("john@example.com");
        when(request.getParameter("rating")).thenReturn("5");
        when(request.getParameter("comments")).thenReturn("Excellent service");
        when(request.getRequestDispatcher("/feedbackConfirmation.jsp")).thenReturn(rd);

        // Mock the DBManager and inject it
        DBManager dbManager = mock(DBManager.class);
        when(dbManager.createFeedbackAndReturnID(anyString(), anyString(), anyString(), anyInt())).thenReturn(1);

        // Inject the mocked DBManager using reflection
        Field dbManagerField = FeedbackSubmissionServlet.class.getDeclaredField("dbManager");
        dbManagerField.setAccessible(true); // Make the private field accessible
        dbManagerField.set(feedbackServlet, dbManager); // Set the mocked dbManager

        // Call the Feedback servlet
        feedbackServlet.doPost(request, response);

        // Verify that the data was processed and the request was forwarded
        verify(request).getRequestDispatcher("/feedbackConfirmation.jsp");
        verify(rd).forward(request, response);
    }

    @Test
    public void testMissingFeedbackFields() throws ServletException, IOException {
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

    @Test
    public void testIncorrectDataTypeInFeedback() throws ServletException, IOException {
        // Mock servlet path to simulate the action "/feedbackSubmit"
        when(request.getServletPath()).thenReturn("/feedbackSubmit");
        when(request.getParameter("rating")).thenReturn("five"); // Invalid rating
        when(request.getRequestDispatcher("/feedback.jsp")).thenReturn(rd);

        // Call the Feedback servlet
        feedbackServlet.doPost(request, response);

        // Verify that validation error is triggered
        verify(request).setAttribute(eq("errorMessage"), anyString());
        verify(request).getRequestDispatcher("/feedback.jsp");
        verify(rd).forward(request, response);
    }

    // Error Management Tests
    @Test
    public void testValidErrorSubmission()
            throws ServletException, IOException, SQLException, NoSuchFieldException, IllegalAccessException {
        // Mock servlet path to simulate the action "/submit"
        when(request.getServletPath()).thenReturn("/submit");

        // Mock valid parameters
        when(request.getParameter("description")).thenReturn("Error description");
        when(request.getParameter("steps")).thenReturn("Steps to reproduce");
        when(request.getParameter("category")).thenReturn("UI Bug");
        when(request.getParameter("severity")).thenReturn("High");

        // Mock the DBManager and inject it
        DBManager dbManager = mock(DBManager.class);
        when(dbManager.createErrorReportAndReturnID(anyString(), anyString(), anyString(), anyString(),
                any(Timestamp.class))).thenReturn(1);

        // Inject the mocked DBManager using reflection
        Field dbManagerField = ErrorSubmissionServlet.class.getDeclaredField("dbManager");
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

        // Call the Error servlet
        errorServlet.doGet(request, response);

        // Verify that the correct JSP is displayed
        verify(request).getRequestDispatcher("/viewError.jsp");
        verify(rd).forward(request, response);
    }
}
