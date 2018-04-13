<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/menuDatatables.js" defer></script>
<script type="text/javascript" src="resources/js/dishDatatables.js" defer></script>

<div class="jumbotron">
    <div class="container">
        <h3>${restaurant.name}</h3>

        <a class="btn btn-primary" onclick="addM()">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            Добавить
        </a>

        <a class="btn btn-primary" onclick="get(${restaurant.id})" style="background: green;">
            <%--<a class="btn btn-primary" onclick="getDish('100008')" style="background: green;">--%>
            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
            Блюда
        </a>

        <table class="table table-striped display" id="datatable">
            <thead>
            <tr>
                <th>Название меню</th>
                <th>Блюда</th>
                <th>Цена блюда</th>
                <th>Итого</th>
                <th>Голоса</th>
                <th>Дата меню</th>
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
                            Название меню</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="description" name="menuDescription"
                                   placeholder="Название меню">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="date" class="control-label col-xs-3">Дата меню</label>

                        <div class="col-xs-9">
                            <input class="form-control" id="date" name="date"
                                   placeholder="Дата меню">
                        </div>
                    </div>

                    <div class="form-group" id="dishes">
                        <label for="datatableM" class="control-label col-xs-3">Блюда</label>

                        <div class="col-xs-9">
                            <table class="table table-striped display" id="datatableM">
                                <thead>
                                <tr>
                                    <th><h5>Блюда включены</h5></th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>

                    <div class="form-group" id="addNew">
                        <label for="descriptionD" class="control-label col-xs-3">
                            Добавить новое блюдо</label>

                        <div class="col-xs-4">
                            <input type="text" class="form-control" id="nameD" name="description"
                                   placeholder="Название блюда">
                        </div>

                        <div class="col-xs-3">
                            <input type="number" class="form-control" id="price" name="price"
                                   placeholder="Цена блюда">
                        </div>

                        <button type="button" onclick="addNew(document.getElementById('id').value, ${restaurant.id})"
                                class="btn btn-primary" style="background: none; border-style: none">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true" style="color: blue"></span>
                        </button>

                        <button type="button" onclick="clearForm()" class="btn btn-primary"
                                style="background: none; border-style: none">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true" style="color: red"></span>
                        </button>

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

<div class="modal fade" id="editRowD">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitleD"></h2>

            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="detailsFormD">

                    <div class="form-group">
                        <label for="datatableD" class="control-label col-xs-3"></label>

                        <div class="col-xs-9">
                            <table class="table table-striped display" id="datatableD">
                                <thead>
                                <tr>
                                    <th>Блюда</th>
                                    <th>Цена блюда</th>
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
                        <label for="descriptionD" class="control-label col-xs-2">
                            Блюда</label>

                        <div class="col-xs-4">
                            <input type="text" class="form-control" id="descriptionD" name="description"
                                   placeholder="Название блюда">
                        </div>

                        <div class="col-xs-4">
                            <input type="number" class="form-control" name="price"
                                   placeholder="Цена блюда">
                        </div>

                        <button type="button" onclick="update(${restaurant.id})" class="btn btn-primary"
                                style="background: none; border-style: none">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true" style="color: blue"></span>
                        </button>

                        <button type="button" onclick="resert()" class="btn btn-primary"
                                style="background: none; border-style: none">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true" style="color: red"></span>
                        </button>
                    </div>

                    <br>
                    <br>

                    <div class="form-group">
                        <div class="col-xs-offset-8 col-xs-9">
                            <button type="button" onclick="ok()" class="btn btn-primary">
                                Закрыть
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
    i18n["addTitle"] = 'Добавление меню';
    i18n["editTitle"] = 'Редактирование меню';
    i18n["editTitle2"] = 'Редактирование блюд';

</script>
</html>