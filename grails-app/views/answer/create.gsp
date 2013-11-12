

<%@ page import="edu.harvard.cscie56.finalproject.Answer" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="mobile">
        <g:set var="entityName" value="${message(code: 'answer.label', default: 'Answer')}" />
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
			<g:hasErrors bean="${answerInstance}">
			<div class="errors" role="alert">
				<g:renderErrors bean="${answerInstance}" as="list" />
			</div>
			</g:hasErrors>
			<g:form action="save" >
			
				<div data-role="fieldcontain">
					<label for="optionSelected"><g:message code="answer.optionSelected.label" default="Option Selected" /></label>
					<g:select name="optionSelected.id" from="${edu.harvard.cscie56.finalproject.Option.list()}" optionKey="id" value="${answerInstance?.optionSelected?.id}"  />
				</div>
			
				<div data-role="fieldcontain">
					<label for="surveyResult"><g:message code="answer.surveyResult.label" default="Survey Result" /></label>
					<g:select name="surveyResult.id" from="${edu.harvard.cscie56.finalproject.SurveyResult.list()}" optionKey="id" value="${answerInstance?.surveyResult?.id}"  />
				</div>
			
				<g:submitButton name="create" data-icon="check" value="${message(code: 'default.button.create.label', default: 'Create')}" />
			</g:form>
		</div>
		<div data-role="footer">
		</div>
    </body>
</html>
