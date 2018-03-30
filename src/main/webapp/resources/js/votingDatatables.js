var ajaxUrl = "ajax/profile/voting/";
var datatableApi;

function renderBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='editInRow(" + row.menuId + ");'>" +
            "<span class='glyphicon glyphicon-thumbs-up' aria-hidden='true'></span></a>";
    }
}

function deleteFromRow(id) {
    if (id!==0) {
        $.ajax({
            url: ajaxUrl + id,
            type: "DELETE"
        })
            .done(function () {
                updateTable();
                successNoty("voice.deleted");
            });
    }
}

function editInRow(id) {
    var hour = new Date().getHours();
    if (hour<11) {
        $.get(ajaxUrl+"getvoice", deleteFromRow);
    }
    setTimeout(function addInRow() {
        $.ajax({
            url: ajaxUrl + id,
            type: "PUT"
        }).done(function () {
            updateTable();
            successNoty("voice.saved");
        });
    }, 500)
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
                "data": "restaurantName"
            },
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
                "render": renderBtn,
                "defaultContent": "",
                "orderable": false
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
});