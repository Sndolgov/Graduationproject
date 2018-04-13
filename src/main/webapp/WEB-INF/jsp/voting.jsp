<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/votingDatatables.js" defer></script>
<div class="jumbotron">
    <div class="container">
        <h3>Меню ресторанов на сегодня</h3>

        <table class="table table-striped display" id="datatable">
            <thead>
            <tr>
                <th>Название ресторана</th>
                <th>Название меню</th>
                <th>Блюда</th>
                <th>Цена блюда</th>
                <th>Итого</th>
                <th>Голоса</th>

                <th></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<jsp:include page="fragments/bodyHeader.jsp"/>
<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp"/>
</html>