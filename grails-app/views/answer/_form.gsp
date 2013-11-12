<%@ page import="edu.harvard.cscie56.finalproject.Answer" %>



<div class="fieldcontain ${hasErrors(bean: answerInstance, field: 'optionSelected', 'error')} required">
	<label for="optionSelected">
		<g:message code="answer.optionSelected.label" default="Option Selected" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="optionSelected" name="optionSelected.id" from="${edu.harvard.cscie56.finalproject.Option.list()}" optionKey="id" required="" value="${answerInstance?.optionSelected?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: answerInstance, field: 'surveyResult', 'error')} required">
	<label for="surveyResult">
		<g:message code="answer.surveyResult.label" default="Survey Result" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="surveyResult" name="surveyResult.id" from="${edu.harvard.cscie56.finalproject.SurveyResult.list()}" optionKey="id" required="" value="${answerInstance?.surveyResult?.id}" class="many-to-one"/>
</div>

