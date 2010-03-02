
<%@ page import="net.turfclub.Booking" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'booking.label', default: 'Booking')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'booking.id.label', default: 'Id')}" />
                        
                            <th><g:message code="booking.eventDate.label" default="Event Date" /></th>
                   	    
                            <th><g:message code="booking.band.label" default="Band" /></th>
                   	    
                            <th><g:message code="booking.stage.label" default="Stage" /></th>
                   	    
                            <g:sortableColumn property="appearanceOrder" title="${message(code: 'booking.appearanceOrder.label', default: 'Appearance Order')}" />
                        
                            <g:sortableColumn property="confirmed" title="${message(code: 'booking.confirmed.label', default: 'Confirmed')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${bookingInstanceList}" status="i" var="bookingInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${bookingInstance.id}">${fieldValue(bean: bookingInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: bookingInstance, field: "event")}</td>
                        
                            <td>${fieldValue(bean: bookingInstance, field: "band")}</td>
                        
                            <td>${fieldValue(bean: bookingInstance, field: "stage")}</td>
                        
                            <td>${fieldValue(bean: bookingInstance, field: "appearanceOrder")}</td>
                        
                            <td><g:formatBoolean boolean="${bookingInstance.confirmed}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${bookingInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
