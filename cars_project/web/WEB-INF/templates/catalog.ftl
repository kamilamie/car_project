<#ftl encoding="UTF-8">
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Каталог</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="static/js/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="static/css/catalog.css"/>
    <script type="text/javascript" src="static/js/item_descr.js"></script>

</head>
<style>
    @import url(https://fonts.googleapis.com/css?family=Roboto:300);

    .login-page {
        width: 360px;
        padding: 8% 0 0;
        margin: auto;
    }

    .form {
        position: relative;
        z-index: 1;
        background: #FFFFFF;
        max-width: 360px;
        margin: 0 auto 100px;
        padding: 45px;
        text-align: center;
        box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
    }

    .form input {
        font-family: "Roboto", sans-serif;
        outline: 0;
        background: #f2f2f2;
        width: 100%;
        border: 0;
        margin: 0 0 15px;
        padding: 15px;
        box-sizing: border-box;
        font-size: 14px;
    }

    .form button {
        font-family: "Roboto", sans-serif;
        text-transform: uppercase;
        outline: 0;
        background: #4CAF50;
        width: 100%;
        border: 0;
        padding: 15px;
        color: #FFFFFF;
        font-size: 14px;
        -webkit-transition: all 0.3 ease;
        transition: all 0.3 ease;
        cursor: pointer;
    }

    .form button:hover, .form button:active, .form button:focus {
        background: #2aaad9;
    }

    .form .message {
        margin: 15px 0 0;
        color: #b3b3b3;
        font-size: 12px;
    }

    .form .message a {
        color: #4CAF50;
        text-decoration: none;
    }

    .form .register-form {
        display: none;
    }

    body {
        background: #2aaad9; /* fallback for old browsers */
        background: -webkit-linear-gradient(right, #2aaad9, #8DC26F);
        background: -moz-linear-gradient(right, #2aaad9, #8DC26F);
        background: -o-linear-gradient(right, #2aaad9, #8DC26F);
        background: linear-gradient(to left, #2aaad9, #8DC26F);
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
    }

    .font {
        font-family: "Roboto", sans-serif;
    }
</style>
<body>
<#include "header.ftl">

<div style="margin-top: 50px" class="row font">

    <div class="container">
        <div class="search-container" style="margin-top: 30px">
            <form id="search-form">
                <input type="text" placeholder="Искать по модели или производителю" id="search" oninput="printTips()"
                       name="search">
            </form>
        </div>
        <div class="search-container">
            <form>
                <div>
                    <label>Класс: </label>
                    <div>
                        <select id="class">
                            <option disabled selected value="select">select</option>
                        <#list classes as c>
                            <option value="${c.id}">${c.letter} ${c.name}</option>
                        </#list>
                        </select>
                    </div>
                </div>
                <div>
                    <label>Производитель: </label>
                    <div>
                        <select id="producer">
                            <option disabled selected value> select</option>
                        <#list producers as p >
                            <option value="${p.id}">${p.name}</option>
                        </#list>
                        </select>
                    </div>
                </div>

                <div>
                    <label>Коробка передач: </label>
                    <div>
                        <select id="transmission">
                            <option disabled selected value> select</option>
                            <option value="manual">механическая</option>
                            <option value="automatic">автомат</option>
                        </select>
                    </div>
                </div>

                <div>
                    <label>Минимальная мощность(л.с.): </label>
                    <div>
                        <input type="number" id="horsepower">
                    </div>
                </div>
                <div>
                    <label>Вместимость минимум ..(чел.): </label>
                    <div>
                        <input type="number" id="seats">
                    </div>
                </div>
                <div>
                    <label>Цена от: </label>
                    <div>
                        <input type="number" id="price">
                    </div>
                </div>

            </form>
            <div>
                <button type="submit" onclick="sort()">Сортировать</button>
            </div>
            <div>
                <button type="submit" onclick="clean()">Очистить</button>
            </div>
        </div>

        <br>
        <div id="cars-list">
            <#list cars as c>
                <div class="media">
                    <div class="media-left media-top">
                        <img src="${c.photo_path}" class="media-object" style="width:90px; height: 95px;">
                    </div>
                    <div class="media-body" style="padding: 15px">
                        <a href="/catalog/${c.getId()}"
                           class="text-success item-title">${c.getModel().getProducer().getName()} ${c.getModel().getName()}</a>
                        <p>Мощность : ${c.horsepower} л.с.</p>
                        <p>Цвет : ${c.color}</p>
                        <p>Цена : ${c.getPrice()}</p>
                    </div>
                </div>
                <hr color="white">
            </#list>
        </div>

    </div>
</div>

</body>
<script>
    function clean() {
        var list = $("#cars-list");
        var val = 'select';
        $('#class option:contains(' + val + ')').prop({selected: true});
        $('#producer option:contains(' + val + ')').prop({selected: true});
        $('#transmission option:contains(' + val + ')').prop({selected: true});
        $("#horsepower").val('');
        $("#seats").val('');
        $("#price").val('');
        list.html("");
        list.append("<#list cars as c>\n"+
"                <div class=\"media\">\n" +
"                    <div class=\"media-left media-top\">\n" +
"                        <img src=\"${c.photo_path}\" class=\"media-object\" style=\"width:90px; height: 95px;\">\n" +
"                    </div>\n" +
"                    <div class=\"media-body\" style=\"padding: 15px\">\n" +
"                        <a href=\"/catalog/${c.getId()}\"\n" +
"                           class=\"text-success item-title\">${c.getModel().getProducer().getName()} ${c.getModel().getName()}</a>\n" +
"                        <p>Мощность : ${c.horsepower} л.с.</p>\n" +
"                        <p>Цвет : ${c.color}</p>\n" +
"                        <p>Цена : ${c.getPrice()}</p>\n" +
"                    </div>\n" +
"                </div>\n" +
"                <hr color=\"white\">\n" +
"            </#list>");
    }

    function sort() {
        var carClass = $("#class");
        var producer = $("#producer");
        var transmission = $("#transmission");
        var horsepower = $("#horsepower");
        var seats = $("#seats");
        var price = $("#price");
        if (carClass.val() != null || producer.val() != null || transmission.val() != null || horsepower.val().length > 0 ||
                seats.val().length > 0 || price.val().length > 0) {

            $.ajax({
                url: "/ajax",
                type: "post",
                data: {
                    "ajax": "sortCars",
                    "carClass": carClass.val(),
                    "producer": producer.val(),
                    "transmission": transmission.val(),
                    "horsepower": horsepower.val(),
                    "seats": seats.val(),
                    "price": price.val()
                },
                success: function (msg) {
                    var list = $("#cars-list");
                    list.html("");
                    if (msg.cars === undefined) {
                        list.append("<div style='color: white;'>No suitable cars</div>");
                    }
                    if (msg.cars.length > 0) {
                        for (var i = 0; i < msg.cars.length; i++) {
                            list.prepend("<div class=\"media\">\n" +
                                    "                    <div class=\"media-left media-top\">\n" +
                                    "                        <img src=\"" + msg.cars[i].path + "\" class=\"media-object\" style=\"width:90px; height: 95px;\">\n" +
                                    "                    </div>" +
                                    "<div class=\"media-body\" style=\"padding: 15px\">\n" +
                                    "                        <a href=\"/catalog/" + msg.cars[i].id + "\"\n" +
                                    "                           class=\"text-success item-title\">" + msg.cars[i].model + "</a>\n" +
                                    "                        <p>Мощность : " + msg.cars[i].horsepower + " л.с.</p>\n" +
                                    "                        <p>Цвет : " + msg.cars[i].color + "</p>\n" +
                                    "                        <p>Цена : " + msg.cars[i].price + "</p>\n" +
                                    "                    </div>\n" +
                                    "                </div>\n" +
                                    "                <hr color=\"white\">")
                        }
                    }

                },
                error: function (msg) {
                    alert("error");
                }
            });
        } else {
            alert("empty request!");
        }
    }

    function printTips() {
        var text = $("#search");
        var list = $("#cars-list");
        if (text.val().length > 0) {
            clean();
            $.ajax({
                url: "/ajax",
                type: "post",
                data: {
                    "ajax": "getCarsList",
                    "text": text.val()
                },
                success: function (msg) {
                    list.html("");
                    if (msg.cars.length>0) {

                        for (var i = 0; i < msg.cars.length; i++) {
                            list.prepend("<div class=\"media\">\n" +
                                    "                    <div class=\"media-left media-top\">\n" +
                                    "                        <img src=\"" + msg.cars[i].path + "\" class=\"media-object\" style=\"width:90px; height: 95px;\">\n" +
                                    "                    </div>" +
                                    "<div class=\"media-body\" style=\"padding: 15px\">\n" +
                                    "                        <a href=\"/catalog/" + msg.cars[i].id + "\"\n" +
                                    "                           class=\"text-success item-title\">" + msg.cars[i].model + "</a>\n" +
                                    "                        <p>Мощность : " + msg.cars[i].horsepower + " л.с.</p>\n" +
                                    "                        <p>Цвет : " + msg.cars[i].color + "</p>\n" +
                                    "                        <p>Цена : " + msg.cars[i].price + "</p>\n" +
                                    "                    </div>\n" +
                                    "                </div>\n" +
                                    "                <hr color=\"white\">")
                        }
                    } else {
                        list.append("<div style='color: white;'>No suitable cars</div>")
                    }

                },
                error: function (msg) {
                    alert("error");
                }
            });

        } else {
            clean();
        }

    }
</script>
</html>