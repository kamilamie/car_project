<#ftl encoding="UTF-8">
<!DOCTYPE html>
<html>
<head>
    <title>Избранное</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="/static/js/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/static/css/catalog.css"/>
    <script type="text/javascript" src="/static/js/item_descr.js"></script>

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
    .font{
        font-family: "Roboto", sans-serif;
    }
</style>
<body>
<#include "header.ftl">
<br>
<br>
<br>
<div class="row font">
    <div class="container">
        <h1 align="center " id="title">Избранные автомобили: </h1>
        <#if favorites?has_content>
            <#list favorites as f >
        <div id="${f.id}">
            <div  class="media">

                <div class="media-left  media-top">
                    <img src="/static/img/01.jpg" class="media-object" style="width:90px; height: 95px;">
                </div>
                <div class="media-body">
                    <a href="/catalog/${f.id}"
                       class="text-success item-title">${f.model.producer.name} ${f.model.name} </a>
                    <p>Цена от: ${f.price}</p>
                </div>
                <div class="media-right">
                    <button class="btn btn-danger" onclick="deleteFavorite(${f.id})">
                        Удалить
                    </button>
                </div>
            </div>
            <hr color="white">
        </div>
            </#list>
        <#else >
        <h3 align="center " id="title">У Вас пока нет Избранных автомобилей </h3>
        </#if>


    </div>
</div>

</body>
<script>
    function deleteFavorite(fav_id) {

        $.ajax({
            url: "/ajax",
            type: "post",
            data: {
                "ajax": "removeFavorite",
                "fav_id": fav_id
            },
            success: function (msg) {

                document.getElementById(fav_id).remove();

            },
            error: function (msg) {
                alert("error");
            }
        });
    }
</script>
</html>


