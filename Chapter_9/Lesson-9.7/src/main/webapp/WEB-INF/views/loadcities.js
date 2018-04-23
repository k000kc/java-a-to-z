$(document).ready(function () {
    $("#countrylist").change(function () {
        $("#citylist").empty();

        var country = $("#countrylist option:selected").val();
        $.ajax({
            url : 'ajax',
            type : 'get',
            data: {country : country},
            complete: function (data) {
                var result = "<option>Choose the city</option>";
                var cities = JSON.parse(data.responseText);
                for (var i = 0; i != cities.length; i++) {
                    result += "<option>" + cities[i] + "</option>"
                }
                var selectOptions = $("#citylist");
                selectOptions.attr("disabled", false);
                $(result).appendTo(selectOptions);
            }
        });
    });
});