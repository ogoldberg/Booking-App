
<%@ page import="net.turfclub.ShiroUser" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'shiroUser.label', default: 'ShiroUser')}" />
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
            <g:hasErrors bean="${shiroUserInstance}">
            <div class="errors">
                <g:renderErrors bean="${shiroUserInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${shiroUserInstance?.id}" />
                <g:hiddenField name="version" value="${shiroUserInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="username"><g:message code="shiroUser.username.label" default="Username" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: shiroUserInstance, field: 'username', 'errors')}">
                                    <g:textField name="username" value="${shiroUserInstance?.username}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="events"><g:message code="shiroUser.events.label" default="Events" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: shiroUserInstance, field: 'events', 'errors')}">
                                    
<ul>
<g:each in="${shiroUserInstance?.events?}" var="e">
    <li><g:link controller="event" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="event" action="create" params="['shiroUser.id': shiroUserInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'event.label', default: 'Event')])}</g:link>

                                </td>
                            </tr>
                        
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="permissions"><g:message code="shiroUser.permissions.label" default="Permissions" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: shiroUserInstance, field: 'permissions', 'errors')}">
                                    
                                </td>
                            </tr>
                        

                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="activeUser"><g:message code="event.activeUser.label" default="Active" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: eventInstance, field: 'activeUser', 'errors')}">
                                    <g:checkBox name="activeUser" value="${shiroUserInstance?.activeUser}" />


                                    
                                </td>

                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'user.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
