
<%@ page import="net.turfclub.Band" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'band.label', default: 'Band')}" />
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
            <g:hasErrors bean="${bandInstance}">
            <div class="errors">
                <g:renderErrors bean="${bandInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${bandInstance?.id}" />
                <g:hiddenField name="version" value="${bandInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="bandName"><g:message code="band.bandName.label" default="Band Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: bandInstance, field: 'bandName', 'errors')}">
                                    <g:textField name="bandName" value="${bandInstance?.bandName}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="homePage"><g:message code="band.homePage.label" default="Home Page" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: bandInstance, field: 'homePage', 'errors')}">
                                    <g:textField name="homePage" value="${bandInstance?.homePage}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="email"><g:message code="band.email.label" default="Email" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: bandInstance, field: 'email', 'errors')}">
                                    <g:textField name="email" value="${bandInstance?.email}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="phone"><g:message code="band.phone.label" default="Phone" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: bandInstance, field: 'phone', 'errors')}">
                                    <g:textField name="phone" value="${bandInstance?.phone}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="contact"><g:message code="band.contact.label" default="Contact" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: bandInstance, field: 'contact', 'errors')}">
                                    <g:textField name="contact" value="${bandInstance?.contact}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="notes"><g:message code="band.notes.label" default="Notes" /></label>
                                </td>
                                <td><comments:render bean="${bandInstance}" /><br />
                <comments:each bean="${bandInstance}">
                      ${comment.body} - Posted by ${comment.poster}
          </comments:each>
          </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="bookings"><g:message code="band.bookings.label" default="Bookings" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: bandInstance, field: 'bookings', 'errors')}">
                                    
<ul>
<g:each in="${bandInstance?.bookings?}" var="b">

<li><g:link controller="booking" action="show" id="${b.id}">${b?.event.encodeAsHTML()}</g:link></li>


</g:each>
</ul>
<g:link controller="booking" action="create" params="['band.id': bandInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'booking.label', default: 'Booking')])}</g:link>

                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons"> 
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" 
                        value="${message(code: 'band.button.delete.label', default: 'Delete')}" 
                        onclick="return confirm('${message(code: 'band.button.delete.confirm.message', 
                        default: 'ARE YOU SURE?<br /> When you delete a band, you also delete 
                        ALL of that bands bookings: past, present, and future.')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
