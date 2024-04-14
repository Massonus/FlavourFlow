<#macro page>

    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="shortcut icon" href="../../static/img/favicon.ico" type="image/x-icon">
        <title>Flow</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
              crossorigin="anonymous">

        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet">

        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0"/>

        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">

        <script src="https://kit.fontawesome.com/9deb3ecfc7.js" crossorigin="anonymous"></script>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>


        <script src="../../static/js/loginRegistration.js"></script>
        <script src="../../static/js/kitchenCategory.js"></script>
        <script src="../../static/js/companyCountry.js"></script>
        <script src="../../static/js/changeStyle.js"></script>
        <script src="../../static/js/basket.js"></script>
        <script src="../../static/js/wishes.js"></script>
        <script src="../../static/js/filterSearch.js"></script>
        <script src="../../static/js/order.js"></script>
        <script src="../../static/js/uploadFile.js"></script>
        <script src="../../static/js/product.js"></script>
        <script src="../../static/js/company.js"></script>
        <script src="../../static/js/user.js"></script>
        <script src="../../static/js/message.js"></script>
        <script src="../../static/js/rating.js"></script>
        <script src="../../static/js/token.js"></script>
        <script defer src="../../static/js/changeStyle.js"></script>

        <script>
            if (localStorage.getItem('theme') === "/css/dark-theme.css") {
                document.write('<link id="theme" rel="stylesheet" type="text/css" href="/css/dark-theme.css">');
            } else {
                document.write('<link id="theme" rel="stylesheet" type="text/css" href="/css/light-theme.css">');
            }
        </script>
        <link rel="stylesheet" type="text/css" href="/css/main.css">
        <link rel="stylesheet" type="text/css" href="/css/login-form.css">
        <link rel="stylesheet" type="text/css" href="/css/stars.css">
        <link rel="stylesheet" type="text/css" href="/css/file.css">
        <link rel="stylesheet" type="text/css" href="/css/error.css">

    </head>
    <body onload="setTheme(); addEventListeners(); changeBasketObjectsCount()">

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>

    <script src="https://code.jquery.com/jquery-3.6.1.min.js"
            integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
            crossorigin="anonymous"></script>

    <#include "navbar.ftlh">
    <div class="container mt-5">
        <#nested>
    </div>


    <div id="preloader">
        <div class="spinner-border" role="status">
            <span class="visually-hidden">Loading...</span>
        </div>
    </div>

    </body>

    <#include "footer.ftlh">
    </html>
</#macro>