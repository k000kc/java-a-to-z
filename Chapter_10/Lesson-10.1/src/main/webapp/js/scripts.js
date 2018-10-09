$(document).ready(function () {
    console.log("click");
    $("#button_item").click(function () {
        console.log("click2");
        create_item();
    })
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