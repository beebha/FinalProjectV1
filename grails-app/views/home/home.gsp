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
        <g:if test="${title == 'My Surveys'}">
        <div data-role="collapsible-set" data-theme="b" data-mini="true" data-content-theme="d" data-inset="false">
            <div data-role="collapsible">
                <h2>Completed Surveys</h2>
                <ul data-role="listview">
                    <g:each in='${completedCategoriesAndCounts}' var='completedSingleCategoryInfo'>
                        <li>
                            <a href="#page1" data-transition="slide">${completedSingleCategoryInfo.category}<span class="ui-li-count">${completedSingleCategoryInfo.count}</span></a>
                        </li>
                    </g:each>
                </ul>
            </div>
            <div data-role="collapsible">
                <h2>Incomplete Surveys</h2>
                <ul data-role="listview">
                    <g:each in='${incompleteCategoriesAndCounts}' var='inCompleteSingleCategoryInfo'>
                        <li>
                            <a href="#page1" data-transition="slide">${inCompleteSingleCategoryInfo.category}<span class="ui-li-count">${inCompleteSingleCategoryInfo.count}</span></a>
                        </li>
                    </g:each>
                </ul>
            </div>
            <div data-role="collapsible">
                <h2>Published Surveys</h2>
                <ul data-role="listview">
                    <g:each in='${activeCategoriesAndCounts}' var='activeSingleCategoryInfo'>
                        <li>
                            <a href="#page1" data-transition="slide">${activeSingleCategoryInfo.category}<span class="ui-li-count">${activeSingleCategoryInfo.count}</span></a>
                        </li>
                    </g:each>
                </ul>
            </div>
        </g:if>
        <g:if test="${title == 'All Surveys'}">
            <ul data-role="listview" data-mini="true" data-divider-theme="b" data-inset="true">
                <li data-role="list-divider" role="heading">
                    ${title}
                </li>
                <g:each in='${categoriesAndCounts}' var='singleCategoryInfo'>
                    <li data-theme="c">
                        <a href="#page1" data-transition="slide">${singleCategoryInfo.category}<span class="ui-li-count">${singleCategoryInfo.count}</span></a>
                    </li>
                </g:each>
            </ul>
        </g:if>
        <br>
        <center>
            <g:if test="${title == 'My Surveys'}">
                <g:link controller="survey" action="createSurvey">
                    <input type="button" value="Create Survey" data-icon="plus" data-iconpos="right" data-mini="true" data-inline="true">
                </g:link>
            </g:if>
            <g:if test="${title == 'All Surveys'}">
                <g:link controller="survey" action="takeSurvey">
                    <input type="button" value="Take Survey" data-icon="plus" data-iconpos="right" data-mini="true" data-inline="true">
                </g:link>
            </g:if>
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
