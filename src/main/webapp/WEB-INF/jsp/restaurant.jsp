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

        <a class="btn btn-primary" onclick="get(${restaurant.id})" style="background: green;">
        <%--<a class="btn btn-primary" onclick="getDish('100008')" style="background: green;">--%>
            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
            <spring:message code="dish.dishes"/>
        </a>

        <table class="table table-striped display" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="menu.description"/></th>
                <th><spring:message code="dish.dishes"/></th>
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

                    <input type="hidden" id="id" name="id">
                    <input type="hidden" id="restId" name="restaurantId">



                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">
                            <spring:message code="menu.description"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="description" name="menuDescription"
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
                        <label for="datatable2" class="control-label col-xs-3"><spring:message
                                code="dish.dishes"/></label>

                        <div class="col-xs-9">
                            <table class="table table-striped display" id="datatable2">
                                <thead>
                                <tr>
                                    <th><h5><spring:message code="dish.included"/></h5></th>
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

<div class="modal fade" id="editRow2">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitle2"></h2>

            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="detailsForm2">

                    <div class="form-group">
                        <label for="datatable3" class="control-label col-xs-3"></label>

                        <div class="col-xs-9">
                            <table class="table table-striped display" id="datatable3">
                                <thead>
                                <tr>
                                    <th><spring:message code="dish.dishes"/></th>
                                    <th><spring:message code="dish.price"/></th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>

                    <input type="hidden" id="idD" name="id">
                    <input type="hidden" id="parentId" name="parentId">

                    <div class="form-group">
                        <label for="descriptionD" class="control-label col-xs-3">
                            <spring:message code="dish.dishes"/></label>

                        <div class="col-xs-4">
                            <input type="text" class="form-control" id="descriptionD" name="description"
                                   placeholder="<spring:message code="dish.description"/>">
                        </div>

                        <div class="col-xs-4">
                            <input type="text" class="form-control" id="price" name="price"
                                   placeholder="<spring:message code="dish.price"/>">
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
    i18n["editTitle2"] = '<spring:message code="dish.edit"/>';

</script>
</html>