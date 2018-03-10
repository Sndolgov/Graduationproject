<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <a href="restaurants" class="navbar-brand"><spring:message code="app.title"/></a>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <sec:authorize access="isAuthenticated()">
                        <form:form class="navbar-form" action="logout" method="post">
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <a class="btn btn-info" href="users"><spring:message code="user.title"/></a>
                            </sec:authorize>
                            <a class="btn btn-info" href="profile"><sec:authentication
                                    property="principal.userTo.name"/> <spring:message code="app.profile"/></a>
                            <button class="btn btn-primary" type="submit">
                                <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                            </button>
                        </form:form>
                    </sec:authorize>
                    <sec:authorize access="isAnonymous()">
                        <form:form class="navbar-form" action="spring_security_check" method="post">
                            <div class="form-group">
                                <input type="text" placeholder="Email" class="form-control" name="username">
                            </div>
                            <div class="form-group">
                                <input type="password" placeholder="Password" class="form-control" name="password">
                            </div>
                            <button type="submit" class="btn btn-success">
                                <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
                            </button>
                        </form:form>
                    </sec:authorize>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">${pageContext.response.locale}<b
                            class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="${requestScope['javax.servlet.forward.request_uri']}?lang=en">English</a></li>
                        <li><a href="${requestScope['javax.servlet.forward.request_uri']}?lang=ru">Русский</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>

<script type="text/javascript">
    function renderDeleteBtnAdmin(data, type, row) {
        if (type === "display") {
            return "<sec:authorize access="hasRole('ROLE_ADMIN')">" + "<a onclick='rowDelete(" + row.id + ");'>" +
                "<span class='glyphicon glyphicon-remove' aria-hidden='true'></span></a>" + "</sec:authorize>";
        }
    }

    function renderEditBtnAdmin(data, type, row) {
        if (type === "display") {
            return "<sec:authorize access="hasRole('ROLE_ADMIN')">" + "<a href='restaurant?id=" + row.id + "'>" +
                "<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a>" + "</sec:authorize>";
        }
    }
</script>


<script type="text/javascript">
    var localeCode = "${pageContext.response.locale}";
</script>
