var ajaxUrl = "ajax/admin/restaurants/";
var datatableApi;

function add() {
    $("#modalTitle").html(i18n["addTitle"]);
    document.getElementById('menu').setAttribute('style', 'visibility:hidden;');
    form.find(":input").val("");
    $("#editRow").modal();
}

function updateRow(id) {
    $("#modalTitle").html(i18n["editTitle"]);
    var href = "restaurant/" + id;
    document.getElementById('menu').setAttribute('href', href);
    document.getElementById('menu').setAttribute('style', 'visibility:visible;');

    $.get(ajaxUrl + id, function (data) {
            $.each(data, function (key, value) {
                form.find("input[name='" + key + "']").val(value);
            });
            $('#editRow').modal();
        }
    )
    ;
}

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
                "data": "name"
            },
            {
                "data": "enabled",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<input type='checkbox' " + (data ? "checked" : "") + " onclick='enable($(this)," + row.id + ");'/>";
                    }
                    return data;
                }
            },
            {
                "defaultContent": "",
                "orderable": false,
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
                0,
                "asc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.enabled) {
                $(row).addClass("disabled");
            }
        },
        "initComplete": makeEditable
    });
});
