$(document).ready(function () {
    $("#button_item").click(function () {
        create_item();
    });
    checkViewAll();
    $("#view_all").click(function () {
        checkViewAll();
    });

    $("#all_items").on('click', '.checked_items', function () {
        var id = $(this.elements[1].attributes[1]).val();
        var desc = $(this.elements[2].attributes[1]).val();
        var created = $(this.elements[3].attributes[1]).val();
        var done = $(this.elements[4].attributes[1]).val();
        var json = {"id": id, "desc": desc, "created": created, "done": done};
        update_item(json);
    });
});

function create_item() {
    var description = $("#description_item").val();
    var json = {"description" : description};
    $.ajax({
        type: "POST",
        data: json,
        url: "create",
        success: function (data) {
            checkViewAll();
        }
    });
}

function checkViewAll() {
    if ($("#view_all").prop("checked")) {
        getItems("get_items");
    } else {
        getItems("get_failed_items");
    }
}

function getItems(url) {
    $("#items_table").empty();
    $.getJSON(url, function (data) {
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
    var checkbox = "<form class='checked_items' >" +
        "<input type='checkbox'";
    if (items.done == true)
        checkbox += "checked";
    checkbox += ">" +
        "<input type='hidden' value='" + items.id + "'>" +
        "<input type='hidden' value='" + items.desc + "'>" +
        "<input type='hidden' value='" + items.created + "'>" +
        "<input type='hidden' value='" + items.done + "'>" +
        "</form>";
    return checkbox;
}

function update_item(json) {
    $.ajax({
        type: "POST",
        data: json,
        url: "update_item",
        success: function (data) {
            console.log(data.toString());
            checkViewAll();
        }
    });
}