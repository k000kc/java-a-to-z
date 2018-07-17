// $(document).ready(function () {
//     $('#userstable').on('click', '.update-user', function () {
//         $('#updateform').show();
//     });
//
//     $('#newbutton').click(function () {
//         var login = $('#user-login').val();
//         var password = $('#newpassword').val();
//         var name = $('#newname').val();
//         var email = $('#newemail').val();
//         var country = $('#newcountry').val();
//         var city = $('#newcity').val();
//         var street = $('#newstreet').val();
//         var house = $('#newhouse').val();
//         var role = $('#newroleslist').val();
//         var musics = [];
//         $('input:checkbox:checked').each(function () {
//             musics.push($(this).val());
//         });
//         var arr = JSON.stringify(musics);
//         var json = {"login" : login, "password": password, "name": name, "email": email, "country": country, "city": city, "street": street, "house": house, "role": role, "musics": arr};
//
//         $.ajax({
//             type: "POST",
//             data: json,
//             url: "update",
//             complete: function (data) {
//                 printTable(data);
//                 $('#updateform').hide();
//             }
//         });
//     })
// })