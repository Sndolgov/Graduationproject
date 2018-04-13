<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <c:if test="${param.error}">
            <div class="error">
                    ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
            </div>
        </c:if>
        <c:if test="${not empty param.message}">
            <div class="message">
                ${param.message}"
            </div>
        </c:if>
        <br/>
        <p>
            <a class="btn btn-lg btn-success" href="register">Регистрирация &raquo;</a>
            <buttonF type="submit" class="btn btn-lg btn-primary" onclick="setCredentials('user@yandex.ru', 'password')">
                Войти как User
            </buttonF>
            <button type="submit" class="btn btn-lg btn-primary" onclick="setCredentials('admin@gmail.com', 'admin')">
                Войти как Admin
            </button>
        </p>
        <br/>
        <p>Стек технологий: <a href="http://projects.spring.io/spring-security/">Spring Security</a>,
            <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html">Spring MVC</a>,
            <a href="http://projects.spring.io/spring-data-jpa/">Spring Data JPA</a>,
            <a href="http://spring.io/blog/2014/05/07/preview-spring-security-test-method-security">Spring Security
                Test</a>,
            <a href="http://hibernate.org/orm/">Hibernate ORM</a>,
            <a href="http://hibernate.org/validator/">Hibernate Validator</a>,
            <a href="http://www.slf4j.org/">SLF4J</a>,
            <a href="https://github.com/FasterXML/jackson">Json Jackson</a>,
            <a href="http://ru.wikipedia.org/wiki/JSP">JSP</a>,
            <a href="http://en.wikipedia.org/wiki/JavaServer_Pages_Standard_Tag_Library">JSTL</a>,
            <a href="http://tomcat.apache.org/">Apache Tomcat</a>,
            <a href="http://www.webjars.org/">WebJars</a>,
            <a href="http://datatables.net/">DataTables plugin</a>,
            <a href="http://ehcache.org">EHCACHE</a>,
            <a href="http://www.postgresql.org/">PostgreSQL</a>,
            <a href="http://junit.org/">JUnit</a>,
            <a href="http://hamcrest.org/JavaHamcrest/">Hamcrest</a>,
            <a href="http://jquery.com/">jQuery</a>,
            <a href="http://ned.im/noty/">jQuery notification</a>,
            <a href="http://getbootstrap.com/">Bootstrap</a>.</p>
    </div>
</div>
<div class="container">
    <div class="lead">
        &nbsp;&nbsp;&nbsp;<a href="https://github.com/Sndolgov/graduationproject">Java Enterprise проект</a> 2 типа пользователей: администратор и постоянные пользователи
        Администратор может добавить ресторан и его обеденное меню дня (обычно 2-5 блюд, только c названием и ценой)
        Меню меняется каждый день (администраторы делают обновления)
        Пользователи могут проголосовать, в каком ресторане они хотят пообедать.
        Пользователь может отдать только один голос.
        Если пользователь снова проголосовал в тот же день:
        Если до 11:00 мы предполагаем, что он передумал.
        Если это после 11:00, то уже слишком поздно, голосование не может быть изменено.
        Каждый ресторан предлагает новое меню каждый день.
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
<script type="text/javascript">
    <c:if test="${not empty param.username}">
    setCredentials("${param.username}", "");
    </c:if>
    function setCredentials(username, password) {
        $('input[name="username"]').val(username);
        $('input[name="password"]').val(password);
    }
</script>
</body>
</html>