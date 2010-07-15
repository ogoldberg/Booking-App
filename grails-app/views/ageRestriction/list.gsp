
<%@ page import="net.turfclub.AgeRestriction" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'ageRestriction.label', default: 'AgeRestriction')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'ageRestriction.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="ageRestriction" title="${message(code: 'ageRestriction.ageRestriction.label', default: 'Age Restriction')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${ageRestrictionInstanceList}" status="i" var="ageRestrictionInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${ageRestrictionInstance.id}">${fieldValue(bean: ageRestrictionInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: ageRestrictionInstance, field: "ageRestriction")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${ageRestrictionInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
