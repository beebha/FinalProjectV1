
<%@ page import="edu.harvard.cscie56.finalproject.Answer" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="mobile">
        <g:set var="entityName" value="${message(code: 'answer.label', default: 'Answer')}" />
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
			
				<dt><g:message code="answer.id.label" default="Id" /></dt>
				
					<dd><g:fieldValue bean="${answerInstance}" field="id"/></dd>
				
			
				<dt><g:message code="answer.optionSelected.label" default="Option Selected" /></dt>
				
					<dd><g:link controller="option" action="show" id="${answerInstance?.optionSelected?.id}">${answerInstance?.optionSelected?.encodeAsHTML()}</g:link></dd>
				
			
				<dt><g:message code="answer.surveyResult.label" default="Survey Result" /></dt>
				
					<dd><g:link controller="surveyResult" action="show" id="${answerInstance?.surveyResult?.id}">${answerInstance?.surveyResult?.encodeAsHTML()}</g:link></dd>
				
			
			</dl>
			<g:form>
				<g:hiddenField name="id" value="${answerInstance?.id}" />
				<g:actionSubmit data-icon="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" />
			</g:form>
		</div>
		<div data-role="footer">
		</div>
    </body>
</html>
