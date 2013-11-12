<%@ page import="edu.harvard.cscie56.finalproject.SurveyResult" %>



<div class="fieldcontain ${hasErrors(bean: surveyResultInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="surveyResult.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="required" value="${surveyResultInstance?.name}" />
</div>

<div class="fieldcontain ${hasErrors(bean: surveyResultInstance, field: 'answers', 'error')} ">
	<label for="answers">
		<g:message code="surveyResult.answers.label" default="Answers" />
		
	</label>
	
<ul>
<g:each in="${surveyResultInstance?.answers?}" var="a">
    <li><g:link controller="answer" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="answer" action="create" params="['surveyResult.id': surveyResultInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'answer.label', default: 'Answer')])}</g:link>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyResultInstance, field: 'survey', 'error')} required">
	<label for="survey">
		<g:message code="surveyResult.survey.label" default="Survey" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="survey.id" from="${edu.harvard.cscie56.finalproject.Survey.list()}" optionKey="id" value="${surveyResultInstance?.survey?.id}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: surveyResultInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="surveyResult.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="user.id" from="${edu.harvard.cscie56.finalproject.auth.User.list()}" optionKey="id" value="${surveyResultInstance?.user?.id}"  />
</div>

