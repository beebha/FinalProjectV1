<%@ page import="edu.harvard.cscie56.finalproject.Option" %>



<div class="fieldcontain ${hasErrors(bean: optionInstance, field: 'optionText', 'error')} required">
	<label for="optionText">
		<g:message code="option.optionText.label" default="Option Text" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="optionText" required="" value="${optionInstance?.optionText}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: optionInstance, field: 'question', 'error')} required">
	<label for="question">
		<g:message code="option.question.label" default="Question" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="question" name="question.id" from="${edu.harvard.cscie56.finalproject.Question.list()}" optionKey="id" required="" value="${optionInstance?.question?.id}" class="many-to-one"/>
</div>

