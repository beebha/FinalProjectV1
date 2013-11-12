
<%@ page import="edu.harvard.cscie56.finalproject.Question" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="mobile">
        <g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
		<div data-role="header" data-position="fixed">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<div data-role="navbar">
				<ul>
					<li><a data-icon="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
					<li><g:link data-icon="grid" data-ajax="false" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				</ul>
			</div>
		</div>
		<div data-role="content">
			<g:if test="${flash.message}">
			<div class="message">${flash.message}</div>
			</g:if>
			<dl>
			
				<dt><g:message code="question.id.label" default="Id" /></dt>
				
					<dd><g:fieldValue bean="${questionInstance}" field="id"/></dd>
				
			
				<dt><g:message code="question.questionText.label" default="Question Text" /></dt>
				
					<dd><g:fieldValue bean="${questionInstance}" field="questionText"/></dd>
				
			
				<dt><g:message code="question.type.label" default="Type" /></dt>
				
					<dd><g:fieldValue bean="${questionInstance}" field="type"/></dd>
				
			
				<dt><g:message code="question.options.label" default="Options" /></dt>
				
					<g:each in="${questionInstance.options}" var="o">
						<dd><g:link controller="option" action="show" id="${o.id}">${o?.encodeAsHTML()}</g:link></dd>
					</g:each>
				
			
				<dt><g:message code="question.survey.label" default="Survey" /></dt>
				
					<dd><g:link controller="survey" action="show" id="${questionInstance?.survey?.id}">${questionInstance?.survey?.encodeAsHTML()}</g:link></dd>
				
			
			</dl>
			<g:form>
				<g:hiddenField name="id" value="${questionInstance?.id}" />
				<g:actionSubmit data-icon="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" />
			</g:form>
		</div>
		<div data-role="footer">
		</div>
    </body>
</html>
