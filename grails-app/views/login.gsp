<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title></title>

    <!-- jQuery Mobile CSS -->
    <link rel="stylesheet" href="${resource(dir: 'css/jquery', file: 'jquery.mobile-1.3.2.css')}">

    <!-- jQuery and jQuery Mobile -->
    <script src="${resource(dir: 'js/jquery', file: 'jquery-1.9.1.js')}"></script>
    <script src="${resource(dir: 'js/jquery', file: 'jquery.mobile-1.3.2.js')}"></script>


</head>
<body>
<!-- Home -->
<div data-role="page" id="loginPage">
    <div data-theme="a" data-role="header">
        <h3>
            CSCI-56 Final Project
        </h3>
    </div>
    <div data-role="content">
        <h2>
            Surveys Galore
        </h2>
        <form action="">
            <div data-role="fieldcontain">
                <label for="username">
                    Email
                </label>
                <input name="" id="username" placeholder="" value="" type="email">
            </div>
            <div data-role="fieldcontain">
                <label for="password">
                    Password
                </label>
                <input name="" id="password" placeholder="" value="" type="password">
            </div>
            <center>
                <input type="submit" data-inline="true" value="Submit">
                <input type="submit" data-inline="true" value="Clear">
            </center>
            <div>
                <a href="" data-transition="fade">
                    Register
                </a>
            </div>
            <div>
                <a href="" data-transition="fade">
                    Forgot Password
                </a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
