var ajaxUrl = "ajax/admin/menus/";
var form2;
var datatableApi;
var datatable;

function makeEditable() {
    form = $('#detailsForm');
    form2 = $('#detailsForm2');

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });

    $.ajaxSetup({cache: false});

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}

function renderDelete(data, type, row) {
    if (type === "display") {
        var dish = "dish/"
        return "<a onclick='deleteRow("+ row.id + ");'>" +
            "<span class='glyphicon glyphicon-remove' aria-hidden='true' style='color: red'></span></a>";
    }
}

function renderEditBtnActual(data, type, row) {
    if (type === "display") {
        if (row.actual) {
            return "<a onclick='updateRow(" + row.menuId + ");'>" +
                "<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a>";
        }
        return " ";
    }
}

function get (id) {
    $("#modalTitle2").html(i18n["editTitle2"]);
    getRows(id);
    $('#editRow2').modal();
}

function getRow(id, parentId) {
     $("#modalTitle2").html(i18n["editTitle2"]);
     $.get(ajaxUrl + "dish/" + id, function (data) {
         $.each(data, function (key, value) {
             form2.find("input[name='" + key + "']").val(value);
         });
         getRows(parentId);
     });
}


function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='getRow(" + row.id + "," + row.parentId +");'>" +
            "<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a>";
    }
}

function add() {
    $("#modalTitle").html(i18n["addTitle"]);
    form.find(":input").val("");
    $("#editRow").modal();
    datatable.rows().remove().draw();
}

function updateRow(id) {
    $("#modalTitle").html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        getIncludRows(data.id);
        $('#editRow').modal();
    });
}

function enable(chkbox, dishId, menuId) {
    var enabled = chkbox.is(":checked");
//  https://stackoverflow.com/a/22213543/548473
    $.ajax({
        url: ajaxUrl + dishId + "/" + menuId,
        type: "POST",
        data: "enabled=" + enabled
    }).done(function () {
        chkbox.closest("tr").toggleClass("disabled");
        successNoty(enabled ? "common.enabled" : "common.disabled");
    }).fail(function () {
        $(chkbox).prop("checked", !enabled);
    });
}

$('#editRow').on('hide.bs.modal', function () {
    updateTable();
});

$(function () {
    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "menuDescription"
            },
            {
                "data": "dishDescription"
            },
            {
                "data": "dishPrice"
            },
            {
                "data": "totalValue"
            },
            {
                "data": "voices"
            },
            {
                "data": "date"
            },

            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtnActual
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                5,
                "desc"
            ]
        ],
        "initComplete": makeEditable,
        "createdRow": function (row, data, dataIndex) {
            if (!data.actual) {
                $(row).addClass("disabled");
            }
        }
    });
    $('#date').datetimepicker({
        format: 'Y-m-d',
        timepicker: false
    });
});

function getIncludRows(id) {
    datatable = $("#datatable2").DataTable({
        "ajax": {
            "url": ajaxUrl + "dishes/" + id,
            "dataSrc": ""
        },
        "paging": false,
        "info": false,
        "destroy": true,
        "sDom": '',
        "columns": [
            {
                "data": "description"
            },
            {
                "data": "price"
            },
            {
                "data": "enabled",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<input type='checkbox' " + (data ? "checked" : "") +
                            " onclick='enable($(this)," + row.id + ", " + row.menuId + ");'/>";
                    }
                    return data;
                }
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.enabled) {
                $(row).addClass("disabled");
            }
        }
    });
}

function getRows(id) {
    datatable = $("#datatable3").DataTable({
        "ajax": {
            "url": ajaxUrl + "alldishes/" + id,
            "dataSrc": ""
        },
        "paging": false,
        "info": false,
        "destroy": true,
        "sDom": '',
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
                "render": renderEditBtn
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
