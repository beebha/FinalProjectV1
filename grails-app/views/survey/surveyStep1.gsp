<!DOCTYPE html>
<html>
<head>
    <g:render template='../includes/header'/>
</head>
<body>
<g:javascript src="application.js"/>
<div data-role="page" id="surveyStep1Page">
    <g:render template='../includes/headerBarWithLogoutConfirmation'/>
    <div data-role="content">
        <h2>Create Survey (Step 1)</h2>
        <g:hasErrors bean="${surveyInstance}">
            <ul class="errorMsg" role="alert">
                <g:eachError bean="${surveyInstance}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
        </g:hasErrors>
        <g:form controller="survey" action="saveSurveyStep1">
            <div data-role="fieldcontain">
                <label for="surveyname">
                    Name
                </label>
                <input name="name" id="surveyname" placeholder="Survey Name" value="${surveyInstance?.name}"
                       type="text" data-mini="true" required>
            </div>
            <div data-role="fieldcontain">
                <label for="categorymenu">
                    Category
                </label>
                <select id="categorymenu" name="category" data-mini="true">
                    <g:each in='${categories}' var='singleCategory'>
                        <option value="${singleCategory}">${singleCategory}</option>
                    </g:each>
                </select>
            </div>
            <center>
                <input type="submit" value="Save & Continue" data-icon="arrow-r" data-iconpos="right" data-mini="true" data-inline="true">
                <g:link controller="home">
                    <input type="button" value="Cancel" data-icon="back" data-iconpos="right" data-mini="true" data-inline="true">
                </g:link>
            </center>
        </g:form>
        <br>
        <g:render template='../includes/footer'/>
    </div>
</div>
</body>
</html>
