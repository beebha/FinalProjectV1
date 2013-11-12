
<%@ page import="edu.harvard.cscie56.finalproject.Option" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="mobile">
        <g:set var="entityName" value="${message(code: 'option.label', default: 'Option')}" />
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
			
				<dt><g:message code="option.id.label" default="Id" /></dt>
				
					<dd><g:fieldValue bean="${optionInstance}" field="id"/></dd>
				
			
				<dt><g:message code="option.optionText.label" default="Option Text" /></dt>
				
					<dd><g:fieldValue bean="${optionInstance}" field="optionText"/></dd>
				
			
				<dt><g:message code="option.question.label" default="Question" /></dt>
				
					<dd><g:link controller="question" action="show" id="${optionInstance?.question?.id}">${optionInstance?.question?.encodeAsHTML()}</g:link></dd>
				
			
			</dl>
			<g:form>
				<g:hiddenField name="id" value="${optionInstance?.id}" />
				<g:actionSubmit data-icon="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" />
			</g:form>
		</div>
		<div data-role="footer">
		</div>
    </body>
</html>
