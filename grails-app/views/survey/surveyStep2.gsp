<!DOCTYPE html>
<html>
<head>
    <g:render template='../includes/header'/>
</head>
<body>
<g:javascript src="application.js"/>
<script>

</script>
<div data-role="page" id="homePage">
    <div data-theme="a" data-role="header">
        <h3>
            CSCI-56 Final Project - Surveys Galore
        </h3>
    </div>
    <div data-role="content">
        <h2>Create Survey (Step 2)</h2>
        <g:hasErrors bean="${surveyInstance}">
            <ul class="errorMsg" role="alert">
                <g:eachError bean="${surveyInstance}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
        </g:hasErrors>
        <g:form controller="survey" action="saveSurveyStep2">
            <h3>Add Question</h3>
            <div data-role="fieldcontain">
                <label for="questionText">
                    Question Text
                </label>
                <input name="questionText" id="questionText" value="" type="text" data-mini="true" required>
            </div>
            <div data-role="fieldcontain">
                <label for="questionTypeMenu">
                    Question Type
                </label>
                <select id="questionTypeMenu" name="type" data-mini="true" onchange='questionTypeChange(${qnTypesJSON});'>
                    <g:each in='${qnTypes}' var='singleQnType'>
                        <option value="${singleQnType}">${singleQnType}</option>
                    </g:each>
                </select>
            </div>
            <div id="answerSection"></div>
            <br>
            <center>
                <input type="submit" value="Save & Complete Survey" data-icon="check" data-iconpos="right" data-mini="true" data-inline="true">
                <input type="submit" value="Save & Add Next Question" data-icon="arrow-r" data-iconpos="right" data-mini="true" data-inline="true">
                <g:render template='../includes/confirmLogout'/>
                <a id="logoutBtn" href="#popupDialog" data-rel="popup" data-role="button" data-icon="gear" data-iconpos="right" data-mini="true" data-inline="true" data-transition="pop">Logout</a>
            </center>
        </g:form>
        <br>
        <g:render template='../includes/footer'/>
    </div>
</div>
</body>
</html>
