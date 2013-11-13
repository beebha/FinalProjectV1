
<%@ page import="edu.harvard.cscie56.finalproject.SurveyResult" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'surveyResult.label', default: 'SurveyResult')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-surveyResult" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/admin')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-surveyResult" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'surveyResult.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'surveyResult.dateCreated.label', default: 'Date Created')}" />
					
						<th><g:message code="surveyResult.survey.label" default="Survey" /></th>
					
						<th><g:message code="surveyResult.user.label" default="User" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${surveyResultInstanceList}" status="i" var="surveyResultInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${surveyResultInstance.id}">${fieldValue(bean: surveyResultInstance, field: "name")}</g:link></td>
					
						<td><g:formatDate date="${surveyResultInstance.dateCreated}" /></td>
					
						<td>${fieldValue(bean: surveyResultInstance, field: "survey")}</td>
					
						<td>${fieldValue(bean: surveyResultInstance, field: "user")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${surveyResultInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
