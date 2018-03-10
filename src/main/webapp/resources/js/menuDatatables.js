var ajaxUrl = "ajax/admin/menus/";
var datatableApi;

function getId() {
    get = String(window.location);
    x = get.indexOf('?');
    l = get.length;
    get = get.substr(x+1, l-x);
    x = get.indexOf('=');
    l = get.length;
    return get.substr(x+1, l);
}

$(function () {
    id = getId()
    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": ajaxUrl+id,
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