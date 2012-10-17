
<%@ page import="com.mdw360.domain.ReceivedMessage" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'receivedMessage.label', default: 'ReceivedMessage')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-receivedMessage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list">Consumed Message List</g:link></li>
		    </ul>
		</div>
		<div id="show-receivedMessage" class="content scaffold-show" role="main">
			<h1>RabbitMQ Consumed Message Details</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list receivedMessage">
			
				<g:if test="${receivedMessageInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="receivedMessage.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${receivedMessageInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${receivedMessageInstance?.message}">
				<li class="fieldcontain">
					<span id="message-label" class="property-label"><g:message code="receivedMessage.message.label" default="Message" /></span>
					
						<span class="property-value" aria-labelledby="message-label"><g:fieldValue bean="${receivedMessageInstance}" field="message"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${receivedMessageInstance?.id}" />
					<g:link class="edit" action="edit" id="${receivedMessageInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
