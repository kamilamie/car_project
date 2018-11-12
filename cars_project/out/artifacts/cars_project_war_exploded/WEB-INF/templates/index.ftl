<#ftl encoding="UTF-8">

<!DOCTYPE html>
<!--[if lte IE 9]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 9]><!-->
<html class="no-js">
<head>
    <meta charset="utf-8">
    <title>Главная страница</title>
    <meta name="description" content="">
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta name="viewport" content="minimum-scale=1.0, width=device-width, maximum-scale=1, user-scalable=no" />
    <link href='http://fonts.googleapis.com/css?family=Cabin:400,500,600,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Dancing+Script:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/static/css/owl.carousel.css">
    <link rel="stylesheet" type="text/css" href="/static/css/animations.css">
    <link rel="stylesheet" type="text/css" href="/static/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/static/css/jquery.flickr.css">
    <link rel="stylesheet" type="text/css" href="/static/css/prettyPhoto.css">
    <link rel="stylesheet" href="/static/css/main.css">
    <script async src="/static/js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
</head>
<body class="eschool" data-spy="scroll" data-target="#navbar" data-offset="70">
<div class="navbar-custom">
    <div class="container">
        <a href="index.html" class="logo"><img src="img/logo.png" alt=""></a>
        <nav id="navbar">
            <a href="#" class="nav-triger"><span class="fa fa-navicon"></span></a>
            <ul class="main-menu nav">
                <li><a href="#section0">Вступление</a></li>
                <li><a href="#section1">О нас</a></li>
                <li><a href="#section2">Авто</a></li>
                <li><a href="/catalog">Каталог</a></li>
            </ul>
        </nav>

    </div>
</div>
<section id="section0" class="header">
    <div class="container">
        <div class="headerInner">
            <h2>Приветствуем на сайте по подбору автомобилей</h2>
            <p>    Здесь, пройдя простую регистрацию, Вы сможете воспользоваться системой фильтров и тестов для подбора интересных для Вас автомобилей. Не волнуйтесь, это не займет много времени.</p>
            <div class="row mt-30">

                <div class="col-md-4 col-sm-6">
                    <#if user??>
                        <form>
                            <button class="btn btn-default btn-head mt-20 mb-50"><a href="/profile">Личный кабинет</a></button>
                        <#if admin??>
                            <button class="btn btn-default btn-head mt-20 mb-50"><a href="/addCar">Добавить машину</a></button>
                        </#if>
                        </form>
                    <#else>
                        <form>
                            <button class="btn btn-default btn-head mt-20 mb-50"><a href="/signup">Зарегистрироваться</a></button>
                            <button class="btn btn-default btn-head mt-20 mb-50"><a href="/login">Войти</a></button>
                        </form>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</section>
<section id="section1" class="our-courses mt-30 mb-50">
    <div class="container">
        <div class="text-center mb-50 head">
            <h2 class="mb-25">О <span>НАС</span></h2>
            <p>Нашей задачей является создание удобного сайта с возможностью простого, удобного и доступного поиска автомобилей различных марок</p>
        </div>
        <div class="row courses top">
            <div class="col-sm-4 course text-bold clearfix">
                <figure class="pull-left mt-10"><i style="font-size:80px; color:#5A9BD1" class="fa fa-check-circle" aria-hidden="true"></i></figure>
                <div class="info">
                    <h5>Преимущество №1</h5>
                    <p>Быстрая навигация по сайту, минималистичный и понятный дизайн</p>
                </div>
            </div>
            <div class="col-sm-4 course text-bold clearfix">
                <figure class="pull-left mt-10"><i style="font-size:80px; color:#FFC000" class="fa fa-check-circle" aria-hidden="true"></i></figure>
                <div class="info">
                    <h5>Преимущество №2</h5>
                    <p>Удобный способ подбора интересных автомобилей с помощью теста</p>
                </div>
            </div>
            <div class="col-sm-4 course text-bold clearfix">
                <figure class="pull-left mt-10"><i style="font-size:80px; color:#EE6555" class="fa fa-check-circle" aria-hidden="true"></i></figure>
                <div class="info">
                    <h5>Преимущество №3</h5>
                    <p>Возможность общения и обмена опытом с другими пользователями сайта</p>
                </div>
            </div>

        </div>
    </div>
</section>

<section id="section2" class="our-courses parallax parallax_one" data-stellar-background-ratio="0.5" style="background-position: center bottom">
    <div class="overlay"></div>
    <div class="parallax_inner">
        <div class="container">
            <div class="text-center mb-50 head">
                <h2 class="mb-25" style="color:#fff">Наши <span style="color:#ffc000">автомобили</span></h2>
                <p>Косвенная реклама порождена временем</p>
            </div>
            <div class="row courses">
                <div class="col-md-4 col-sm-6 course text-bold clearfix">
                    <div class="course_inner">
                        <figure class="pull-left mt-10"><img alt="" style="height: 200px;" src="${car1.photo_path}"></figure>
                        <div class="info">
                            <h5 style="color:#fff">${car1.model.producer.name} ${car1.model.name}</h5>
                            <p>От ${car1.price} рублей</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-sm-6 course text-bold clearfix">
                    <div class="course_inner">
                        <figure class="pull-left mt-10"><img alt="" style="height: 200px;" src="${car2.photo_path}"></figure>
                        <div class="info">
                            <h5 style="color:#fff">${car2.model.producer.name} ${car2.model.name}</h5>
                            <p>От ${car2.price} рублей</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-sm-6 course text-bold clearfix">
                    <div class="course_inner">
                        <figure class="pull-left mt-10"><img alt="" style="height:200px;" src="${car3.photo_path}"></figure>
                        <div class="info">
                            <h5 style="color:#fff">${car3.model.producer.name} ${car3.model.name}</h5>
                            <p>От ${car3.price} рублей</p>
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>
</section>
<footer class="text-center color-white text-bold">
    <span>Семестровая работа</span>
</footer>
<!--===============================
Script Source
=================================-->

<script src="/static/js/jquery.js"></script>
<script src="/static/js/jquery.stellar.min.js"></script>
<script src="/static/js/owl.carousel.min.js"></script>
<script src="/static/js/jquery.easing-1.3.pack.js"></script>
<script src="/static/js/jflickrfeed.min.js"></script>
<script src="/static/js/jquery.prettyPhoto.js"></script>
<script src="/static/js/bootstrap-scrollspy.min.js"></script>
<script src="/static/js/main.js"></script>

</body>
</html>