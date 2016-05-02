<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Gentallela Alela! | </title>

    <!-- Bootstrap core CSS -->

    <link href="<c:url value="/static/css/bootstrap.min.css"/>" rel="stylesheet">

    <link href="<c:url value="/static/fonts/css/font-awesome.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/css/animate.min.css"/>" rel="stylesheet">

    <!-- Custom styling plus plugins -->
    <link href="<c:url value="/static/css/custom.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/css/icheck/flat/green.css"/>" rel="stylesheet">


    <script src="<c:url value="/static/js/jquery.min.js"/>"></script>

    <!--[if lt IE 9]>
    <script src="../assets/js/ie8-responsive-file-warning.js"></script>
    <![endif]-->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body style="background:#F7F7F7;">

<div class="">
    <div id="wrapper">
        <div id="login" class="form">
            <section class="login_content">
                <form>
                    <h1>Reset password</h1>
                    <div>
                        <input type="text" class="form-control" placeholder="Username or Email" required="" />
                    </div>
                    <div>
                        <a class="btn btn-default submit" href="index.html">Send reset email</a>
                        <a class="reset_pass" href="#">Cancel</a>
                    </div>
                    <div class="clearfix"></div>
                    <div class="separator">
                        <p class="change_link">New to site?
                            <a href="<c:url value="/account/signup/"/>" class="to_register"> Create Account </a>
                        </p>
                        <div class="clearfix"></div>
                        <br />
                        <div>
                            <h1><i class="fa fa-paw" style="font-size: 26px;"></i> Gentelella Alela!</h1>

                            <p>©2015 All Rights Reserved. Gentelella Alela! is a Bootstrap 3 template. Privacy and Terms</p>
                        </div>
                    </div>
                </form>
                <!-- form -->
            </section>
            <!-- content -->
        </div>
    </div>
</div>

</body>

</html>
