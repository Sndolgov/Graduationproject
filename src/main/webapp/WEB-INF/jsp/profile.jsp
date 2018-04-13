<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="graduation" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">

        <h2>${userTo.name} ${register ? 'Регистрирация' : 'профиль'} </h2>

        <form:form modelAttribute="userTo" class="form-horizontal" method="post"
                   action="${register ? 'register' : 'profile'}"
                   charset="utf-8" accept-charset="UTF-8">


            <graduation:inputField label='Имя' name="name"/>


            <graduation:inputField label='Почта' name="email"/>

            <graduation:inputField label='Пароль' name="password" inputType="password"/>

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