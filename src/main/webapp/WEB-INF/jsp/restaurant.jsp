<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/menuDatatables.js" defer></script>
<div class="jumbotron">
    <div class="container">
        <h3>${restaurant.name}</h3>

        <a class="btn btn-primary" onclick="add()">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            <spring:message code="common.add"/>
        </a>

        <table class="table table-striped display" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="menu.description"/></th>
                <th><spring:message code="dish.description"/></th>
                <th><spring:message code="dish.price"/></th>
                <th><spring:message code="menu.totalValue"/></th>
                <th><spring:message code="menu.voices"/></th>
                <th><spring:message code="menu.date"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitle"></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="detailsForm">
                    <div class="form-group">
                        <label for="id" class="control-label col-xs-3">Id</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="id" name="id"
                                   placeholder="id">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="restaurantId" class="control-label col-xs-3">restaurantId</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="restaurantId" name="restaurantId"
                                   placeholder="MenuId">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="restaurantName" class="control-label col-xs-3"><spring:message
                                code="restaurant.name"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="restaurantName" name="restaurantName"
                                   placeholder="<spring:message code="restaurant.name"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="menuDescription" class="control-label col-xs-3"><spring:message
                                code="menu.description"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="menuDescription" name="menuDescription"
                                   placeholder="<spring:message code="menu.description"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="date" class="control-label col-xs-3"><spring:message code="menu.date"/></label>

                        <div class="col-xs-9">
                            <input class="form-control" id="date" name="date"
                                   placeholder="<spring:message code="menu.date"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="menutable" class="control-label col-xs-3"><spring:message code="dish.description"/></label>

                        <div class="col-xs-9">
                            <table class="table table-striped display" id="menutable">
                                <thead>
                                <tr>
                                    <th><h5>Включены в меню</h5></th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="button" onclick="save()" class="btn btn-primary">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<jsp:include page="fragments/bodyHeader.jsp"/>
<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp"/>
<script type="text/javascript">
    i18n["addTitle"] = '<spring:message code="menu.add"/>';
    i18n["editTitle"] = '<spring:message code="menu.edit"/>';
</script>
</html>