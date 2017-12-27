<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<body>
<section>
    <form method="post" action="users">
        Зайти как: <select name="userId">
    <option value="100000" selected>User</option>
    <option value="100001">Admin</option>
</select>
        <button type="submit">Выбрать</button>
</form>
<ul>
    <li><a href="users">Пользователи</a></li>
</ul>
</section>
</body>
</html>