$(document).ready(function () {
    $("#countrylist").change(function () {
        clearcity();
        var country = $("#countrylist").val();
        $.ajax({
            url : 'ajax',
            type : 'get',
            dataType : 'json',
            data : {country : country},
            complete: function (data) {
                var result = "<option>Choose the city</option>";
                var cities = JSON.parse(data.responseText);
                for (var i = 0; i != cities.length; i++) {
                    result += "<option>" + cities[i] + "</option></br>";
                }
                var selectOptions = $("#citylist");
                selectOptions.attr("disabled", false);
                $(result).appendTo(selectOptions);
            }
        });
    });
});

function clearcity() {
    $("#citylist").empty();
}