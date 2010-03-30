
<%@ page import="net.turfclub.*" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
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
    <g:hasErrors bean="${eventInstance}">
      <div class="errors">
        <g:renderErrors bean="${eventInstance}" as="list" />
      </div>
    </g:hasErrors>
    <g:form method="post" >
      <g:hiddenField name="id" value="${eventInstance?.id}" />
      <g:hiddenField name="version" value="${eventInstance?.version}" />
      <div class="dialog">
        <table>
          <tbody>
            <tr class="prop">
              <td valign="top" class="name">
                <label for="finalized"><g:message code="event.finalized.label" default="Finalized" /></label>
              </td>
              <td valign="top" class="value ${hasErrors(bean: eventInstance, field: 'finalized', 'errors')}">
          <g:checkBox name="finalized" value="${eventInstance?.finalized}" />
          </td>
          </tr>
          <tr class="prop">
            <td valign="top" class="name">
              <label for="eventDate"><g:message code="event.eventDate.label" default="Event Date" /></label>
            </td>
            <td valign="top" class="value ${hasErrors(bean: eventInstance, field: 'date', 'errors')}">
          <calendar:resources lang="en" theme="green"/>
          <calendar:datePicker name="eventDate" precision="day" dateFormat="%m/%e/%Y" value="${eventInstance?.eventDate}"  />
          </td>
          </tr>


          <tr class="prop">
            <td>
              <label for="showHour"><g:message code="event.eventDate.label" default="Time" /></label>
            </td>
            <td>
          <turfclub:eventTimeSelectors date="${eventInstance.eventDate}" />
          </td>
          </tr>

           <tr class="prop">
            <td valign="top" class="name">
              <label for="event"><g:message code="event.booker.label" default="Booker" /></label>
            </td>
            <td valign="top" class="value ${hasErrors(bean: eventInstance, field: 'booker', 'errors')}">
          <g:select name="booker.id"
                    from="${ShiroUser.list()}"
                    optionKey="id"
                    optionValue="username"
                    value="${eventInstance?.booker?.id}"  />
          </td>
          </tr>

          <tr class="prop">
            <td valign="top" class="name">
              <label for="eventTitle"><g:message code="event.eventTitle.label" default="Event Title" /></label>
            </td>
            <td valign="top" class="value ${hasErrors(bean: eventInstance, field: 'eventTitle', 'errors')}">
          <g:textField name="eventTitle" value="${eventInstance?.eventTitle}" />
          </td>
          </tr>

          <tr class="prop">
            <td valign="top" class="name">
              <label for="description"><g:message code="event.description.label" default="Description" /></label>
            </td>
            <td valign="top" class="value ${hasErrors(bean: eventInstance, field: 'description', 'errors')}">
          <g:textArea name="description" cols="40" rows="5" value="${eventInstance?.description}" />
          </td>
          </tr>

          <tr class="prop">
            <td valign="top" class="name">
              <label for="cover"><g:message code="event.cover.label" default="Cover" /></label>
            </td>
            <td valign="top" class="value ${hasErrors(bean: eventInstance, field: 'cover', 'errors')}">
          <g:textField name="cover" value="${eventInstance?.cover}" />
          </td>
          </tr>

          <tr class="prop">
            <td valign="top" class="name">
              <label for="sponsorships"><g:message code="event.sponsorships.label" default="Sponsorships" /></label>
            </td>
            <td valign="top" class="value ${hasErrors(bean: eventInstance, field: 'sponsorships', 'errors')}">

              <ul>
                <g:each in="${eventInstance?.sponsorships?}" var="s">
                  <li><g:link controller="sponsorship" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
                </g:each>
              </ul>
          <g:link controller="sponsorship" action="create" params="['event.id': eventInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'sponsorship.label', default: 'Sponsorship')])}</g:link>

          </td>
          </tr>

          <tr class="prop">
            <td valign="top" class="name">
              <label for="bookings"><g:message code="event.bookings.label" default="Bookings" /></label>
            </td>
            <td valign="top" class="value ${hasErrors(bean: eventInstance, field: 'bookings', 'errors')}">

              <ul>
                <g:each in="${eventInstance?.bookings?}" var="b">
                  <li><g:link controller="booking" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></li>
                </g:each>
              </ul>
          <g:link controller="booking" action="create" params="['event.id': eventInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'booking.label', default: 'Booking')])}</g:link>

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
