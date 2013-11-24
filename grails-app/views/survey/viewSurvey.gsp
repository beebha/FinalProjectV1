<!DOCTYPE html>
<html>
<head>
    <g:render template='../includes/header'/>
</head>
<body>
<g:javascript src="application.js"/>
<div data-role="page" id="viewSurveyPage">
    <div data-theme="a" data-role="header">
        <h3>
            CSCI-56 Final Project - Surveys Galore
        </h3>
    </div>
    <div data-role="content">
        <h2>View Survey - ${surveyInstance?.name}</h2>
        <div data-role="fieldcontain">
            <label for="surveyname">
                Name
            </label>
            <input name="name" id="surveyname" value="${surveyInstance?.name}" type="text" data-mini="true" required <g:if test="${surveyState == 'active'}">readonly</g:if>>
        </div>
        <g:if test="${surveyState == 'active'}">
            <div data-role="fieldcontain">
                <label for="surveyname">
                    Name
                </label>
                <input name="category" id="category" value="${surveyInstance?.category}" type="text" data-mini="true" readonly>
            </div>
        </g:if>
        <g:else>
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
        </g:else>

        <g:set var="currentQnCnt" value="${1}"/>
        <g:each in='${surveyInstance?.questions}' var='singleQn'>
            <div id="question${currentQnCnt}">
                <h3>Question ${currentQnCnt}</h3>
                <div data-role="fieldcontain">
                    <label for="questionText">
                        Question Text
                    </label>
                    <input name="questionText" id="questionText" value="${singleQn?.questionText}" type="text" data-mini="true" required <g:if test="${surveyState == 'active'}">readonly</g:if>>
                    <label for="questionType">
                        Question Type
                    </label>
                    <input name="type" id="questionType" value="${singleQn?.type}" type="text" data-mini="true" readonly>
                    <g:if test="${singleQn?.type == 'Comment'}">
                        <div data-role="fieldcontain">
                            <label for="qn${currentQnCnt}Comments">
                                Comments
                            </label>
                            <textarea name="qn${currentQnCnt}Comments" id="qn${currentQnCnt}Comments"></textarea>
                        </div>
                    </g:if>
                    <g:if test="${singleQn?.type == 'Multiple Choice (One Answer)'}">
                        <div data-role="fieldcontain">
                            <fieldset data-role="controlgroup" data-type="vertical" data-mini="true">
                                <legend>
                                    Choose
                                </legend>
                                <g:set var="optionCntOne" value="${1}"/>
                                <g:each in='${singleQn?.options}' var='singleOption'>
                                    <input id="qn${currentQnCnt}Radio${optionCntOne}" name="qn${currentQnCnt}Radio" data-theme="c" type="radio">
                                    <label for="qn${currentQnCnt}Radio${optionCntOne}">
                                        ${singleOption}
                                    </label>
                                    <g:set var="optionCntOne" value="${optionCntOne + 1}"/>
                                </g:each>
                            </fieldset>
                        </div>
                    </g:if>
                    <g:if test="${singleQn?.type == 'Multiple Choice (Multiple Answers)'}">
                        <div data-role="fieldcontain">
                            <fieldset data-role="controlgroup" data-type="vertical" data-mini="true">
                                <legend>
                                    Choose:
                                </legend>
                                <g:set var="optionCntMultiple" value="${1}"/>
                                <g:each in='${singleQn?.options}' var='singleOption'>
                                    <input id="qn${currentQnCnt}Checkbox${optionCntMultiple}" data-theme="c" type="checkbox">
                                    <label for="qn${currentQnCnt}Checkbox${optionCntMultiple}">
                                        ${singleOption}
                                    </label>
                                    <g:set var="optionCntMultiple" value="${optionCntMultiple + 1}"/>
                                </g:each>
                            </fieldset>
                        </div>
                    </g:if>
                    <g:if test="${singleQn?.type == 'Numerical Slider Scale'}">
                        <div data-role="fieldcontain">
                            <label for="qn${currentQnCnt}Slider">
                                Value
                            </label>
                            <input id="qn${currentQnCnt}Slider" name="qn${currentQnCnt}Slider" type="range" value="${singleQn?.scale}" min="0" max="${singleQn?.scale}" data-highlight="true" data-mini="true">
                        </div>
                    </g:if>
                    <g:if test="${singleQn?.type == 'Ranking'}">
                        <div data-role="fieldcontain">
                            <label for="qn${currentQnCnt}Rank">
                                Rank
                            </label>
                            <ul id="qn${currentQnCnt}Rank" data-role="listview" data-divider-theme="b" data-inset="true" data-mini="true">
                                <g:each in='${singleQn?.options}' var='singleOption'>
                                <li data-role="fieldcontain">
                                    <select data-mini="true" data-inline="true">
                                        <g:each in="${1..singleQn?.options?.size()}">
                                            <option value="${it}">${it}</option>
                                        </g:each>
                                    </select>
                                    ${singleOption}
                                </li>
                                </g:each>
                            </ul>
                        </div>
                    </g:if>
                    <g:if test="${singleQn?.type == 'Discrete Rating Scale'}">
                        <div data-role="fieldcontain">
                            <fieldset data-role="controlgroup" data-type="horizontal" data-mini="true">
                                <legend>
                                    Choose
                                </legend>
                                <g:each in="${1..singleQn?.scale}">
                                    <input id="qn${currentQnCnt}RadioH${it}" name="qn${currentQnCnt}RadioH" data-theme="c" type="radio">
                                    <label for="qn${currentQnCnt}RadioH${it}">
                                        ${it}
                                    </label>
                                </g:each>
                            </fieldset>
                        </div>
                    </g:if>
                    <br>
                    <g:if test="${surveyState != 'active'}">
                        <center>
                            <g:if test="${singleQn?.type != 'Comment'}">
                                <input type="button" value="Edit Question" data-icon="arrow-r" data-iconpos="right" data-mini="true" data-inline="true" onclick="editQn(${currentQnCnt});">
                            </g:if>
                            <input type="button" value="Delete Question" data-icon="delete" data-iconpos="right" data-mini="true" data-inline="true">
                        </center>
                    </g:if>
                </div>
            </div>
            <g:set var="currentQnCnt" value="${currentQnCnt + 1}"/>
        </g:each>
        <center><h3>END OF SURVEY</h3></center>
        <center>
            <g:if test="${surveyState == 'active'}">
                <input type="submit" value="Deactivate Survey" data-icon="home" data-iconpos="right" data-mini="true" data-inline="true">
            </g:if>
            <g:if test="${surveyState == 'complete'}">
                <input type="submit" value="Save & Publish Survey" data-icon="home" data-iconpos="right" data-mini="true" data-inline="true">
            </g:if>
            <g:if test="${surveyState == 'incomplete'}">
                <input type="submit" value="Save & Continue Later" data-icon="arrow-r" data-iconpos="right" data-mini="true" data-inline="true">
                <input type="submit" value="Save & Complete Survey" data-icon="check" data-iconpos="right" data-mini="true" data-inline="true">
            </g:if>
            <g:render template='../includes/confirmLogout'/>
            <a id="logoutBtn" href="#popupDialog" data-rel="popup" data-role="button" data-icon="gear" data-iconpos="right" data-mini="true" data-inline="true" data-transition="pop">Logout</a>
        </center>
        <g:render template='../includes/footer'/>
    </div>
</div>
</body>
</html>