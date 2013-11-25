<!DOCTYPE html>
<html>
<head>
    <g:render template='../includes/header'/>
</head>
<body>
<g:javascript src="application.js"/>
<div data-role="page" id="viewQuestionPage">
    <div data-theme="a" data-role="header">
        <h3>
            CSCI-56 Final Project - Surveys Galore
        </h3>
    </div>
    <div data-role="content">
        <h2>Edit Question</h2>
        <g:form controller="question" action="saveQn">
            <g:hiddenField name="surveyID" value="${surveyID}"/>
            <input type="hidden" id="submitBtnClicked" name="submitBtnClicked">
            <input type="hidden" id="totalOptions" name="totalOptions">
            <h3>Edit Question</h3>
            <div data-role="fieldcontain">
                <label for="questionText">
                    Question Text
                </label>
                <input name="questionText" id="questionText" value="${questionInstance?.questionText}" type="text" data-mini="true" required>
            </div>
            <div data-role="fieldcontain">
                <label for="questionTypeMenu">
                    Question Type
                </label>
                <select id="questionTypeMenu" name="type" data-mini="true" onchange='questionTypeChange(${qnTypesJSON});'>
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
                        <div data-role="fieldcontain">
                            <label for="option${optionCnt}">Option ${optionCnt}</label>
                            <input name="option${optionCnt}" id="option${optionCnt}" value="${singleOption}" data-mini="true">
                        </div>
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
                <div id="answerSectionForNumericalSliderAndDiscreteRating" <g:if test="${questionInstance?.type == 'Comment'}"> style="display:none;" </g:if>>
                    <div data-role="fieldcontain">
                        <label for="scaleMenu" id="scaleMenuTxt"></label>
                        <select id="scaleMenu" name="scale" data-mini="true">
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                        </select>
                    </div>
                    <div data-role="fieldcontain">
                        <label for="scaleStartLbl" id="scaleStartLblTxt"></label>
                        <input name="scaleStartLbl" id="scaleStartLbl" data-mini="true">
                    </div>
                    <div data-role="fieldcontain">
                        <label for="scaleEndLbl" id="scaleEndLblTxt"></label>
                        <input name="scaleEndLbl" id="scaleEndLbl" data-mini="true">
                    </div>
                </div>
                <fieldset data-role="controlgroup" data-type="vertical">
                    <input id="addcomments" name="comment" data-theme="c" type="checkbox">
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
