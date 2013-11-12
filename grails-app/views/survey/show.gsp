
<%@ page import="edu.harvard.cscie56.finalproject.Survey" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="mobile">
        <g:set var="entityName" value="${message(code: 'survey.label', default: 'Survey')}" />
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
			
				<dt><g:message code="survey.id.label" default="Id" /></dt>
				
					<dd><g:fieldValue bean="${surveyInstance}" field="id"/></dd>
				
			
				<dt><g:message code="survey.name.label" default="Name" /></dt>
				
					<dd><g:fieldValue bean="${surveyInstance}" field="name"/></dd>
				
			
				<dt><g:message code="survey.category.label" default="Category" /></dt>
				
					<dd><g:fieldValue bean="${surveyInstance}" field="category"/></dd>
				
			
				<dt><g:message code="survey.dateCreated.label" default="Date Created" /></dt>
				
					<dd><g:formatDate date="${surveyInstance?.dateCreated}" /></dd>
				
			
				<dt><g:message code="survey.questions.label" default="Questions" /></dt>
				
					<g:each in="${surveyInstance.questions}" var="q">
						<dd><g:link controller="question" action="show" id="${q.id}">${q?.encodeAsHTML()}</g:link></dd>
					</g:each>
				
			
				<dt><g:message code="survey.surveyResults.label" default="Survey Results" /></dt>
				
					<g:each in="${surveyInstance.surveyResults}" var="s">
						<dd><g:link controller="surveyResult" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></dd>
					</g:each>
				
			
				<dt><g:message code="survey.user.label" default="User" /></dt>
				
					<dd><g:link controller="user" action="show" id="${surveyInstance?.user?.id}">${surveyInstance?.user?.encodeAsHTML()}</g:link></dd>
				
			
			</dl>
			<g:form>
				<g:hiddenField name="id" value="${surveyInstance?.id}" />
				<g:actionSubmit data-icon="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" />
			</g:form>
		</div>
		<div data-role="footer">
		</div>
    </body>
</html>
