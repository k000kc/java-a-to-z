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
$(document).ready(function () {
        $.getJSON("show", function (data) {
            var result = "<table>";
            $.each(data, function (index, users) {
                console.log(users.login);
                result += "<tr><td>" + users.login + "</tr><td>";

            });
            var usertable = $("#allusers");
            $(result).appendTo(usertable);
        });
});
