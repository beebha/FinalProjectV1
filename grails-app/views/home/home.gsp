<!DOCTYPE html>
<html>
<head>
    <g:render template='../includes/header'/>
</head>
<body>
<g:javascript src="application.js" />
<div data-role="page" id="homePage">
    <div data-theme="a" data-role="header">
        <h3>
            CSCI-56 Final Project - Surveys Galore
        </h3>
    </div>
    <div data-role="content">
        <h2>HOME</h2>
        <center>
            <g:link controller="logout" action="logout">
                <input type="button" data-inline="true" value="Logout">
            </g:link>
        </center>
    </div>
</div>
</body>
</html>
