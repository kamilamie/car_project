function del(elt) {
    var elt2 = elt.parentNode;
    for (var i = 0; i < elt2.childNodes.length; i++) {

        if (elt2.childNodes[i].className == "item_id") {

            itemId = elt2.childNodes[i].textContent.valueOf();
            break;
        }
    }

    alert("itemId: " + itemId);
    while (elt.className != "media") {
        elt = elt.parentNode;
    }
    $.ajax({
        type: 'get',
        url: '#',
        data: {"id" : itemId},
        success: function (data) {
            elt.remove();
            alert("deleted");
        }

    });


}