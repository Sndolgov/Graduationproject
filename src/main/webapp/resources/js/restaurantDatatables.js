var ajaxUrl = "ajax/admin/restaurants/";
var datatableApi;

/*function renderBtn(data, type, row) {
 if (type === "display") {
 return "<a onclick='editInRow(" + row.menuId + ");'>" +
 "<span class='glyphicon glyphicon-thumbs-up' aria-hidden='true'></span></a>";
 }
 }*/


/*function editInRow(id) {
 var hour = new Date().getHours();
 if (hour >= 11) {
 $.get(ajaxUrl + "getvoice", deleteFromRow);
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
 }*/

/*function deleteFromRow(id) {
 if (id !== 0) {
 $.ajax({
 url: ajaxUrl + "deletevoice/" + id,
 type: "DELETE"
 })
 .done(function () {
 updateTable();
 successNoty("voice.deleted");
 });
 }
 }

 function rowDelete(id) {
 $.ajax({
 url: ajaxUrl + "admin/" + id,
 type: "DELETE"
 }).done(function () {
 updateTable();
 successNoty("common.deleted");
 });
 }*/

function renderEditBtnAdmin(data, type, row) {
    //document.getElementById('id').value;
    if (type === "display") {
        return "<a href='restaurant/" + row.id + "'>" +
            "<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a>";
        //  }
    }
}

function updateRow(id) {
    $("#modalTitle").html(i18n["editTitle"]);
    var href = "restaurant/" + id;
    document.getElementById('menu').setAttribute('href', href);

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
