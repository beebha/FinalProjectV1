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
        <h2>${title}</h2>

        <!-- My Surveys -->
        <ul data-role="listview" data-divider-theme="b" data-inset="true">
            <li data-role="list-divider" role="heading">
                ${title}
            </li>
            <g:each in='${categoriesAndCounts}' var='singleCategoryInfo'>
                <li data-theme="c">
                    <a href="#page1" data-transition="slide">${singleCategoryInfo.category}<span class="ui-li-count">${singleCategoryInfo.count}</span></a>
                </li>
            </g:each>
        </ul>
        <center>
            <g:if test="${title == 'My Surveys'}">
                <g:link controller="survey" action="createView">
                    <input type="button" value="Create Survey" data-icon="plus" data-iconpos="right" data-mini="true" data-inline="true">
                </g:link>
            </g:if>
            <g:if test="${title == 'All Surveys'}">
                <g:link controller="survey" action="takeView">
                    <input type="button" value="Take Survey" data-icon="plus" data-iconpos="right" data-mini="true" data-inline="true">
                </g:link>
            </g:if>
            <g:link controller="logout" action="logout">
                <input type="button" value="Logout" data-icon="gear" data-iconpos="right" data-mini="true" data-inline="true">
            </g:link>
        </center>
        <br>
        <div data-role="footer" data-id="persistent_navbar" data-position="fixed">
            <div data-role="navbar" data-iconpos="bottom">
                <ul>
                    <li><a id="mysurveys" href="../home/mysurveyindex" data-icon="home">My Surveys</a></li>
                    <li><a id="allsurveys" href="../home/allsurveyindex" data-icon="home">All Surveys</a></li>
                    <li><a id="results" href="#" data-icon="grid">Results</a></li>
                    <li><a id="favorites" href="#" data-icon="star">Favorites</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
