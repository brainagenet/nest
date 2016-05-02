<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Log in</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="<c:url value="/static/adminlte/bootstrap/css/bootstrap.min.css"/>">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="<c:url value="/static/adminlte/dist/css/AdminLTE.min.css"/>">
    <!-- iCheck -->
    <link rel="stylesheet" href="<c:url value="/static/adminlte/plugins/iCheck/square/blue.css"/>">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <a href="<c:url value="/"/>"><b>Nest</b></a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">Reset password</p>

        <form id="passwordResetForm" name="passwordResetForm" action="<c:url value="/account/password/reset/"/>" method="post">
            <div class="form-group has-feedback">
                <input type="email" class="form-control" placeholder="Username or Email">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-6">
                </div>
                <!-- /.col -->
                <div class="col-xs-6">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">Send reset email</button>
                </div>
                <!-- /.col -->
            </div>
        </form>
    </div>
    <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 2.2.0 -->
<script src="<c:url value="/static/adminlte/plugins/jQuery/jQuery-2.2.0.min.js"/>"></script>
<!-- Bootstrap 3.3.6 -->
<script src="<c:url value="/static/adminlte/bootstrap/js/bootstrap.min.js"/>"></script>
<!-- iCheck -->
<script src="<c:url value="/static/adminlte/plugins/iCheck/icheck.min.js"/>"></script>
<script>
    $(function () {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
    });
</script>
</body>
</html>
