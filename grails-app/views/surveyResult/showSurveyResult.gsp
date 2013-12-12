<!DOCTYPE html>
<html>
<head>
    <g:render template='../includes/header'/>
</head>
<body>
<g:javascript src="application.js"/>
<div data-role="page" id="showSurveyResultPage">
    <g:render template='../includes/headerBar'/>
    <div data-role="content">
        <h2>Survey Results</h2>
        <h3>Taken by <em>${surveyResultInstance?.surveyTaker.username}</em> (<g:formatDate date="${surveyResultInstance?.dateCreated}" formatName="default.date.format"/>)</h3>
        <g:set var="currentQnCnt" value="${1}"/>
        <g:each in='${surveyResultInstance?.answers.sort{a,b-> a.question.id.compareTo(b.question.id)}}' var='singleAns'>
            <div id="question${currentQnCnt}">
                <h3>Question ${currentQnCnt}</h3>
                <h4>${singleAns?.question.questionText}</h4>
                <div data-role="fieldcontain">
                    <g:if test="${singleAns?.question.type == 'Comment'}">
                        <div data-role="fieldcontain">
                            <label for="qn${singleAns?.id}">
                                Comments
                            </label>
                            <textarea name="qn${singleAns?.id}" id="qn${singleAns?.id}" readonly>${singleAns?.allAnswers.get(0)}</textarea>
                        </div>
                    </g:if>
                    <g:if test="${singleAns?.question.type == 'Multiple Choice (One Answer)'}">
                        <div data-role="fieldcontain">
                            <fieldset data-role="controlgroup" data-type="vertical" data-mini="true">
                                <legend>
                                    Choose
                                </legend>
                                <g:set var="optionCntOne" value="${1}"/>
                                <g:each in='${singleAns?.question.options}' var='singleOption'>
                                    <input id="qn${currentQnCnt}Radio${optionCntOne}" name="qn${singleAns?.question.id}"
                                            <g:if test="${singleAns?.allAnswers.get(0) == singleOption}">checked</g:if>
                                           data-theme="c" type="radio" disabled>
                                    <label for="qn${currentQnCnt}Radio${optionCntOne}">
                                        ${singleOption}
                                    </label>
                                    <g:set var="optionCntOne" value="${optionCntOne + 1}"/>
                                </g:each>
                            </fieldset>
                        </div>
                    </g:if>
                    <g:if test="${singleAns?.question.type == 'Multiple Choice (Multiple Answers)'}">
                        <div data-role="fieldcontain">
                            <fieldset data-role="controlgroup" data-type="vertical" data-mini="true" required>
                                <legend>
                                    Choose
                                </legend>
                                <g:set var="optionCntMultiple" value="${1}"/>
                                <g:each in='${singleAns?.question.options}' var='singleOption'>
                                    <input id="qn${currentQnCnt}Checkbox${optionCntMultiple}" name="qn${singleAns?.question.id}"
                                           <g:if test="${singleAns?.allAnswers.contains(singleOption)}">checked</g:if>
                                           data-theme="c" type="checkbox" disabled>
                                    <label for="qn${currentQnCnt}Checkbox${optionCntMultiple}">
                                        ${singleOption}
                                    </label>
                                    <g:set var="optionCntMultiple" value="${optionCntMultiple + 1}"/>
                                </g:each>
                            </fieldset>
                        </div>
                    </g:if>
                    <g:if test="${singleAns?.question.type == 'Numerical Slider Scale'}">
                        <div data-role="fieldcontain">
                            <label for="qn${currentQnCnt}Slider">
                                Choose
                            </label>
                            <span class="ui-slider-inner-label" style="position: absolute; left:31%; top:30px; text-shadow:none; color:black; font-weight:normal">${singleAns?.question.startLabel}</span>
                            <input id="qn${currentQnCnt}Slider" name="qn${singleAns?.question.id}"
                                   type="range" value="${singleAns?.allAnswers.get(0)}" min="0" max="${singleAns?.question.scale}"
                                   data-highlight="true" data-mini="true" disabled>
                            <span class="ui-slider-inner-label" style="position: absolute; right:2%; top:30px; text-shadow:none; color:black; font-weight:normal">${singleAns?.question.endLabel}</span>
                        </div>
                    </g:if>
                    <g:if test="${singleAns?.question.type == 'Ranking'}">
                        <div data-role="fieldcontain">
                            <label for="qn${currentQnCnt}Rank">
                                Rank
                            </label>
                            <ul id="qn${currentQnCnt}Rank" data-role="listview" data-divider-theme="b" data-inset="true" data-mini="true">
                                <g:set var="optionCntRank" value="${1}"/>
                                <g:each in='${singleAns?.question.options}' var='singleOption'>
                                    <li data-role="fieldcontain">
                                        <select data-mini="true" data-inline="true"
                                                id="qn${currentQnCnt}RankSelect${optionCntRank}"
                                                name="qn${singleAns?.question.id}" disabled>
                                            <g:each in="${1..singleAns?.question.options.size()}">
                                                <option value="${it}" <g:if test="${singleAns?.allAnswers.get(optionCntRank-1) == it+""}">selected</g:if>>${it}</option>
                                            </g:each>
                                        </select>
                                        ${singleOption}
                                    </li>
                                    <g:set var="optionCntRank" value="${optionCntRank + 1}"/>
                                </g:each>
                            </ul>
                        </div>
                    </g:if>
                    <g:if test="${singleAns?.question.type == 'Discrete Rating Scale'}">
                        <div data-role="fieldcontain">
                            <fieldset data-role="controlgroup" data-type="horizontal" data-mini="true">
                                <legend>
                                    Choose
                                </legend>
                                <g:each in="${1..singleAns?.question.scale}">
                                    <input id="qn${currentQnCnt}RadioH${it}" name="qn${singleAns?.question.id}"
                                           <g:if test="${singleAns?.allAnswers.get(0) == it+""}">checked</g:if>
                                           data-theme="c" type="radio" disabled>
                                    <label for="qn${currentQnCnt}RadioH${it}">
                                        <g:if test="${it == 1}">
                                            ${singleAns?.question.startLabel}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </g:if>
                                        ${it}
                                        <g:if test="${it == singleAns?.question.scale}">
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${singleAns?.question.endLabel}
                                        </g:if>
                                    </label>
                                </g:each>
                            </fieldset>
                        </div>
                    </g:if>
                    <g:if test="${singleAns?.question.overallComment == true}">
                        <div data-role="fieldcontain">
                            <label for="qn${currentQnCnt}AdditionalComments">
                                Comments
                            </label>
                            <textarea name="additionalCommentsQn${singleAns?.question.id}" id="qn${currentQnCnt}AdditionalComments" readonly>${singleAns?.additonalComments}</textarea>
                        </div>
                    </g:if>
                    <br>
                </div>
            </div>
            <g:set var="currentQnCnt" value="${currentQnCnt + 1}"/>
        </g:each>
        <br><center><h3>END OF SURVEY RESULTS</h3></center><br>
        <g:render template='../includes/footer'/>
    </div>
</div>
</body>
</html>