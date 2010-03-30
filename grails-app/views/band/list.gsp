
<%@ page import="net.turfclub.Band" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'band.label', default: 'Band')}" />
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
                                                         
                            <g:sortableColumn property="bandName" title="${message(code: 'band.bandName.label', default: 'Band Name')}" />
                        
                            <g:sortableColumn property="homePage" title="${message(code: 'band.homePage.label', default: 'Home Page')}" />
                        
                            <g:sortableColumn property="email" title="${message(code: 'band.email.label', default: 'Email')}" />
                        
                            <g:sortableColumn property="phone" title="${message(code: 'band.phone.label', default: 'Phone')}" />
                        
                            <g:sortableColumn property="contact" title="${message(code: 'band.contact.label', default: 'Contact')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${bandInstanceList}" status="i" var="bandInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="edit" id="${bandInstance.id}">${fieldValue(bean: bandInstance, field: "bandName")}</g:link></td>
                                                
                            <td>${fieldValue(bean: bandInstance, field: "homePage")}</td>
                        
                            <td>${fieldValue(bean: bandInstance, field: "email")}</td>
                        
                            <td>${fieldValue(bean: bandInstance, field: "phone")}</td>
                        
                            <td>${fieldValue(bean: bandInstance, field: "contact")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${bandInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
