

<%@ page import="edu.harvard.cscie56.finalproject.Option" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="mobile">
        <g:set var="entityName" value="${message(code: 'option.label', default: 'Option')}" />
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
			<g:hasErrors bean="${optionInstance}">
			<div class="errors" role="alert">
				<g:renderErrors bean="${optionInstance}" as="list" />
			</div>
			</g:hasErrors>
			<g:form action="save" >
			
				<div data-role="fieldcontain">
					<label for="optionText"><g:message code="option.optionText.label" default="Option Text" /></label>
					<g:textField name="optionText" required="required" value="${optionInstance?.optionText}" />
				</div>
			
				<div data-role="fieldcontain">
					<label for="question"><g:message code="option.question.label" default="Question" /></label>
					<g:select name="question.id" from="${edu.harvard.cscie56.finalproject.Question.list()}" optionKey="id" value="${optionInstance?.question?.id}"  />
				</div>
			
				<g:submitButton name="create" data-icon="check" value="${message(code: 'default.button.create.label', default: 'Create')}" />
			</g:form>
		</div>
		<div data-role="footer">
		</div>
    </body>
</html>
