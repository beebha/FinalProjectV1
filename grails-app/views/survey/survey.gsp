<!DOCTYPE html>
<html>
<head>
    <g:render template='../includes/header'/>
</head>
<body>
<g:javascript src="application.js"/>
<div data-role="page" id="homePage">
    <div data-theme="a" data-role="header">
        <h3>
            CSCI-56 Final Project - Surveys Galore
        </h3>
    </div>
    <div data-role="content">
        <h2>Create Survey</h2>
        <div data-role="fieldcontain">
            <label for="surveyname">
                Name
            </label>
            <input name="name" id="surveyname" placeholder="Survey Name" value=""
                   type="text" data-mini="true">
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
            <g:link controller="logout" action="logout">
                <input type="button" value="Logout" data-icon="gear" data-iconpos="right" data-mini="true" data-inline="true">
            </g:link>
        </center>
        <br>
        <g:render template='../includes/footer'/>
    </div>
</div>
</body>
</html>
