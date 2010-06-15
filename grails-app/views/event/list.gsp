
<%@ page import="net.turfclub.Event" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
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

        <g:sortableColumn property="date" title="${message(code: 'event.date.label', default: 'Date')}" />

        <g:sortableColumn property="finalized" title="Finalized" />

        <g:sortableColumn property="eventTitle" title="${message(code: 'event.eventTitle.label', default: 'Event Title')}" />

        <g:sortableColumn property="imageLink" title="${message(code: 'event.imageLink.label', default: 'Image Link')}" />

        <g:sortableColumn property="description" title="${message(code: 'event.description.label', default: 'Description')}" />

        <g:sortableColumn property="cover" title="${message(code: 'event.cover.label', default: 'Cover')}" />

        <g:sortableColumn property="featured" title="Featured" />

        </tr>
        </thead>
        <tbody>
        <g:each in="${eventInstanceList}" status="i" var="eventInstance">
          <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

            <td><g:link action="show" id="${eventInstance.id}">
            <turfclub:formatDate date="${eventInstance.eventDate}" />
          </g:link></td>

           <td><g:formatBoolean boolean="${eventInstance.finalized}" /></td>

          <td>${fieldValue(bean: eventInstance, field: "eventTitle")}</td>

          <td>${fieldValue(bean: eventInstance, field: "imageLink")}</td>

          <td>${fieldValue(bean: eventInstance, field: "description")}</td>

          <td>${fieldValue(bean: eventInstance, field: "cover")}</td>

          <td><g:formatBoolean boolean="${eventInstance.featured}" /></td>

          </tr>
        </g:each>
        </tbody>
      </table>
    </div>
    <div class="paginateButtons">
      <g:paginate total="${eventInstanceTotal}" />
    </div>
  </div>
</body>
</html>
