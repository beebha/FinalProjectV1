<!DOCTYPE html>
<html>
<head>
    <g:render template='../includes/header'/>
</head>
<body>
    <g:javascript src="application.js" />
    <div data-role="page" id="registerPage">
        <div data-theme="a" data-role="header">
            <h3>
                CSCI-56 Final Project - Surveys Galore
            </h3>
        </div>
        <div data-role="content">
            <h2>REGISTRATION</h2>
            <g:if test="${flash.message}">
                <div id="errorMsg" class="errorMsg">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${registerInstance}">
                <ul class="errorMsg" role="alert">
                    <g:eachError bean="${registerInstance}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                    </g:eachError>
                </ul>
            </g:hasErrors>
            <g:form controller="register" action="save">
                <div data-role="fieldcontain">
                    <label for="r_username">
                        Email
                    </label>
                    <input name="username" id="r_username" placeholder="" value="${registerInstance?.username}" type="email" required>
                </div>
                <div data-role="fieldcontain">
                    <label for="r_password">
                        Password
                    </label>
                    <input name="password" id="r_password" placeholder="" value="" type="password" required>
                </div>
                <center>
                    <input type="submit" data-inline="true" value="Register">
                    <input type="button" data-inline="true" value="Clear" onclick="clearButtonClicked('r_username','r_password');">
                </center>
            </g:form>
        </div>
    </div>
</body>
</html>
