<!doctype html>
<html>
<head >
    <title> Ping Messaging </title>
    <meta name="layout" content="main">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'errors.css')}" type="text/css">
</head>
<body>
    <a href="#list-receivedMessage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <div class="nav" role="navigation">
        <ul>
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            <li><g:link class="list" controller="receivedMessage" action="list">Consumed Message List</g:link></li>
        </ul>
    </div>
    <div id="list-receivedMessage" class="content scaffold-list" role="main">
        <h1>RabbitMQ Publisher</h1>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <g:form url="[action:'ping', controller:'pingPong']" method="post">
            <div style="padding: 10px 10px 10px 10px;">

                Number of Messages to Send:
                <g:field type="number" name="numMessages" min="5" max="100" required="" value="${numMessages ?: 10}"/>
                <br/><span style="color: #cecece; font-size: 12px; font-style: italic;">Enter the number of messages you wish to publish to the RabbitMQ Broker and press Enter. (max 100)</span>
            </div>
        </g:form>
    </div>
</body>
</html>
