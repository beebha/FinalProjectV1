<%@ page import="edu.harvard.cscie56.finalproject.Question" %>



<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'questionText', 'error')} required">
	<label for="questionText">
		<g:message code="question.questionText.label" default="Question Text" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="questionText" required="" value="${questionInstance?.questionText}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'type', 'error')} required">
	<label for="type">
		<g:message code="question.type.label" default="Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="type" from="${questionInstance.constraints.type.inList}" required="" value="${questionInstance?.type}" valueMessagePrefix="question.type"/>
</div>

<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'options', 'error')} ">
	<label for="options">
		<g:message code="question.options.label" default="Options" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${questionInstance?.options?}" var="o">
    <li><g:link controller="option" action="show" id="${o.id}">${o?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="option" action="create" params="['question.id': questionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'option.label', default: 'Option')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'survey', 'error')} required">
	<label for="survey">
		<g:message code="question.survey.label" default="Survey" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="survey" name="survey.id" from="${edu.harvard.cscie56.finalproject.Survey.list()}" optionKey="id" required="" value="${questionInstance?.survey?.id}" class="many-to-one"/>
</div>

