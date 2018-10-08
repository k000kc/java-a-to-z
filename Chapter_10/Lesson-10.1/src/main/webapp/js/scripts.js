$(document).ready(function () {
    $("#button_item").click(function () {
        create_item();
    })
});

function create_item() {
    var description = $("#add_item").val();
    var json = {description : description};
    $.ajax({
        type: "POST",
        data: json,
        url: create,
        success: function (data) {
            console.log("created");
        }
    })
}