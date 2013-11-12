
<%@ page import="edu.harvard.cscie56.finalproject.SurveyResult" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'surveyResult.label', default: 'SurveyResult')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-surveyResult" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-surveyResult" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list surveyResult">
			
				<g:if test="${surveyResultInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="surveyResult.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${surveyResultInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyResultInstance?.answers}">
				<li class="fieldcontain">
					<span id="answers-label" class="property-label"><g:message code="surveyResult.answers.label" default="Answers" /></span>
					
						<g:each in="${surveyResultInstance.answers}" var="a">
						<span class="property-value" aria-labelledby="answers-label"><g:link controller="answer" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${surveyResultInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="surveyResult.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${surveyResultInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyResultInstance?.survey}">
				<li class="fieldcontain">
					<span id="survey-label" class="property-label"><g:message code="surveyResult.survey.label" default="Survey" /></span>
					
						<span class="property-value" aria-labelledby="survey-label"><g:link controller="survey" action="show" id="${surveyResultInstance?.survey?.id}">${surveyResultInstance?.survey?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyResultInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="surveyResult.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${surveyResultInstance?.user?.id}">${surveyResultInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${surveyResultInstance?.id}" />
					<g:link class="edit" action="edit" id="${surveyResultInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
