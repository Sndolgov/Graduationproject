<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/restaurantDatatables.js" defer></script>
<div class="jumbotron">
    <div class="container">
        <h3>Список рестаранов</h3>

        <a class="btn btn-primary" onclick="add()">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            Добавить
        </a>

        <table class="table table-striped display" id="datatable">
            <thead>
            <tr>
                <th>Название ресторана</th>
                <th>Активный</th>
                <th></th>
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

                    <div class="form-group">
                        <label for="name" class="control-label col-xs-4">Название ресторана</label>

                        <div class="col-xs-7">
                            <input type="text" class="form-control" id="name" name="name"
                                   placeholder="Название ресторана">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="name" class="control-label col-xs-4"></label>

                        <div class="col-xs-7">
                                <a class="btn btn-info" href id="menu"> Редактирование меню</a>

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
    i18n["addTitle"] = 'Добавление ресторана';
</script>
</html>