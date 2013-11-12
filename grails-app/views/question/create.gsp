

<%@ page import="edu.harvard.cscie56.finalproject.Question" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="mobile">
        <g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
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
			<g:hasErrors bean="${questionInstance}">
			<div class="errors" role="alert">
				<g:renderErrors bean="${questionInstance}" as="list" />
			</div>
			</g:hasErrors>
			<g:form action="save" >
			
				<div data-role="fieldcontain">
					<label for="questionText"><g:message code="question.questionText.label" default="Question Text" /></label>
					<g:textField name="questionText" required="required" value="${questionInstance?.questionText}" />
				</div>
			
				<div data-role="fieldcontain">
					<label for="type"><g:message code="question.type.label" default="Type" /></label>
					<g:select name="type" from="${questionInstance.constraints.type.inList}" value="${questionInstance?.type}" valueMessagePrefix="question.type"  />
				</div>
			
				<div data-role="fieldcontain">
					<label for="options"><g:message code="question.options.label" default="Options" /></label>
					
<ul>
<g:each in="${questionInstance?.options?}" var="o">
    <li><g:link controller="option" action="show" id="${o.id}">${o?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="option" action="create" params="['question.id': questionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'option.label', default: 'Option')])}</g:link>

				</div>
			
				<div data-role="fieldcontain">
					<label for="survey"><g:message code="question.survey.label" default="Survey" /></label>
					<g:select name="survey.id" from="${edu.harvard.cscie56.finalproject.Survey.list()}" optionKey="id" value="${questionInstance?.survey?.id}"  />
				</div>
			
				<g:submitButton name="create" data-icon="check" value="${message(code: 'default.button.create.label', default: 'Create')}" />
			</g:form>
		</div>
		<div data-role="footer">
		</div>
    </body>
</html>
