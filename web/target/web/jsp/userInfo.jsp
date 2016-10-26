<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>userInfo</title>
    <style>
        ul.hr {
            margin: 0; /* Обнуляем значение отступов */
            padding: 4px; /* Значение полей */
        }
        ul.hr li {
            margin-right: 25px; /* Отступ слева */
            border: 1px solid #000; /* Рамка вокруг текста */
            padding: 3px; /* Поля вокруг текста */
        }
    </style>
</head>
<body>
<ul class="hr">
    <li>Phone: ${phone}</li>
    <li>FirstName: ${firstName}</li>
    <li>LastName: ${lastName}</li>
    <li>Country: ${country}</li>
    <li>City: ${city}</li>
    <li>Age: ${age}</li>
    <li>Password: ${password}</li>
</ul>
</body>
</html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table border="1">
    <caption>User Information</caption>
    <tr>
        <th>Param Name</th>
        <th>Value</th>
    </tr>
    <tr><td>Phone</td><td>${phone}</td>
    <tr><td>FirstName</td><td>${firstName}</td>
    <tr><td>LastName</td><td>${lastName}</td>
    <tr><td>Country</td><td>${country}</td>
    <tr><td>City</td><td>${city}</td>
    <tr><td>Age</td><td>${age}</td>
    <tr><td>Password</td><td>${password}</td>
</table>
</body>
</html>
