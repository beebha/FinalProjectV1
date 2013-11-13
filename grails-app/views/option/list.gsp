
<%@ page import="edu.harvard.cscie56.finalproject.Option" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'option.label', default: 'Option')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-option" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/admin')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-option" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="optionText" title="${message(code: 'option.optionText.label', default: 'Option Text')}" />
					
						<th><g:message code="option.question.label" default="Question" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${optionInstanceList}" status="i" var="optionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${optionInstance.id}">${fieldValue(bean: optionInstance, field: "optionText")}</g:link></td>
					
						<td>${fieldValue(bean: optionInstance, field: "question")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${optionInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
