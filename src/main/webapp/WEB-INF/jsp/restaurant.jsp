<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="graduation" tagdir="/WEB-INF/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <%--@elvariable id="restaurantTo" type="ru.sndolgov.graduationproject.to.RestaurantTo"--%>
        <h2>${RestaurantTo.restaurantName} </h2>

        <form:form modelAttribute="restaurantTo" class="form-horizontal" method="post" action='register'
                   charset="utf-8" accept-charset="UTF-8">

            <spring:message code="user.name" var="userName"/>
            <graduation:inputField label='${userName}' name="restaurantName"/>

          <%--  <spring:message code="user.email" var="userEmail"/>
            <graduation:inputField label='${userEmail}' name="email"/>

            <spring:message code="user.password" var="userPassword"/>
            <graduation:inputField label='${userPassword}' name="password" inputType="password"/>--%>

            <div class="form-group">
                <div class="col-xs-offset-2 col-xs-10">
                    <button type="submit" class="btn btn-primary">
                        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                    </button>
                </div>
            </div>
        </form:form>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>