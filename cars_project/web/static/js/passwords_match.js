function checkPasswordMatch() {

    var password = $("#password1").val();
    var confirmPassword = $("#password2").val();

    if (password != confirmPassword)
        $("#divCheckPasswordMatch").html("Пароли не совпадают!");
    else
        $("#divCheckPasswordMatch").html("");
}


$(document).ready(function () {
    $("#password2").keyup(checkPasswordMatch);
});