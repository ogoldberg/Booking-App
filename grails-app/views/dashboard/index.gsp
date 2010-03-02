
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

        <g:sortableColumn property="eventTitle" title="${message(code: 'event.eventTitle.label', default: 'Event Title')}" />

        <g:sortableColumn property="description" title="${message(code: 'event.description.label', default: 'Description')}" />

        <g:sortableColumn property="cover" title="${message(code: 'event.cover.label', default: 'Cover')}" />

        </tr>
        </thead>
        <tbody>
        <g:each in="${eventInstanceList}" status="i" var="eventInstance">
          <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

            <td><g:link action="show" controller="event" id="${eventInstance.id}">
            <turfclub:formatDate date="${eventInstance.eventDate}" />
          </g:link></td>

          <td>${fieldValue(bean: eventInstance, field: "eventTitle")}</td>

          <td>${fieldValue(bean: eventInstance, field: "description")}</td>

          <td>${fieldValue(bean: eventInstance, field: "cover")}</td>

          </tr>
        </g:each>
        </tbody>
      </table>
    </div>
    <div class="paginateButtons">
      <g:paginate total="${eventInstanceTotal}" />
    </div>

    <div class="list">
      <h1>Booking List</h1>
      <div class="nav">
        <span class="menuButton"><g:link class="create" controller="booking" action="create"><g:message code="default.new.label" args="['Booking']" /></g:link></span>
      </div>
      <table>
        <thead>
          <tr>

            <th><g:message code="booking.eventDate.label" default="Event Date" /></th>

        <g:sortableColumn property="appearanceOrder" title="${message(code: 'booking.appearanceOrder.label', default: 'Appearance Order')}" />

        <g:sortableColumn property="confirmed" title="${message(code: 'booking.confirmed.label', default: 'Confirmed')}" />

        <th><g:message code="booking.band.label" default="Band" /></th>

        <g:sortableColumn property="headliner" title="${message(code: 'booking.headliner.label', default: 'Headliner')}" />

        </tr>
        </thead>
        <tbody>
        <g:each in="${bookingInstanceList}" status="i" var="bookingInstance">
          <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

            <td><g:link action="show" controller="booking" id="${bookingInstance.id}">${fieldValue(bean: bookingInstance, field: "eventDate")}</g:link></td>

          <td>${fieldValue(bean: bookingInstance, field: "appearanceOrder")}</td>

          <td><g:formatBoolean boolean="${bookingInstance.confirmed}" /></td>

          <td>${fieldValue(bean: bookingInstance, field: "band")}</td>

          <td><g:formatBoolean boolean="${bookingInstance.headliner}" /></td>

          </tr>
        </g:each>
        </tbody>
      </table>
    </div>
  </div>
</body>
</html>
