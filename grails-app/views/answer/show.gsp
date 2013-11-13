
<%@ page import="edu.harvard.cscie56.finalproject.Answer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'answer.label', default: 'Answer')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-answer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/admin')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-answer" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list answer">
			
				<g:if test="${answerInstance?.optionSelected}">
				<li class="fieldcontain">
					<span id="optionSelected-label" class="property-label"><g:message code="answer.optionSelected.label" default="Option Selected" /></span>
					
						<span class="property-value" aria-labelledby="optionSelected-label"><g:link controller="option" action="show" id="${answerInstance?.optionSelected?.id}">${answerInstance?.optionSelected?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${answerInstance?.surveyResult}">
				<li class="fieldcontain">
					<span id="surveyResult-label" class="property-label"><g:message code="answer.surveyResult.label" default="Survey Result" /></span>
					
						<span class="property-value" aria-labelledby="surveyResult-label"><g:link controller="surveyResult" action="show" id="${answerInstance?.surveyResult?.id}">${answerInstance?.surveyResult?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${answerInstance?.id}" />
					<g:link class="edit" action="edit" id="${answerInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
