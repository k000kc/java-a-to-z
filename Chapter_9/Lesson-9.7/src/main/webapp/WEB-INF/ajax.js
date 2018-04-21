$(document).ready(function () {
    $("#countrylist").change(function () {
        var county = $("#countrylist option:selected").val();
        alert(county)
    })
})