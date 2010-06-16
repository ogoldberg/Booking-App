
<%@ page import="net.turfclub.Sponsor" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'sponsor.label', default: 'Sponsor')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="sponsor.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: sponsorInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="sponsor.name.label" default="Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: sponsorInstance, field: "name")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="sponsor.website.label" default="Website" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: sponsorInstance, field: "website")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="sponsor.logo.label" default="Logo" /></td>
                            
                            <td valign="top" class="value"><img src="${createLink(action:'displayLogo', id:sponsorInstance?.id)}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="sponsor.contact.label" default="Contact" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: sponsorInstance, field: "contact")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="sponsor.phone.label" default="Phone" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: sponsorInstance, field: "phone")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="sponsor.email.label" default="Email" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: sponsorInstance, field: "email")}</td>
                            
                        </tr>
                        <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="sponsorships"><g:message code="sponsor.sponsorships.label" default="Sponsorships" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: sponsorInstance, field: 'sponsorships', 'errors')}">

                        <ul>
                            <g:each in="${sponsorInstance?.sponsorships?}" var="s">
                            <li><g:link controller="sponsorship" 
                            action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
                            </g:each>
                        </ul>
                        <g:link controller="sponsorship" action="create" 
                        params="['sponsor.id': sponsorInstance?.id]">${message(code: 'default.add.label', 
                        args: [message(code: 'sponsorship.label', default: 'Sponsorship')])}</g:link>

                                </td>
                            </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${sponsorInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
