function validatePassword() {

    var emailRegEx = new RegExp(/^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{8,64})$/);
    var password = $("#password1").val();

    if (emailRegEx.test(password) || password.length == 0) {
        $("#invalidPasswordMessage").html("");
        $("#passwordRules").hide();
    } else {
        $("#invalidPasswordMessage").html("Некорректный пароль!");
        $("#passwordRules").show();
    }


}

$(document).ready(function () {
    $("#password1").keyup(validatePassword);
});