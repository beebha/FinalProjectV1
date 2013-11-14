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
            <g:if test="${flash.message}">
                <center><div class="errorMsg">${flash.message}</div></center>
            </g:if>
            <g:form controller="login" action="authenticate">
                <div data-role="fieldcontain">
                    <label for="username">
                        Email
                    </label>
                    <input name="j_username" id="username" placeholder="" value="" type="email">
                </div>
                <div data-role="fieldcontain">
                    <label for="password">
                        Password
                    </label>
                    <input name="j_password" id="password" placeholder="" value="" type="password">
                </div>
                <center>
                    <input type="submit" data-inline="true" value="Submit">
                    <input type="button" data-inline="true" value="Clear" onclick="clearButtonClicked();">
                </center>
                <div>
                    <g:link controller="register">
                        Register
                    </g:link>
                </div>
                <div>
                    <g:link mapping="forgotPassword">
                        Forgot Password
                    </g:link>
                </div>
            </g:form>
        </div>
    </div>
</body>
</html>
