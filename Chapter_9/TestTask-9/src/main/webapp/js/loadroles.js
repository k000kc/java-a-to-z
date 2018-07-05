$(document).ready(function getroles() {
    $.getJSON("loadroles", function (data) {
        var result = "";
        $.each(data, function (index, roles) {
            result += "<option value='" + roles.id + "'>" + roles.roleType + "</option>>";
        });
        var roleslist = $("#roleslist");
        $(result).appendTo(roleslist);
    });
});