

<%@ page import="edu.harvard.cscie56.finalproject.SurveyResult" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="mobile">
        <g:set var="entityName" value="${message(code: 'surveyResult.label', default: 'SurveyResult')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
		<div data-role="header" data-position="fixed">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
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
			<g:hasErrors bean="${surveyResultInstance}">
			<div class="errors" role="alert">
				<g:renderErrors bean="${surveyResultInstance}" as="list" />
			</div>
			</g:hasErrors>
			<g:form action="save" >
			
				<div data-role="fieldcontain">
					<label for="name"><g:message code="surveyResult.name.label" default="Name" /></label>
					<g:textField name="name" required="required" value="${surveyResultInstance?.name}" />
				</div>
			
				<div data-role="fieldcontain">
					<label for="answers"><g:message code="surveyResult.answers.label" default="Answers" /></label>
					
<ul>
<g:each in="${surveyResultInstance?.answers?}" var="a">
    <li><g:link controller="answer" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="answer" action="create" params="['surveyResult.id': surveyResultInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'answer.label', default: 'Answer')])}</g:link>

				</div>
			
				<div data-role="fieldcontain">
					<label for="survey"><g:message code="surveyResult.survey.label" default="Survey" /></label>
					<g:select name="survey.id" from="${edu.harvard.cscie56.finalproject.Survey.list()}" optionKey="id" value="${surveyResultInstance?.survey?.id}"  />
				</div>
			
				<div data-role="fieldcontain">
					<label for="user"><g:message code="surveyResult.user.label" default="User" /></label>
					<g:select name="user.id" from="${edu.harvard.cscie56.finalproject.auth.User.list()}" optionKey="id" value="${surveyResultInstance?.user?.id}"  />
				</div>
			
				<g:submitButton name="create" data-icon="check" value="${message(code: 'default.button.create.label', default: 'Create')}" />
			</g:form>
		</div>
		<div data-role="footer">
		</div>
    </body>
</html>
