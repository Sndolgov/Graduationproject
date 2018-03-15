var ajaxUrl = "ajax/admin/menus/";
var datatableApi;

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRow(" + row.menuId + ");'>" +
            "<span class='glyphicon glyphicon-remove' aria-hidden='true'></span></a>";
    }
}


function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRow(" + row.menuId + ");'>" +
            "<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a>";
    }
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
                0,
                "asc"
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
    $("#menutable").DataTable({
        "ajax": {
            "url": ajaxUrl+"dishes/"+id,
            "dataSrc": ""
        },
        "paging": false,
        "info": false,
        "destroy": true,
        "columns": [
            {
                "data": "description"
            }
        ]

    });
}