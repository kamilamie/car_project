function validateEmail() {

    var emailRegEx = new RegExp(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/);
    var email = $("#email").val();

    if(emailRegEx.test(email) || email.length == 0 )
        $("#invalidEmailMessage").html("");
    else
        $("#invalidEmailMessage").html("Некорректный email!");


}

$(document).ready(function () {
    $("#email").keyup(validateEmail);
});