$(document).ready(function () {
    getall();
    getroles("#roleslist");
    getmusics("#musiclist");
    getroles("#newroleslist");
    getmusics("#newmusiclist");

    $("#button-find-all").click(function () {
        getall();
    })

    $("#button-find-address").click(function () {
        findByAddress();
    });

    $("#button-find-role").click(function () {
        findByRole();
    });

    $("#button-find-music").click(function () {
        findByMusic();
    });

    $("#button").click(function () {
        setUser();
    });
    $("#allusers").on('click', '.update-user', function () {
        var login = $(this.elements[1].attributes[1]).val();
        $("#updateuser").show();
        $("#newbutton").click(function () {
            updateUser(login);
        })
    });
    $("#allusers").on('click', '.delete-user', function () {
        var login = $(this.elements[1].attributes[1]).val();
        deleteUser(login);
    })
});

function getall () {
    $.getJSON("show", function (data) {
        printTable(data);
    });
}

function getroles(rolelist) {
    $.getJSON("loadroles", function (data) {
        var result = "<option value=''>" + "select" + "</option>";
        $.each(data, function (index, roles) {
            result += "<option value='" + roles.id + "'>" + roles.roleType + "</option>";
        });
        var roleslist = $(rolelist);
        $(result).appendTo(roleslist);
    });
}

function getmusics(musics) {
    $.getJSON("loadmusics", function (data) {
        var result = "<br>";
        $.each(data, function (index, musics) {
            result += "<input type='checkbox' value='" + musics.id + "'>" + musics.musicType + "<br>";
        });
        var musicslist = $(musics);
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
        success: function (data) {
            printTable (data);
            $('#userform')[0].reset();
        }
    });
}

function updateUser(login) {
    var password = $('#newpassword').val();
    var name = $('#newname').val();
    var email = $('#newemail').val();
    var country = $('#newcountry').val();
    var city = $('#newcity').val();
    var street = $('#newstreet').val();
    var house = $('#newhouse').val();
    var role = $('#newroleslist').val();
    var musics = [];
    $('input:checkbox:checked').each(function () {
        musics.push($(this).val());
    });
    var arr = JSON.stringify(musics);
    var json = {"login" : login, "password": password, "name": name, "email": email, "country": country, "city": city, "street": street, "house": house, "role": role, "musics": arr};
    $.ajax({
        type: "POST",
        data: json,
        url: "update",
        success: function (data) {
            printTable (data);
            $('#updateform')[0].reset();
            $("#updateuser").hide();
        }
    });
}

function deleteUser(login) {
    var json = {"login" : login};
    $.ajax({
        type: "POST",
        data: json,
        url: "delete",
        success: function (data) {
            printTable(data);
        }
    });
}

function findByAddress() {
    var country = $("#by-country").val();
    var city = $("#by-city").val();
    var street = $("#by-street").val();
    var house = $("#by-house").val();
    var json = {"country" : country, "city" : city, "street": street, "house": house};
    $.ajax({
        type: "POST",
        data: json,
        url: "findbyaddress",
        success: function (data) {
            printTable(data);
        }
    });
}

function findByRole() {
    var json = {"role" : $("#by-role").val()};
    $.ajax({
        type: "POST",
        data: json,
        url: "findbyrole",
        success: function (data) {
            printTable(data);
        }
    });
}

function findByMusic() {
    var json = {"music": $("#by-music").val()};
    $.ajax({
        type: "POST",
        data: json,
        url: "findbymusic",
        success: function (data) {
            printTable(data);
        }
    });
}

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
    var buttons = "<td><form class='update-user'>" +
        "<input type='button' value='update'>" +
        "<input type='hidden' value='" + login + "'>" +
        "</form></td>" +
        "<td><form class='delete-user'>" +
        "<input type='button' value='delete'>" +
        "<input type='hidden' value='" + login + "'>" +
        "</form></td>";
    return buttons;
}

