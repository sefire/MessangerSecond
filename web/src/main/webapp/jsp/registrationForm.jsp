<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 10/26/2016
  Time: 00:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Registration</title></head>
<body>
<form name="signupForm" method="POST" action="controller">
    <input type="hidden" name="command" value="passregistration" />
    Phone: <br/>
    <input type="text" name="phone" value=""/>
    <br/>
    FirstName: <br/>
    <input type="text" name="firstname" value=""/>
    <br/>
    LastName: <br/>
    <input type="text" name="laststname" value=""/>
    <br/>
    Country:<br/>
    <input type="text" name="country" value=""/>
    <br/>
    City:<br/>
    <input type="text" name="city" value=""/>
    <br/>
    Age:<br/>
    <input type="text" name="age" value=""/>
    <br/>
    Password:<br/>
    <input type="password" name="password" value=""/>
    <br/>
    <br/>
    <input type="submit" value="Let's start!"/>
</form><hr/>

</body>
</html>
