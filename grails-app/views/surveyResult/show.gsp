
<%@ page import="edu.harvard.cscie56.finalproject.SurveyResult" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="mobile">
        <g:set var="entityName" value="${message(code: 'surveyResult.label', default: 'SurveyResult')}" />
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
			
				<dt><g:message code="surveyResult.id.label" default="Id" /></dt>
				
					<dd><g:fieldValue bean="${surveyResultInstance}" field="id"/></dd>
				
			
				<dt><g:message code="surveyResult.name.label" default="Name" /></dt>
				
					<dd><g:fieldValue bean="${surveyResultInstance}" field="name"/></dd>
				
			
				<dt><g:message code="surveyResult.answers.label" default="Answers" /></dt>
				
					<g:each in="${surveyResultInstance.answers}" var="a">
						<dd><g:link controller="answer" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></dd>
					</g:each>
				
			
				<dt><g:message code="surveyResult.dateCreated.label" default="Date Created" /></dt>
				
					<dd><g:formatDate date="${surveyResultInstance?.dateCreated}" /></dd>
				
			
				<dt><g:message code="surveyResult.survey.label" default="Survey" /></dt>
				
					<dd><g:link controller="survey" action="show" id="${surveyResultInstance?.survey?.id}">${surveyResultInstance?.survey?.encodeAsHTML()}</g:link></dd>
				
			
				<dt><g:message code="surveyResult.user.label" default="User" /></dt>
				
					<dd><g:link controller="user" action="show" id="${surveyResultInstance?.user?.id}">${surveyResultInstance?.user?.encodeAsHTML()}</g:link></dd>
				
			
			</dl>
			<g:form>
				<g:hiddenField name="id" value="${surveyResultInstance?.id}" />
				<g:actionSubmit data-icon="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" />
			</g:form>
		</div>
		<div data-role="footer">
		</div>
    </body>
</html>
