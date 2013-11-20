<!DOCTYPE html>
<html>
<head>
    <g:render template='../includes/header'/>
</head>
<body>
<g:javascript src="application.js" />
<div data-role="page" id="forgotPage">
    <div data-theme="a" data-role="header">
        <h3>
            CSCI-56 Final Project - Surveys Galore
        </h3>
    </div>
    <div data-role="content">
        <h2>FORGOT PASSWORD</h2>
        <g:if test="${flash.message}">
            <div class="errorMsg">${flash.message}</div>
        </g:if>
        <g:hasErrors bean="${forgotInstance}">
            <ul class="errorMsg" role="alert">
                <g:eachError bean="${forgotInstance}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
        </g:hasErrors>
        <g:form controller="forgot" action="reset">
            <div data-role="fieldcontain">
                <label for="f_username">
                    Email
                </label>
                <input name="username" id="f_username" placeholder="" value="${forgotInstance?.username}" type="email" required>
            </div>
            <div data-role="fieldcontain">
                <label for="f_password">
                    New password
                </label>
                <input name="password" id="f_password" placeholder="" value="" type="password" required>
            </div>
            <center>
                <input type="submit" data-inline="true" value="Reset">
                <g:link uri="/">
                    <input type="button" data-inline="true" value="Back to Login">
                </g:link>
            </center>
            <h5>
                Forgot your password?<br>
                Enter your email and new password.<br>
                Your password will be reset.
            </h5>
        </g:form>
    </div>
</div>
</body>
</html>
