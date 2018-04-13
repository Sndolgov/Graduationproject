var ajaxUrlD = "ajax/admin/menus/dishes/";


function get (id) {
    $("#modalTitleD").html(i18n["editTitle2"]);
    formD.find(":input").val("");
    getRows(id);
    $('#editRowD').modal();
}

function resert() {
    formD.find(":input").val("");
}

function clearForm() {
    form.find("input[name='description']").val("");
    form.find("input[name='price']").val("");
}

function update(id) {
    $.ajax({
        type: "POST",
        url: ajaxUrlD,
        data: formD.serialize()
    }).done(function () {
        resert();
        getRows(id);
        successNoty("Запись сохранена");
    });
}

function addNew(menuId, id) {
    $.ajax({
        type: "POST",
        url: ajaxUrlD+id,
        data: form.serialize()
    }).done(function () {
        clearForm();
        updateTableM(menuId, id);
        successNoty("Запись сохранена");
    });
}



function getRow(id, parentId) {
    $("#modalTitleD").html(i18n["editTitle2"]);
    $.get(ajaxUrlD + id+"/"+parentId, function (data) {
        $.each(data, function (key, value) {
            formD.find("input[name='" + key + "']").val(value);
        });
        getRows(parentId);
    });
}

function renderEdit(data, type, row) {
    if (type === "display") {
        return "<a onclick='getRow(" + row.id + "," + row.parentId +");'>" +
            "<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a>";
    }
}

function renderDelete(data, type, row) {
    if (type === "display") {
        return "<a onclick='rowDelete("+ row.id + ","+row.parentId+");'>" +
            "<span class='glyphicon glyphicon-remove' aria-hidden='true' style='color: red'></span></a>";
    }
}

function rowDelete(id, parentId) {
    $.ajax({
        url: ajaxUrlD + id,
        type: "DELETE"
    }).done(function () {
        formD.find(":input").val("");
        get(parentId);
        successNoty("Запись удалена");
    });
}

function ok()  {
    $("#editRowD").modal("hide");
}


$("#editRowD").on("hide.bs.modal", function () {
    updateTable();
});

function getRows(id) {
    datatable = $("#datatableD").DataTable({
        "ajax": {
            "url": ajaxUrlD + id,
            "dataSrc": ""
        },
        "paging": false,
        "info": false,
        "destroy": true,
        "sDom": "",
        "columns": [
            {
                "data": "description"
            },
            {
                "data": "price"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEdit
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDelete
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
}