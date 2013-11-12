<%@ page import="edu.harvard.cscie56.finalproject.SurveyResult" %>



<div class="fieldcontain ${hasErrors(bean: surveyResultInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="surveyResult.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${surveyResultInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: surveyResultInstance, field: 'answers', 'error')} ">
	<label for="answers">
		<g:message code="surveyResult.answers.label" default="Answers" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${surveyResultInstance?.answers?}" var="a">
    <li><g:link controller="answer" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="answer" action="create" params="['surveyResult.id': surveyResultInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'answer.label', default: 'Answer')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyResultInstance, field: 'survey', 'error')} required">
	<label for="survey">
		<g:message code="surveyResult.survey.label" default="Survey" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="survey" name="survey.id" from="${edu.harvard.cscie56.finalproject.Survey.list()}" optionKey="id" required="" value="${surveyResultInstance?.survey?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: surveyResultInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="surveyResult.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${edu.harvard.cscie56.finalproject.auth.User.list()}" optionKey="id" required="" value="${surveyResultInstance?.user?.id}" class="many-to-one"/>
</div>

