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
        <h2>Take Survey</h2>
        <g:if test="${flash.message}">
            <div class="errorMsg">${flash.message}</div>
        </g:if>
        <g:form controller="surveyResult" action="saveSurveyResults">
        <g:hiddenField name="surveyID" value="${surveyInstance?.id}"/>
        <h3>${surveyInstance?.name} - (${surveyInstance?.category})</h3>
        <g:set var="currentQnCnt" value="${1}"/>
        <g:each in='${surveyInstance?.questions}' var='singleQn'>
            <div id="question${currentQnCnt}">
                <h3>Question ${currentQnCnt}</h3>
                <h4>${singleQn?.questionText}</h4>
                <div data-role="fieldcontain">
                    <g:if test="${singleQn?.type == 'Comment'}">
                        <div data-role="fieldcontain">
                            <label for="qn${singleQn?.id}">
                                Comments
                            </label>
                            <textarea name="qn${singleQn?.id}" id="qn${singleQn?.id}" required></textarea>
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
                                    <input id="qn${currentQnCnt}Radio${optionCntOne}" name="qn${singleQn?.id}" value="${singleOption}" data-theme="c" type="radio" required>
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
                            <fieldset data-role="controlgroup" data-type="vertical" data-mini="true" required>
                                <legend>
                                    Choose
                                </legend>
                                <g:set var="optionCntMultiple" value="${1}"/>
                                <g:each in='${singleQn?.options}' var='singleOption'>
                                    <input id="qn${currentQnCnt}Checkbox${optionCntMultiple}" name="qn${singleQn?.id}" value="${singleOption}" data-theme="c" type="checkbox">
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
                                Choose
                            </label>
                            <span class="ui-slider-inner-label" style="position: absolute; left:31%; top:30px; text-shadow:none; color:black; font-weight:normal">${singleQn?.startLabel}</span>
                            <input id="qn${currentQnCnt}Slider" name="qn${singleQn?.id}"
                                   type="range" value="${singleQn?.scale}" min="0" max="${singleQn?.scale}"
                                   data-highlight="true" data-mini="true" required>
                            <span class="ui-slider-inner-label" style="position: absolute; right:2%; top:30px; text-shadow:none; color:black; font-weight:normal">${singleQn?.endLabel}</span>
                        </div>
                    </g:if>
                    <g:if test="${singleQn?.type == 'Ranking'}">
                        <div data-role="fieldcontain">
                            <label for="qn${currentQnCnt}Rank">
                                Rank
                            </label>
                            <ul id="qn${currentQnCnt}Rank" data-role="listview" data-divider-theme="b" data-inset="true" data-mini="true">
                                <g:set var="optionCntRank" value="${1}"/>
                                <g:each in='${singleQn?.options}' var='singleOption'>
                                    <li data-role="fieldcontain">
                                        <select data-mini="true" data-inline="true"
                                                id="qn${currentQnCnt}RankSelect${optionCntRank}"
                                                name="qn${singleQn?.id}"
                                                onchange="setRatingValues('qn${currentQnCnt}RankSelect${optionCntRank}', '${singleQn?.options?.size()}')">
                                            <g:each in="${1..singleQn?.options?.size()}">
                                                <option value="${it}">${it}</option>
                                            </g:each>
                                        </select>
                                        ${singleOption}
                                    </li>
                                    <g:set var="optionCntRank" value="${optionCntRank + 1}"/>
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
                                    <input id="qn${currentQnCnt}RadioH${it}" name="qn${singleQn?.id}" value="${it}" data-theme="c" type="radio" required>
                                    <label for="qn${currentQnCnt}RadioH${it}">
                                        <g:if test="${it == 1}">
                                            ${singleQn?.startLabel}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </g:if>
                                        ${it}
                                        <g:if test="${it == singleQn?.scale}">
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${singleQn?.endLabel}
                                        </g:if>
                                    </label>
                                </g:each>
                            </fieldset>
                        </div>
                    </g:if>
                    <g:if test="${singleQn?.overallComment == true}">
                        <div data-role="fieldcontain">
                            <label for="qn${currentQnCnt}AdditionalComments">
                                Comments
                            </label>
                            <textarea name="additionalCommentsQn${singleQn?.id}" id="qn${currentQnCnt}AdditionalComments"></textarea>
                        </div>
                    </g:if>
                    <br>
                </div>
            </div>
            <g:set var="currentQnCnt" value="${currentQnCnt + 1}"/>
        </g:each>
        <br><center><h3>END OF SURVEY</h3></center><br>
        <center>
            <input type="submit" value="Submit Survey Results" data-icon="check" data-iconpos="right" data-mini="true" data-inline="true">
            <g:render template='../includes/confirmLogout'/>
            <a id="logoutBtn" href="#popupDialog" data-rel="popup" data-role="button" data-icon="gear" data-iconpos="right" data-mini="true" data-inline="true" data-transition="pop">Logout</a>
        </center>
        </g:form>
        <g:render template='../includes/footer'/>
    </div>
</div>
</body>
</html>