
<%@ page import="net.turfclub.Booking" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'booking.label', default: 'Booking')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${bookingInstance}">
            <div class="errors">
                <g:renderErrors bean="${bookingInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="event"><g:message code="booking.event.label" default="Event" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: bookingInstance, field: 'event', 'errors')}">
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
                                    <label for="startTime"><g:message code="booking.startTime.label" default="Start Time" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: bookingInstance, field: 'startTime', 'errors')}">
                                    <g:datePicker name="startTime" precision="day" value="${bookingInstance?.startTime}"  />
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
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
