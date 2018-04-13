<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    var i18n = [];
    <c:forEach var='key' items='<%=new String[]{"Запись удалена", "Запись сохранена",
    "Запись активирована", "Запись деактивирована", "Искать", "Голос учтен", "Предыдущий голос отменен"}%>'>
    i18n['${key}'] = "${key}";
    </c:forEach>
</script>