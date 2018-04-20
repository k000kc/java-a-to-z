$(document).ready(function () {
    $("#countrylist").change(function () {
        var countryvalue = $("#countrylist option:selected");
        getcities();
    })
});

function getcities() {
    var countryvalue = $("#countrylist option:selected");
    var cityvalue = $("#citylist");
    if (countryvalue.length == 0) {
        cityvalue.setAttribute("disabled",true);
    } else {
        cityvalue.setAttribute("disabled",false);
        cityvalue.load()
    }
}
