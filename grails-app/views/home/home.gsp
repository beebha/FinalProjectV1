<!DOCTYPE html>
<html>
<head>
    <g:render template='../includes/header'/>
</head>
<body>
<g:javascript src="application.js"/>
<div data-role="page" id="homePage" data-add-back-btn="false">
    <g:render template='../includes/headerBar'/>
    <div data-role="content">
        <h2>${title}</h2>
        <g:if test="${flash.message}">
            <div class="errorMsg">${flash.message}</div>
        </g:if>
        <g:if test="${title == 'My Surveys'}">
        <div data-role="collapsible-set" data-theme="b" data-mini="true" data-content-theme="d" data-inset="false">
            <div data-role="collapsible">
                <h2>Completed Surveys</h2>
                <ul data-role="listview">
                    <g:each in='${completedCategoriesAndCounts}' var='completedSingleCategoryInfo'>
                        <li>
                            <div>${completedSingleCategoryInfo.category}</div><div class="ui-li-count">${completedSingleCategoryInfo.count}</div>
                            <g:if test="${completedSingleCategoryInfo.count > 0}">
                                <ul>
                                    <g:each in='${completedSingleCategoryInfo?.surveys}' var='completedSingleSurvey'>
                                        <li>
                                            <g:link controller="survey" action="viewSurvey" params="[surveyState:'complete', surveyID: completedSingleSurvey.id]">
                                                ${completedSingleSurvey?.name}
                                            </g:link>
                                        </li>
                                    </g:each>
                                </ul>
                            </g:if>
                        </li>
                    </g:each>
                </ul>
            </div>
            <div data-role="collapsible">
                <h2>Incomplete Surveys</h2>
                <ul data-role="listview">
                    <g:each in='${incompleteCategoriesAndCounts}' var='inCompleteSingleCategoryInfo'>
                        <li>
                            <div>${inCompleteSingleCategoryInfo.category}</div><div class="ui-li-count">${inCompleteSingleCategoryInfo.count}</div>
                            <g:if test="${inCompleteSingleCategoryInfo.count > 0}">
                                <ul>
                                    <g:each in='${inCompleteSingleCategoryInfo?.surveys}' var='incompleteSingleSurvey'>
                                        <li>
                                            <g:link controller="survey" action="viewSurvey" params="[surveyState:'incomplete', surveyID: incompleteSingleSurvey.id]">
                                                ${incompleteSingleSurvey?.name}
                                            </g:link>
                                        </li>
                                    </g:each>
                                </ul>
                            </g:if>
                        </li>
                    </g:each>
                </ul>
            </div>
            <div data-role="collapsible">
                <h2>Published Surveys</h2>
                <ul data-role="listview">
                    <g:each in='${activeCategoriesAndCounts}' var='activeSingleCategoryInfo'>
                        <li>
                            <div>${activeSingleCategoryInfo.category}</div><div class="ui-li-count">${activeSingleCategoryInfo.count}</div>
                            <g:if test="${activeSingleCategoryInfo.count > 0}">
                                <ul>
                                    <g:each in='${activeSingleCategoryInfo?.surveys}' var='activeSingleSurvey'>
                                        <li>
                                            <g:link controller="survey" action="viewSurvey" params="[surveyState:'active', surveyID: activeSingleSurvey.id]">
                                                ${activeSingleSurvey?.name}
                                            </g:link>
                                        </li>
                                    </g:each>
                                </ul>
                            </g:if>
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
                        <div>${singleCategoryInfo.category}</div><div class="ui-li-count">${singleCategoryInfo.count}</div>
                        <g:if test="${singleCategoryInfo.count > 0}">
                            <ul>
                                <g:each in='${singleCategoryInfo?.surveys}' var='singleSurvey'>
                                    <li>
                                        <g:link controller="survey" action="takeSurvey" params="[surveyID: singleSurvey.id]">
                                            ${singleSurvey?.name}
                                        </g:link>
                                    </li>
                                </g:each>
                            </ul>
                        </g:if>
                    </li>
                </g:each>
            </ul>
        </g:if>
        <g:if test="${title == 'All Results'}">
            <ul data-role="listview" data-mini="true" data-divider-theme="b" data-inset="true">
                <li data-role="list-divider" role="heading">
                    ${title}
                </li>
                <g:each in='${allResultsCategoriesAndCounts}' var='resultCategoryInfo'>
                    <li data-theme="c">
                        <div>${resultCategoryInfo.category}</div><div class="ui-li-count">${resultCategoryInfo.count}</div>
                        <g:if test="${resultCategoryInfo.count > 0}">
                            <ul>
                                <g:each in='${resultCategoryInfo?.surveyResults}' var='resultSurvey'>
                                    <li>
                                        <g:link controller="surveyResult" action="showSurveyResults" params="[surveyResultID: resultSurvey?.id]">
                                            ${resultSurvey?.surveyTaker.username} - <g:formatDate date="${resultSurvey?.dateCreated}" formatName="default.date.format"/>
                                        </g:link>
                                    </li>
                                </g:each>
                            </ul>
                        </g:if>
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
        </center>
        <br>
        <g:render template='../includes/footer'/>
    </div>
</div>
</body>
</html>
