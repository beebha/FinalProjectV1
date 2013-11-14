<!DOCTYPE html>
<html>
<head>
    <g:render template='../includes/header'/>
</head>
<body>

    <g:javascript src="application.js" />

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
            <g:render template='../customLogin/loginContent'/>
        </div>
    </div>
</body>
</html>
