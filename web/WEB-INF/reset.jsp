<%-- 
    Document   : reset
    Created on : 18-Nov-2021, 08:54:14
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
        <h1>Forgotten Password</h1>
        
        <p>
            Please enter the e-mail address associated with your account then click the "Forgot Password" button.
        </p>
        <form method="post" action="">
            <label for="resetEmail">E-mail Address:</label>
            <input type="email" id="resetEmail" name="resetEmail"/>
            <input type="submit" id="resetSubmit" name="resetSubmit" value="Forgot Password"/>
        </form>
    </body>
</html>
