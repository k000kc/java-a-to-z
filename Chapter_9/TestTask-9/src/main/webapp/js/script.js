$(document).ready(function () {
    $('#button').click(function () {
        var data = {"login": $("#login").val(), "password": $("#password").val()}
        $.ajax({
            type: "POST",
            data: data,
            url: 'signin',
            success: function () {
            }
        })
    });
})