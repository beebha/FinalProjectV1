<%@ page import="edu.harvard.cscie56.finalproject.Option" %>



<div class="fieldcontain ${hasErrors(bean: optionInstance, field: 'optionText', 'error')} required">
	<label for="optionText">
		<g:message code="option.optionText.label" default="Option Text" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="optionText" required="required" value="${optionInstance?.optionText}" />
</div>

<div class="fieldcontain ${hasErrors(bean: optionInstance, field: 'question', 'error')} required">
	<label for="question">
		<g:message code="option.question.label" default="Question" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="question.id" from="${edu.harvard.cscie56.finalproject.Question.list()}" optionKey="id" value="${optionInstance?.question?.id}"  />
</div>

