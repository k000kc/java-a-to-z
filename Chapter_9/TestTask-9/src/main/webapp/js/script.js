// $(document).ready(function () {
//     $('#button').click(function () {
//         var data = {"login": $("#login").val(), "password": $("#password").val()}
//         $.ajax({
//             type: "POST",
//             data: data,
//             url: 'signin',
//             success: function () {
//             }
//         })
//     });
// })
$(document).ready(getall("show"));

function getall (url) {
    $.getJSON(url, function (data) {
        var result = "";
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
    });
};

function returnbutton(login) {
    var buttons = "<td><form action='/update' method='get'>" +
                    "<input type='button' value='update' class='button'>" +
                    "<input type='hidden' name='login' value='" + login + "'>" +
                "</form>" +
                "</td>" +
                "<td>" +
                "<form action='/delete' method='get'>" +
                    "<input type='button' value='delete' class='button'>" +
                    "<input type='hidden' name='login' value='" + login + "'>" +
                "</form></td>";
    return buttons;
};

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
            console.log($(this).val());
        });
        var arr = JSON.stringify(musics);
    var data = {"login" : login, "password": password, "name": name, "email": email, "country": country, "city": city, "street": street, "house": house, "role": role, "musics": arr};
    $.ajax({
        type: "GET",
        data: data,
        url: "add",
        success: function () {
            $("#userstable").empty();
            getall("add");
        }
    });
}
