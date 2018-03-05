var ajaxUrl = "ajax/profile/restaurants/";
var datatableApi;

function renderBtnVoice(data, type, row) {
    if (type === "display") {
        return "<a onclick='voiceRow(" + row.menuId + ");'>" +
            "<span class='glyphicon glyphicon-thumbs-up' aria-hidden='true'></span></a>";
    }
}


function voiceRow(id) {
    var hour = new Date().getHours();
    if (hour>=11) {
        deleteVoice();
    }
    setTimeout(function addVoice() {
        $.ajax({
            url: ajaxUrl + id,
            type: "PUT"
        }).done(function () {
            updateTable();
            successNoty("voice.saved");
        });
    }, 500)
}

function deleteVoice() {
        $.ajax({
            url: ajaxUrl + "deletevoice",
            type: "DELETE"
        }).done(function () {
            updateTable();
            successNoty("voice.deleted");
        });
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
                "render": renderBtnVoice,
                "defaultContent": "",
                "orderable": false
            },
            {
                "render": renderEditBtnAdmin,
                "defaultContent": "",
                "orderable": false
            },
            {
                "render": renderDeleteBtnAdmin,
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