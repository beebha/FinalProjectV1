<%@ page import="edu.harvard.cscie56.finalproject.Survey" %>



<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="survey.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="required" value="${surveyInstance?.name}" />
</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'category', 'error')} required">
	<label for="category">
		<g:message code="survey.category.label" default="Category" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="category" from="${surveyInstance.constraints.category.inList}" value="${surveyInstance?.category}" valueMessagePrefix="survey.category"  />
</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'questions', 'error')} ">
	<label for="questions">
		<g:message code="survey.questions.label" default="Questions" />
		
	</label>
	
<ul>
<g:each in="${surveyInstance?.questions?}" var="q">
    <li><g:link controller="question" action="show" id="${q.id}">${q?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="question" action="create" params="['survey.id': surveyInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'question.label', default: 'Question')])}</g:link>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'surveyResults', 'error')} ">
	<label for="surveyResults">
		<g:message code="survey.surveyResults.label" default="Survey Results" />
		
	</label>
	
<ul>
<g:each in="${surveyInstance?.surveyResults?}" var="s">
    <li><g:link controller="surveyResult" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="surveyResult" action="create" params="['survey.id': surveyInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'surveyResult.label', default: 'SurveyResult')])}</g:link>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="survey.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="user.id" from="${edu.harvard.cscie56.finalproject.auth.User.list()}" optionKey="id" value="${surveyInstance?.user?.id}"  />
</div>

