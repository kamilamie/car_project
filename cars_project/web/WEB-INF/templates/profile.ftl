<#ftl encoding="UTF-8">
<#include "header.ftl">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Личный кабинет</title>

    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
    <script type="application/javascript"
            src="/static/js/jquery-1.9.1.js"></script>
    <meta name="description" content="">
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta name="viewport" content="minimum-scale=1.0, width=device-width, maximum-scale=1, user-scalable=no"/>
    <link href='http://fonts.googleapis.com/css?family=Cabin:400,500,600,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Dancing+Script:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/owl.carousel.css">
    <link rel="stylesheet" href="/static/css/animations.css">
    <link rel="stylesheet" href="/static/css/font-awesome.min.css">
    <link rel="stylesheet" href="/static/css/jquery.flickr.css">
    <link rel="stylesheet" href="/static/css/prettyPhoto.css">
    <link rel="stylesheet" href="/static/css/main.css">
    <script async src="/static/js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
</head>
<style>
    body {
        background-image: url("/static/img/01.jpg");
    }

    div {
        background-color: midnightblue;
    }

    .frame {
        border: 3px solid white; /* Параметры рамки */
        box-shadow: 0 0 7px #000000; /* Параметры тени */
    }
</style>
<body>

<br>
<br>
<br>
<#if user??>
<div style="background-color: transparent; text-align: center; text-shadow: 2px 2px #000000">


    <h3>Личная информация:</h3>
    <#if user.photo_path??>
        <img src="${user.photo_path}" alt="Chania" width="300" class="frame">
    </#if>
    <p style="color: white">Имя: ${user.username}</p>
    <p style="color: white">Логин: ${user.login}</p>
    <#if user.age??>
        <p style="color: white">Возраст: ${user.age}</p>
    </#if>
    <p style="color: white">Водительский стаж: ${user.experience}</p>
</div>
</#if>
<#if curr_user??>
<div style="background-color: transparent; text-align: center; text-shadow: 2px 2px #000000">
    <h2 style="color: white">Добро пожаловать в Ваш личный кабинет</h2>
    <h5 style="color: white">Здесь вы можете отредактировать информацию о себе.</h5>
    <h5 style="color: white">Для навигации по сайту используйте верхнее меню.</h5>
    <h3>Личная информация:</h3>
    <#if curr_user.photo_path??>
        <img src="${curr_user.photo_path}" alt="Chania" width="300" class="frame">
    </#if>
    <p style="color: white">Имя: ${curr_user.username}</p>
    <p style="color: white">Логин: ${curr_user.login}</p>
    <#if curr_user.age??>
        <p style="color: white">Возраст: ${curr_user.age}</p>
    </#if>
    <p style="color: white">Водительский стаж: ${curr_user.experience}</p>


    <form method="post" action="profile" enctype="multipart/form-data">
        <input type="file" name="file" id="file" class="col-sm-3 photos">
        <button type="submit" name="changePhoto">Изменить аватарку</button><br>
        <button type="submit" name="edit">Редактировать</button>
        <button type="submit" name="logOut">Выйти</button>
    </form>

</div>
</#if>
</body>
</html>