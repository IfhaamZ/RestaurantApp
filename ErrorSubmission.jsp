<!DOCTYPE html>
<html>
<head>
    <title>Error Submission</title>
</head>
<body>
    <h2>Submit an Error Report</h2>
    <form action="submitError" method="post">
        <label for="description">Error Description:</label>
        <textarea id="description" name="description" required></textarea><br><br>
        
        <label for="steps">Steps Taken Before Error:</label>
        <textarea id="steps" name="steps" required></textarea><br><br>
        
        <label for="severity">Severity:</label>
        <select id="severity" name="severity" required>
            <option value="Low">Low</option>
            <option value="Medium">Medium</option>
            <option value="High">High</option>
        </select><br><br>
        
        <input type="submit" value="Submit Error Report">
    </form>
</body>
</html>