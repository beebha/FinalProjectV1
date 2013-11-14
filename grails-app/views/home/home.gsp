<!DOCTYPE html>
<html>
<head>
    <g:render template='../includes/header'/>
</head>
<body onload="getAllHomeData()">
<g:javascript src="application.js"/>
<div data-role="page" id="homePage">
    <div data-theme="a" data-role="header">
        <h3>
            CSCI-56 Final Project - Surveys Galore
        </h3>
    </div>
    <div data-role="content">
        <h2>HOME</h2>

        <!-- My Surveys -->
        <ul data-role="listview" data-divider-theme="b" data-inset="true">
            <li data-role="list-divider" role="heading">
                My Created Surveys
            </li>
            <g:each in='${categories}' var='category'>
                <li data-theme="c">
                    <a href="#page1" data-transition="slide"> ${category}</a>
                </li>
            </g:each>
        </ul>

        <!-- All Surveys -->
        <ul data-role="listview" data-divider-theme="b" data-inset="true">
            <li data-role="list-divider" role="heading">
                All Surveys
            </li>
            <g:each in='${categories}' var='category'>
                <li data-theme="c">
                    <a href="#page1" data-transition="slide"> ${category}</a>
                </li>
            </g:each>
        </ul>

        <!-- Results of Surveys created by me -->
        <ul data-role="listview" data-divider-theme="b" data-inset="true">
            <li data-role="list-divider" role="heading">
                My Surveys Results
            </li>
            <g:each in='${categories}' var='category'>
                <li data-theme="c">
                    <a href="#page1" data-transition="slide"> ${category}</a>
                </li>
            </g:each>
        </ul>
        <center>
            <g:link controller="logout" action="logout">
                <input type="button" data-inline="true" value="Logout">
            </g:link>
        </center>
    </div>
</div>
</body>
</html>
