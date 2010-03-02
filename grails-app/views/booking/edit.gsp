
<%@ page import="net.turfclub.Booking" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'booking.label', default: 'Booking')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${bookingInstance}">
            <div class="errors">
                <g:renderErrors bean="${bookingInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${bookingInstance?.id}" />
                <g:hiddenField name="version" value="${bookingInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="eventDate"><g:message code="booking.eventDate.label" default="Event Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: bookingInstance, field: 'eventDate', 'errors')}">
                                    <g:select name="event.id" from="${net.turfclub.Event.list()}" optionKey="id" value="${bookingInstance?.event?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="band"><g:message code="booking.band.label" default="Band" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: bookingInstance, field: 'band', 'errors')}">
                                    <g:select name="band.id" from="${net.turfclub.Band.list()}" optionKey="id" value="${bookingInstance?.band?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="stage"><g:message code="booking.stage.label" default="Stage" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: bookingInstance, field: 'stage', 'errors')}">
                                    <g:select name="stage.id" from="${net.turfclub.Stage.list()}" optionKey="id" value="${bookingInstance?.stage?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="appearanceOrder"><g:message code="booking.appearanceOrder.label" default="Appearance Order" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: bookingInstance, field: 'appearanceOrder', 'errors')}">
                                    <g:textField name="appearanceOrder" value="${fieldValue(bean: bookingInstance, field: 'appearanceOrder')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="confirmed"><g:message code="booking.confirmed.label" default="Confirmed" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: bookingInstance, field: 'confirmed', 'errors')}">
                                    <g:checkBox name="confirmed" value="${bookingInstance?.confirmed}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="headliner"><g:message code="booking.headliner.label" default="Headliner" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: bookingInstance, field: 'headliner', 'errors')}">
                                    <g:checkBox name="headliner" value="${bookingInstance?.headliner}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
