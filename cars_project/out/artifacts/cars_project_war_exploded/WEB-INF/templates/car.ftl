<#ftl encoding="UTF-8">
<#include "header.ftl">
<!DOCTYPE html>

<html lang="en">
<head>
    <title>${car.model.producer.name} ${car.model.name}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/static/css/house_page.css"/>
    <script type="application/javascript"
            src="/static/js/jquery-1.9.1.js"></script>
    <link rel="stylesheet" type="text/css" href="/static/css/messages.css"/>
    <script type="text/javascript" src="/static/js/messages.js"></script>
</head>
<style>
    @import url(https://fonts.googleapis.com/css?family=Roboto:300);

    .page {
        width: 360px;
        padding: 8% 0 0;
        margin: auto;
    }

    .form {
        position: relative;
        z-index: 1;
        background: #FFFFFF;
        max-width: 660px;
        margin: 0 auto 100px;
        padding: 45px;
        text-align: center;
        box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
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
    .font{
        font-family: "Roboto", sans-serif;
    }
</style>
<body>

<br>
<br>
<br>

<div class="container" style="text-align: center">
    <div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="5000">

        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <#if car.photo_path??>
                    <img src="${car.photo_path}" alt="Chania" width="800" height="600">
                </#if>

            </div>


        </div>

    </div>
</div>
<div class="row font">

    <div class="form container" style="text-align: left;">
        <h1>Производитель : ${car.model.producer.name}</h1>
        <h1>Модель : ${car.model.producer.name} ${car.model.getName()} </h1>
        <h1>Класс : ${car.getCarClass().letter} ${car.getCarClass().name}</h1>
        <h1>Коробка передач : ${car.transmission}</h1>
        <h1>Вместимость(чел.) : ${car.seats}</h1>
        <h1>Мощность(л.с.) : ${car.horsepower} </h1>
        <h1>Цвет : ${car.color}</h1>
        <h1>Цена от : ${car.price}</h1>
        <#if car.description??>
        <div class="spoiler disabled">
            <h3 class=" descr-button">Описание</h3>
            <div class="descr-text">
                ${car.description}
            </div>
        </#if></div>
        <div style="margin-top: 20px">
            <button onclick="addToFavorites()" class="btn btn-success">Добавить в избранное</button>
        </div>

    </div>




    <div class="container">
        <div class="title"><span class="title-text">Комментарии</span>
            <textarea class="form-control" rows="5" id="text-input" maxlength="160"></textarea>
            <button class="btn btn-success" id="comment" type="submit" onclick="addComment()">Оставить комментарий
            </button>
        </div>

        <div id="list">
        <#list comments as c>
            <div id="${c.id}" class="media">
                <div class="media-body">
                    <#if user??>
                        <#if c.user.id = user.id>
                        <div align="right">
                            <button class="btn btn-danger" onclick="deleteComment(${c.id})">
                                Удалить
                            </button>
                        </div>
                        </#if>
                    </#if>
                    <a href="/profile/${c.user.id}"
                       class="media-heading text-success">От: ${c.getUser().getUsername()}</a>
                    <p>${c.getDate()}</p>
                    <p>${c.getText()}</p>
                </div>
            </div>
            <br>
        </#list>
        </div>
    </div>
</div>

</body>
<script>
    function addToFavorites() {
        $.ajax({
            url: "/ajax",
            type: "post",
            data: {
                "ajax": "addToFavorites",
                "car_id": ${car.id}
            },
            success: function (msg) {

                if (msg.result === "added") {
                    alert("Добавлено в Избранное");
                }
                else if (msg.result === "already") {
                    alert("Уже есть в Избранном");
                } else if (msg.result === "null_user") {
                    alert("Избранное доступно только для авторизованных пользователей!")
                }

            },
            error: function (msg) {
                alert("error");
            }
        });
    }

    function addComment() {
        var text = $("#text-input");
        if (text.val().length > 0) {
            $.ajax({
                url: "/ajax",
                type: "post",
                data: {
                    "ajax": "addComment",
                    "text": text.val(),
                    "car_id": ${car.id}
                },
                success: function (msg) {

                    if (msg.result === "success") {
                        var list = $("#list");
                        list.prepend("<div id=\"" + msg.commentId + "\" class=\"media\">\n" +
                                "                <div class=\"media-body\">\n" +
                                "<div align=\"right\">\n" +
                                "                            <button class=\"btn btn-danger\" onclick=\"deleteComment(" + msg.commentId + ")\">\n" +
                                "                                Удалить\n" +
                                "                            </button>\n" +
                                "                        </div>" +
                                "                    <a href=\"/profile/" + msg.userId + " \" class=\"media-heading text-success\">От: " + msg.username + "</a>\n" +
                                "<p>" + msg.date + "</p>" +
                                "                    <p>" + text.val() + "</p>\n" +
                                "                </div>\n" +
                                "            </div><br>");

                    } else if (msg.result === "null_user") {
                        alert("Оставлять комментарии могут только авторизованные пользователи")
                    }
                    text.val("");

                },
                error: function (msg) {
                    alert("error");
                }
            });

        }
        else {
            alert("Вы ничего не ввели");
        }
    }

    function deleteComment(comment_id) {
        $.ajax({

            url: "/ajax",
            type: "post",
            data: {
                "ajax": "deleteComment",
                "comment_id": comment_id
            },
            success: function (msg) {
                document.getElementById(comment_id).remove();

            },
            error: function (msg) {
                alert("error");
            }
        });
    }


</script>
</html>