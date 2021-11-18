<%-- 
    Document   : resetNewPassword
    Created on : 18-Nov-2021, 09:21:58
    Author     : BritishWaldo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notes App</title>
    </head>
    <body>
        <h1>Password Reset</h1>

        <form method="post" action="">
            <label for="resetPassword">New Password: </label>
            <input type="text" id="resetPassword" name="resetPassword"/>
            <input type="submit" id="resetSubmit" name="resetSubmit" value="Reset Password"/>
            <input type="hidden" id="resetUUID" name="resetUUID" value="${resetUUID}"/>
        </form>
    </body>
</html> 