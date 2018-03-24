var ajaxUrl = "ajax/admin/menus/";
var formD;
var datatableApi;
var datatable;

function makeEditable() {
    form = $('#detailsForm');
    formD = $('#detailsFormD');

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

function add() {
    $("#modalTitle").html(i18n["addTitle"]);
    form.find(":input").val("");
    if(datatable!==undefined) {
        datatable.rows().remove().draw();
    }
    $("#editRow").modal();
}

function updateRow(id) {
    $("#modalTitle").html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        getIncludRows(data.id, data.restaurantId);
        $('#editRow').modal();
    });
}

function renderEditBtnActual(data, type, row) {
    if (type === "display") {
        if (row.actual) {
            return "<a onclick='updateRow(" + row.id + ");'>" +
                "<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a>";
        }
        return " ";
    }
}

function enable(chkbox, id, parentId) {
    var enabled = chkbox.is(":checked");
//  https://stackoverflow.com/a/22213543/548473
    $.ajax({
        url: ajaxUrl + id + "/" + parentId,
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

function getIncludRows(id, restaurantId) {
    datatable = $("#datatableM").DataTable({
        "ajax": {
            "url": ajaxUrl + id + "/" + restaurantId,
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
                            " onclick='enable($(this)," + row.id + ", " + row.parentId + ");'/>";
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


