<!DOCTYPE html>
<html>
<head>
    <g:render template='../includes/header'/>
</head>
<body>
<g:javascript src="application.js"/>
<div data-role="page" id="editQuestionPage">
    <div data-theme="a" data-role="header">
        <h3>
            CSCI-56 Final Project - Surveys Galore
        </h3>
    </div>
    <div data-role="content">
        <h2>Edit Question</h2>
        <g:form controller="question" action="saveQn">
            <g:hiddenField name="surveyID" value="${surveyID}"/>
            <g:hiddenField name="questionID" value="${questionInstance?.id}"/>
            <g:hiddenField name="surveyState" value="${surveyState}"/>
            <h3>Edit Question</h3>
            <div data-role="fieldcontain">
                <label for="questionText">
                    Question Text
                </label>
                <input name="questionText" id="questionText" value="${questionInstance?.questionText}" type="text" data-mini="true" required>
            </div>
            <div data-role="fieldcontain">
                <label for="questionTypeMenuEdit">
                    Question Type
                </label>
                <select id="questionTypeMenuEdit" name="type" data-mini="true" onchange='questionTypeChange(${qnTypesJSON}, ${questionInstance?.options.size()}, "Edit");'>
                    <g:each in='${qnTypes}' var='singleQnType'>
                        <option value="${singleQnType}" <g:if test="${questionInstance?.type == singleQnType}"> selected </g:if>>${singleQnType}</option>
                    </g:each>
                </select>
            </div>
            <div id="answerSection" <g:if test="${questionInstance?.type == 'Comment'}"> style="display:none;" </g:if>>
                <h3>Answer Options</h3>
                <div id="answerSectionForMultipleChoiceAndRanking"
                    <g:if test="${questionInstance?.type != 'Multiple Choice (One Answer)' &&
                            questionInstance?.type != 'Multiple Choice (Multiple Answers)' &&
                            questionInstance?.type != 'Ranking'}"> style="display:none;" </g:if>>
                    <g:set var="optionCnt" value="${1}"/>
                    <g:each in='${questionInstance?.options}' var='singleOption'>
                        <g:if test="${optionCnt > 2}">
                            <div id='option${optionCnt}HTML'>
                        </g:if>
                        <div data-role="fieldcontain">
                            <label for="option${optionCnt}">Option ${optionCnt}</label>
                            <input name="option${optionCnt}" id="option${optionCnt}" value="${singleOption}" data-mini="true">
                        </div>
                        <g:if test="${optionCnt > 2}">
                            </div>
                        </g:if>
                        <g:set var="optionCnt" value="${optionCnt + 1}"/>
                    </g:each>
                    <div id="moreOptions"></div>
                    <center>
                        <input type="button" value="Add Option" data-icon="plus" data-iconpos="right" data-mini="true" data-inline="true" onclick="addOption();">
                        <a id="removeOptionBtn" href="#"
                        data-role="button" data-icon="plus"
                        data-iconpos="right" data-mini="true"
                        data-inline="true" onclick="removeOption();"
                        <g:if test="${optionCnt < 3}"> style="display:none;" </g:if>>Remove Option</a>
                    </center>
                </div>
                <div id="answerSectionForNumericalSliderAndDiscreteRating"
                    <g:if test="${questionInstance?.type != 'Numerical Slider Scale' &&
                            questionInstance?.type != 'Discrete Rating Scale'}"> style="display:none;" </g:if>>
                    <div data-role="fieldcontain">
                        <label for="scaleMenu" id="scaleMenuTxt">
                        <g:if test="${questionInstance?.type == 'Numerical Slider Scale'}">
                            Numerical Slider Scale
                        </g:if>
                        <g:else>
                            Discrete Rating Scale
                        </g:else>
                        </label>
                        <select id="scaleMenu" name="scale" data-mini="true">
                            <option value="2" <g:if test="${questionInstance?.scale == 2}"> selected </g:if>>2</option>
                            <option value="3" <g:if test="${questionInstance?.scale == 3}"> selected </g:if>>3</option>
                            <option value="4" <g:if test="${questionInstance?.scale == 4}"> selected </g:if>>4</option>
                            <option value="5" <g:if test="${questionInstance?.scale == 5}"> selected </g:if>>5</option>
                            <option value="6" <g:if test="${questionInstance?.scale == 6}"> selected </g:if>>6</option>
                            <option value="7" <g:if test="${questionInstance?.scale == 7}"> selected </g:if>>7</option>
                            <option value="8" <g:if test="${questionInstance?.scale == 8}"> selected </g:if>>8</option>
                            <option value="9" <g:if test="${questionInstance?.scale == 9}"> selected </g:if>>9</option>
                            <option value="10" <g:if test="${questionInstance?.scale == 10}"> selected </g:if>>10</option>
                        </select>
                    </div>
                    <div data-role="fieldcontain">
                        <label for="scaleStartLbl" id="scaleStartLblTxt">
                            <g:if test="${questionInstance?.type == 'Numerical Slider Scale'}">
                                Numerical Slider Scale Start Label
                            </g:if>
                            <g:else>
                                Discrete Rating Scale Start Label
                            </g:else>
                        </label>
                        <input name="scaleStartLbl" id="scaleStartLbl" data-mini="true" value="${questionInstance?.startLabel}">
                    </div>
                    <div data-role="fieldcontain">
                        <label for="scaleEndLbl" id="scaleEndLblTxt">
                            <g:if test="${questionInstance?.type == 'Numerical Slider Scale'}">
                                Numerical Slider Scale End Label
                            </g:if>
                            <g:else>
                                Discrete Rating Scale End Label
                            </g:else>
                        </label>
                        <input name="scaleEndLbl" id="scaleEndLbl" data-mini="true" value="${questionInstance?.endLabel}">
                    </div>
                </div>
                <fieldset data-role="controlgroup" data-type="vertical">
                    <input id="addcomments" name="comment" data-theme="c" type="checkbox"
                        <g:if test="${questionInstance?.overallComment == true}"> checked </g:if>>
                    <label for="addcomments">
                        Add comment field
                    </label>
                </fieldset>
            </div>
            <br>
            <center>
                <g:link controller="survey" action="viewSurvey" params="[surveyState:surveyState, surveyID:surveyID]">
                    <input type="button" value="Cancel & Return to Survey" data-icon="check" data-iconpos="right" data-mini="true" data-inline="true">
                </g:link>
                <input type="submit" value="Save & Return to Survey" data-icon="check" data-iconpos="right" data-mini="true" data-inline="true">
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
