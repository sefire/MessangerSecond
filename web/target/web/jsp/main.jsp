<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html><head><title>Welcome</title></head>
<body>
<h3>Welcome</h3>
<hr/>
<h1>${user}, hello!</h1>
<hr/>

<form name="InfoForm" method="POST" action="controller">
    <input type="hidden" name="command" value="getuserinfo" />
    <%--<a href="controller?command=registrationform"></a>--%>
    <input type="submit" value="Get My Information"/>
    <br/>
    <br/><hr/>
</form>

<a href="controller?command=logout">Logout</a>
</body></html>
