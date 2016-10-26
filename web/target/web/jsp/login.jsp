<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html><head><title>Login</title></head>
<body>
<h3>Messenger</h3>
<form name="loginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="login" />
    <br/>We apologize for this "design", we do our best to hire a designer...<br/><br/>
    Phone number:<br/>
    <input type="text" name="phoneNumber" value=""/>
    <br/>Password:<br/>
    <input type="password" name="password" value=""/>
    <br/>
    ${errorLoginPassMessage}
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <input type="submit" value="Log in"/>
</form>
<form name="loginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="registrationform" />
    <%--<a href="controller?command=registrationform"></a>--%>
    <input type="submit" value="Pass Registration"/>
    <br/>
    <br/><hr/>
</form>
</body></html>

