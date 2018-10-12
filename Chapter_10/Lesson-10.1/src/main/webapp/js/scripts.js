$(document).ready(function () {
    $("#button_item").click(function () {
        create_item();
    });
    getItems();
});

function create_item() {
    var description = $("#description_item").val();
    var json = {"description" : description};
    $.ajax({
        type: "POST",
        data: json,
        url: "create",
        success: function (data) {
            console.log("created");
        }
    });
}

function getItems() {
    console.log("ggg");
    $.getJSON("get_items", function (data) {
        var result = "<tr>" +
            "<th>id</th>" +
            "<th>description</th>" +
            "<th>created</th>" +
            "<th>done</th>" +
            "</tr>";
        $.each(data, function (index, items) {
            result += "<tr><td>" + items.id + "</td>" +
                "<td>" + items.desc + "</td>" +
                "<td>" + items.created + "</td>" +
                "<td>" + get_checkbox(items) + "</td></tr>";
        });
        var table = $("#items_table");
        $(result).appendTo(table);
    })
}

function get_checkbox(items) {
    var checkbox = "<td>" +
        "<input type='checkbox' ";
    if (items.done == true)
        checkbox += "checked";
    checkbox += ">" +
        "<input type='hidden' value='" + items.id + "'>";
    return checkbox;
}