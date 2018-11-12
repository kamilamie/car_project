<#ftl encoding="UTF-8">
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Создание</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="static/css/ad_edit.css" />
</head>
<body>

<div class="row">
    <div class="col-md-6 col-md-offset-4">
        <h2 class="col-sm-offset-2 col-sm-6" align="left">Добавить автомобиль</h2>

    </div>
</div>
<div class="row">
    <div class="col-sm-offset-2 ">
        <form class="form-horizontal" method="post" action="addCar" enctype="multipart/form-data">
            <div class="form-group">
                <label class="control-label col-sm-2">Класс: </label>
                <div class="col-sm-2">
                    <select name="class" class="form-control" required>
                        <#list classes as c>
                            <option value="${c.id}">${c.letter} ${c.name}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2">Производитель: </label>
                <div class="col-sm-2">
                    <select name="producer" class="form-control" required>
                        <#list producers as p >
                            <option value="${p.id}">${p.name}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2">Модель: </label>
                <div class="col-sm-1">
                    <input type="text" class="form-control" name="model" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2">Цвет: </label>
                <div class="col-sm-2">
                    <select name="color" class="form-control" required>
                        <option value="red">red</option>
                        <option value="grey">grey</option>
                        <option value="black">black</option>
                        <option value="blue">blue</option>
                        <option value="white">white</option>
                        <option value="green">green</option>
                        <option value="yellow">yellow</option>
                        <option value="no">-</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2">Коробка передач: </label>
                <div class="col-sm-2">
                    <select name="transmission" class="form-control" required>
                        <option value="manual">механическая</option>
                        <option value="automatic">автомат</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2">Мощность(л.с.): </label>
                <div class="col-sm-1">
                    <input type="number" class="form-control" name="horsepower" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2">Вместимость(чел.): </label>
                <div class="col-sm-1">
                    <input type="number" class="form-control" name="seats" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2">Цена от: </label>
                <div class="col-sm-1">
                    <input type="number" class="form-control" name="price" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2">Описание : </label>
                <div class="col-sm-3">
                    <textarea class="form-control" rows="5" cols="15" id="text-input" name="description" required></textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="pwd">Фото: </label>
                <input type="file" name="file" id="file" class="col-sm-3 photos" required>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-success col-sm-3"><h4>ДОБАВИТЬ</h4></button>
                </div>
            </div>
        </form>

    </div>
</div>

</body>
</html>