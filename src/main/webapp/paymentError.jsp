<!-- paymentError.jsp -->
<html>
  <head>
    <title>Payment Error</title>
  </head>
  <body>
    <h1>Oops! Something went wrong with your payment.</h1>
    <p>${error}</p>
    <!-- This will display the error message set in the servlet -->
    <a href="paymentForm.jsp">Go back to payment page</a>
  </body>
</html>
