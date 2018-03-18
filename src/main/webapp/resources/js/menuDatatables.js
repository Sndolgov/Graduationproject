var ajaxUrl = "ajax/admin/menus/";
var datatableApi;
var datatable;

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRow(" + row.menuId + ");'>" +
            "<span class='glyphicon glyphicon-remove' aria-hidden='true'></span></a>";
    }
}

/*function renderDelete(data, type, row) {
    if (type === "display") {
        return "<a onclick='alert(" + row.id + ")'>" +
            "<span class='glyphicon glyphicon-remove' aria-hidden='true'></span></a>";
    }
}*/

/*function renderAddDeleteBtn(data, type, row) {
 if (type === "display") {
 if (row.inMenu === true) {
 return "<a onclick='deleteRow(" + row.menuId + ");'>" +
 "<span class='glyphicon glyphicon-remove' aria-hidden='true' style='color: red;'></span></a>";
 }
 else {
 return "<a onclick='updateRow(" + row.menuId + ");'>" +
 "<span class='glyphicon glyphicon-plus' aria-hidden='true'></span></a>";
 }
 }
 }*/


function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRow(" + row.menuId + ");'>" +
            "<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a>";
    }
}

function updateRow(id) {
    $("#modalTitle").html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
            if (key === "restaurantName")
                $("#restaurantName").html(value);
            if (key === "dishes") {
                getDishes(data.id)
            }
        });
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

$('#editRow').on('hide.bs.modal', function() {
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
                "render": renderEditBtn
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
        "initComplete": makeEditable
    });
    $('#date').datetimepicker({
        format: 'Y-m-d',
        timepicker: false
    });
});

function getDishes(id) {
    datatable= $("#menutable").DataTable({
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
                "data": "inMenu",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<input type='checkbox' " + (data ? "checked" : "") +
                            " onclick='enable($(this)," + row.id + ", " + row.menuId + ");'/>";
                    }
                    return data;
                }
            }
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.inMenu) {
                $(row).addClass("disabled");
            }
        }
    });
}
