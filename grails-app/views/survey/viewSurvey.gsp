<!DOCTYPE html>
<html>
<head>
    <g:render template='../includes/header'/>
</head>
<body>
<g:javascript src="application.js"/>
<div data-role="page" id="viewSurveyPage">
    <g:render template='../includes/headerBarWithLogoutConfirmation'/>
    <div data-role="content">
        <h2>View Survey - ${surveyInstance?.name}</h2>
        <g:if test="${flash.message}">
            <div class="errorMsg">${flash.message}</div>
        </g:if>
        <g:form controller="survey" action="updateSurvey">
        <g:hiddenField name="surveyID" value="${surveyInstance?.id}"/>
        <div data-role="fieldcontain">
            <label for="surveyname">
                Name
            </label>
            <input name="name" id="surveyname" value="${surveyInstance?.name}" type="text" data-mini="true" required <g:if test="${surveyState == 'active'}">readonly</g:if>>
        </div>
        <g:if test="${surveyState == 'active'}">
            <div data-role="fieldcontain">
                <label for="category">
                    Category
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
        <g:each in='${surveyInstance?.questions.sort{a,b-> a.id.compareTo(b.id)}}' var='singleQn'>
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
                                    Choose
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
                                Choose
                            </label>
                            <span class="ui-slider-inner-label" style="position: absolute; left:31%; top:30px; text-shadow:none; color:black; font-weight:normal">${singleQn?.startLabel}</span>
                            <input id="qn${currentQnCnt}Slider" name="qn${currentQnCnt}Slider"
                                   type="range" value="${singleQn?.scale}" min="0" max="${singleQn?.scale}"
                                   data-highlight="true" data-mini="true">
                            <span class="ui-slider-inner-label" style="position: absolute; right:2%; top:30px; text-shadow:none; color:black; font-weight:normal">${singleQn?.endLabel}</span>
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
                            <textarea name="qn${currentQnCnt}AdditionalComments" id="qn${currentQnCnt}AdditionalComments"></textarea>
                        </div>
                    </g:if>
                    <br>
                    <g:if test="${surveyState != 'active'}">
                        <center>
                            <g:if test="${singleQn?.type != 'Comment'}">
                                <g:link controller="question" action="editQn" params="[surveyID: surveyInstance?.id, questionID: singleQn?.id, surveyState: surveyState]">
                                    <input type="button" value="Edit Question" data-icon="arrow-r" data-iconpos="right" data-mini="true" data-inline="true">
                                </g:link>
                            </g:if>
                            <g:link controller="question" action="deleteQn" params="[surveyID: surveyInstance?.id, questionID: singleQn?.id, surveyState: surveyState]">
                                <input type="button" value="Delete Question" data-icon="delete" data-iconpos="right" data-mini="true" data-inline="true">
                            </g:link>
                        </center>
                    </g:if>
                </div>
            </div>
            <g:set var="currentQnCnt" value="${currentQnCnt + 1}"/>
        </g:each>
        <br><center><h3>END OF SURVEY</h3></center><br>
        <center>
            <g:if test="${surveyState == 'active'}">
                <input type="submit" name="deactivate" value="Deactivate" data-icon="check" data-iconpos="right" data-mini="true" data-inline="true">
            </g:if>
            <g:if test="${surveyState == 'complete'}">
                <input type="submit" name="saveadd" value="Save & Add Question" data-icon="plus" data-iconpos="right" data-mini="true" data-inline="true">
                <input type="submit" name="savepublish" value="Save & Publish" data-icon="check" data-iconpos="right" data-mini="true" data-inline="true">
                <g:render template='../includes/confirmDelete'/>
                <a id="deleteBtn" href="#popupDeleteDialog" data-rel="popup" data-role="button" data-icon="delete" data-iconpos="right" data-mini="true" data-inline="true" data-transition="pop">Delete</a>
            </g:if>
            <g:if test="${surveyState == 'incomplete'}">
                <input type="submit" name="saveadd" value="Save & Add Question" data-icon="plus" data-iconpos="right" data-mini="true" data-inline="true">
                <input type="submit" name="savecontinue" value="Save & Continue Later" data-icon="arrow-r" data-iconpos="right" data-mini="true" data-inline="true">
                <input type="submit" name="savecomplete" value="Save & Complete" data-icon="check" data-iconpos="right" data-mini="true" data-inline="true">
                <g:render template='../includes/confirmDelete'/>
                <a id="deleteBtn" href="#popupDeleteDialog" data-rel="popup" data-role="button" data-icon="delete" data-iconpos="right" data-mini="true" data-inline="true" data-transition="pop">Delete</a>
            </g:if>
            <g:link controller="home">
                <input type="button" value="Cancel" data-icon="back" data-iconpos="right" data-mini="true" data-inline="true">
            </g:link>
        </center>
        </g:form>
        <g:render template='../includes/footer'/>
    </div>
</div>
</body>
</html>