<%@ page import="com.mdw360.domain.ReceivedMessage" %>



<div class="fieldcontain ${hasErrors(bean: receivedMessageInstance, field: 'message', 'error')} ">
	<label for="message">
		<g:message code="receivedMessage.message.label" default="Message" />
		
	</label>
	<g:textField name="message" value="${receivedMessageInstance?.message}"/>
</div>

