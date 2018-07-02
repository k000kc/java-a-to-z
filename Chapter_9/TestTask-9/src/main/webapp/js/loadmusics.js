$(document).ready(function getmusics() {
    $.getJSON("loadmusics", function (data) {
        var result = "<br>";
        $.each(data, function (index, musics) {
            result += "<input type='checkbox' value='" + musics.id + "'>" + musics.musicType + "<br>";
        });
        var musicslist = $("#musiclist");
        $(result).appendTo(musicslist);
    });
});