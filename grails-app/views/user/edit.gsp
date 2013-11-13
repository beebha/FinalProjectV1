

<%@ page import="edu.harvard.cscie56.finalproject.auth.User" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="mobile">
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
		<div data-role="header" data-position="fixed">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<div data-role="navbar">
				<ul>
					<li><a data-icon="home" href="${createLink(uri: '/admin')}"><g:message code="default.home.label"/></a></li>
					<li><g:link data-icon="grid" data-ajax="false" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				</ul>
			</div>
		</div>
		<div data-role="content">
			<g:if test="${flash.message}">
			<div class="message" role="alert">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${userInstance}">
			<div class="errors" role="alert">
				<g:renderErrors bean="${userInstance}" as="list" />
			</div>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${userInstance?.id}" />
				<g:hiddenField name="version" value="${userInstance?.version}" />
			
				<div data-role="fieldcontain">
					<label for="username"><g:message code="user.username.label" default="Username" /></label>
					<g:field type="email" name="username" required="required" value="${userInstance?.username}" />
				</div>
			
				<div data-role="fieldcontain">
					<label for="password"><g:message code="user.password.label" default="Password" /></label>
					<g:textField name="password" required="required" value="${userInstance?.password}" />
				</div>
			
				<div data-role="fieldcontain">
					<label for="accountExpired"><g:message code="user.accountExpired.label" default="Account Expired" /></label>
					<g:checkBox name="accountExpired" value="${userInstance?.accountExpired}" />
				</div>
			
				<div data-role="fieldcontain">
					<label for="accountLocked"><g:message code="user.accountLocked.label" default="Account Locked" /></label>
					<g:checkBox name="accountLocked" value="${userInstance?.accountLocked}" />
				</div>
			
				<div data-role="fieldcontain">
					<label for="enabled"><g:message code="user.enabled.label" default="Enabled" /></label>
					<g:checkBox name="enabled" value="${userInstance?.enabled}" />
				</div>
			
				<div data-role="fieldcontain">
					<label for="passwordExpired"><g:message code="user.passwordExpired.label" default="Password Expired" /></label>
					<g:checkBox name="passwordExpired" value="${userInstance?.passwordExpired}" />
				</div>
			
				<g:actionSubmit data-icon="check" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
			</g:form>
		</div>
		<div data-role="footer">
		</div>
    </body>
</html>
