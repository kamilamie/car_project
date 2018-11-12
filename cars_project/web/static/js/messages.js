function func(elt) {

    while (elt && elt.tagName != "DIV")
        elt = elt.parentNode;
    var userId = null;
    if (elt) {
        for (var i = 0; i < elt.childNodes.length; i++) {
            if (elt.childNodes[i].className == "user_id") {
                userId = elt.childNodes[i].textContent.valueOf();
                break;
            }
        }
    }
    $(document).ready(function () {
        $('#myModal').modal('show');
    });
    document.getElementById("receiver").value = userId;
}

function filter() {
    var x = document.getElementById("select_user").value;
    if (x != 0) {
        $.ajax({
            type: 'get',
            url: '#?id=' + x,
            data: 'id=',
            // todo: это запрос на одного пользователя
            success: function (data) {

                $('.results').html(data);
            },
            fail: function (data) {
                alert('qqq');
                $('.results').html(data);
            }
        });
    } else {
        alert(x);
        $.ajax({
            type: 'get',
            url: '#',
            // todo: это запрос на всех пользователей
            success: function (data) {

                $('.results').html(data);
            },
            fail: function (data) {

                $('.results').html(data);
            }
        });
    }

}