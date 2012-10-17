
<%@ page import="com.mdw360.domain.ReceivedMessage" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'receivedMessage.label', default: 'ReceivedMessage')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-receivedMessage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-receivedMessage" class="content scaffold-list" role="main">
			<h1>RabbitMQ Consumed Message List</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'receivedMessage.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="message" title="${message(code: 'receivedMessage.message.label', default: 'Message')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${receivedMessageInstanceList}" status="i" var="receivedMessageInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${receivedMessageInstance.id}">${fieldValue(bean: receivedMessageInstance, field: "dateCreated")}</g:link></td>
					
						<td>${fieldValue(bean: receivedMessageInstance, field: "message")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${receivedMessageInstanceTotal}" />
			</div>
            <br/>
            <g:form>
                <fieldset class="buttons">

                    <g:actionSubmit class="delete" action="deleteAll" value="${message(code: 'default.button.flush.label', default: 'Flush All Messages')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
		</div>
	</body>
</html>
