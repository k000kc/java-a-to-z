$(document).ready(function () {
    getall();
    getroles("loadroles");
    getmusics("loadmusics");
    $("#button").click(function () {
        setUser();
    });
});

function getall () {
    $.getJSON("show", function (data) {
        printTable(data);
    });
};

function getroles(roles) {
    $.getJSON(roles, function (data) {
        var result = "<option value=''>" + "select" + "</option>";
        $.each(data, function (index, roles) {
            result += "<option value='" + roles.id + "'>" + roles.roleType + "</option>";
        });
        var roleslist = $("#roleslist");
        $(result).appendTo(roleslist);
    });
};

function getmusics(musics) {
    $.getJSON(musics, function (data) {
        var result = "<br>";
        $.each(data, function (index, musics) {
            result += "<input type='checkbox' value='" + musics.id + "'>" + musics.musicType + "<br>";
        });
        var musicslist = $("#musiclist");
        $(result).appendTo(musicslist);
    });
}

function setUser() {
    var login = $("#login").val();
    var password = $('#password').val();
    var name = $('#name').val();
    var email = $('#email').val();
    var country = $('#country').val();
    var city = $('#city').val();
    var street = $('#street').val();
    var house = $('#house').val();
    var role = $('#roleslist').val();
    var musics = [];
    $('input:checkbox:checked').each(function () {
        musics.push($(this).val());
    });
    var arr = JSON.stringify(musics);
    var json = {"login" : login, "password": password, "name": name, "email": email, "country": country, "city": city, "street": street, "house": house, "role": role, "musics": arr};
    $.ajax({
        type: "POST",
        data: json,
        url: "add",
        complete: function (data) {
            printTable(data);
            $('#userform')[0].reset();
        }
    });
};

function printTable(data) {
    $("#userstable").empty();
    var result = "<tr>" +
        "<th>login</th>" +
        "<th>user_name</th>" +
        "<th>email</th>" +
        "<th>country</th>" +
        "<th>city</th>" +
        "<th>street</th>" +
        "<th>house</th>" +
        "<th>role</th>" +
        "<th>music_type</th>" +
        "<th colspan='2'>actions</th>" +
        "</tr>";
    $.each(data, function (index, users) {
        result += "<tr><td>" + users.login + "</td>";
        result += "<td>" + users.name + "</td>";
        result += "<td>" + users.email + "</td>";
        result += "<td>" + users.address.country + "</td>";
        result += "<td>" + users.address.city + "</td>";
        result += "<td>" + users.address.street + "</td>";
        result += "<td>" + users.address.house + "</td>";
        result += "<td>" + users.role.roleType + "</td>";
        result += "<td>";
        for (var i = 0; i < users.musicTypes.length; i++) {
            result += users.musicTypes[i].musicType + ", ";
        }
        result += "</td>";
        result += returnbutton(users.login);
        result += "</tr>";
    });
    var usertable = $("#userstable");
    $(result).appendTo(usertable);
}

function returnbutton(login) {
    var buttons = "<td><form action='/update' method='get'>" +
        "<input type='submit' value='update' class='submit>" +
        "<input type='hidden' name='login' value='" + login + "'>" +
        "</form>" +
        "</td>" +
        "<td>" +
        "<form>" +
        "<input type='button' value='delete' class='delete-user'>" +
        "</form></td>";
    return buttons;
};

