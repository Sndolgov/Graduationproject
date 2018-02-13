var ajaxUrl = "ajax/profile/restaurants/";
var datatableApi;

function updateTable() {
    $.get(ajaxUrl, updateTableByData);
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