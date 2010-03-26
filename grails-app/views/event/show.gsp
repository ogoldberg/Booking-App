
<%@ page import="net.turfclub.Event" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
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
            <td valign="top" class="name"><g:message code="event.finalized.label" default="Finalized" /></td>

        <td valign="top" class="value"><g:formatBoolean boolean="${eventInstance?.finalized}" /></td>

        </tr>

        <tr class="prop">
          <td valign="top" class="name"><g:message code="event.date.label" default="Date" /></td>

        <td valign="top" class="value"><turfclub:formatDate date="${eventInstance.eventDate}" /></td>

        </tr>

         <tr class="prop">
          <td valign="top" class="name"><g:message code="event.doorTime.label" default="Door Time" /></td>

        <td valign="top" class="value"><turfclub:formatTime date="${eventInstance.eventDate}" /></td>

        </tr>

       <tr class="prop">
          <td valign="top" class="name"><g:message code="event.booker.label" default="Booker" /></td>

        <td valign="top" class="value">${fieldValue(bean: eventInstance, field: "booker")}</td>

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
          </td>
        </tr>

        </tbody>
      </table>
    </div>
    <div class="buttons">
      <g:form>
        <g:hiddenField name="id" value="${eventInstance?.id}" />
        <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
        <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
      </g:form>
    </div>
  </div>
  <!--  <div class="dialog1">
 <g:render template='bookingList' model="[eventInstance:eventInstance]" />
    </div>-->
  <div class="dialog">
    <g:render template='createBooking' model="[eventInstance:eventInstance, bookingInstance : bookingInstance]" />
  </div>
  
</body>
</html>
