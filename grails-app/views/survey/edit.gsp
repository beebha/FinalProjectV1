

<%@ page import="edu.harvard.cscie56.finalproject.Survey" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="mobile">
        <g:set var="entityName" value="${message(code: 'survey.label', default: 'Survey')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
		<div data-role="header" data-position="fixed">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<div data-role="navbar">
				<ul>
					<li><a data-icon="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
					<li><g:link data-icon="grid" data-ajax="false" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				</ul>
			</div>
		</div>
		<div data-role="content">
			<g:if test="${flash.message}">
			<div class="message" role="alert">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${surveyInstance}">
			<div class="errors" role="alert">
				<g:renderErrors bean="${surveyInstance}" as="list" />
			</div>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${surveyInstance?.id}" />
				<g:hiddenField name="version" value="${surveyInstance?.version}" />
			
				<div data-role="fieldcontain">
					<label for="name"><g:message code="survey.name.label" default="Name" /></label>
					<g:textField name="name" required="required" value="${surveyInstance?.name}" />
				</div>
			
				<div data-role="fieldcontain">
					<label for="category"><g:message code="survey.category.label" default="Category" /></label>
					<g:select name="category" from="${surveyInstance.constraints.category.inList}" value="${surveyInstance?.category}" valueMessagePrefix="survey.category"  />
				</div>
			
				<div data-role="fieldcontain">
					<label for="questions"><g:message code="survey.questions.label" default="Questions" /></label>
					
<ul>
<g:each in="${surveyInstance?.questions?}" var="q">
    <li><g:link controller="question" action="show" id="${q.id}">${q?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="question" action="create" params="['survey.id': surveyInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'question.label', default: 'Question')])}</g:link>

				</div>
			
				<div data-role="fieldcontain">
					<label for="surveyResults"><g:message code="survey.surveyResults.label" default="Survey Results" /></label>
					
<ul>
<g:each in="${surveyInstance?.surveyResults?}" var="s">
    <li><g:link controller="surveyResult" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="surveyResult" action="create" params="['survey.id': surveyInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'surveyResult.label', default: 'SurveyResult')])}</g:link>

				</div>
			
				<div data-role="fieldcontain">
					<label for="user"><g:message code="survey.user.label" default="User" /></label>
					<g:select name="user.id" from="${edu.harvard.cscie56.finalproject.auth.User.list()}" optionKey="id" value="${surveyInstance?.user?.id}"  />
				</div>
			
				<g:actionSubmit data-icon="check" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
			</g:form>
		</div>
		<div data-role="footer">
		</div>
    </body>
</html>
