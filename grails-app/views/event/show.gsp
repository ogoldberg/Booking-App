<%@ page import="net.turfclub.*" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
  <title><g:message code="default.show.label" args="[entityName]" /></title>
 <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
  <link rel="stylesheet" href="http://dev.jquery.com/view/trunk/plugins/autocomplete/jquery.autocomplete.css" type="text/css" />
  <script type="text/javascript" src="http://dev.jquery.com/view/trunk/plugins/autocomplete/jquery.autocomplete.js"></script>
  <script>
  $(document).ready(function(){
    var data = "/turf/band/bandNames";
    $("#bandName").autocomplete(data, { autoFill:true, minChars:1});

    $("foo").click(function(){
      var i=1;
      alert ($("#bandName").current);
      alert ($("#bandName").emptyList);
  });
  
  $('.confirmedSwitcher').click(function() {
       $.post('${createLink(controller:"event",
action:"confirmBooking")}',
              {'confirmed' : $(this).attr('checked'), 'id' : $(this).attr("bookingId") });
            });

  $('.finalizedSwitcher').click(function() {
       $.post('${createLink(controller:"event",
action:"finalizeBooking")}',
              {'finalized' : $(this).attr('checked'), 'id' : $(this).attr("eventId") });
              });

 $('.featuredSwitcher').click(function() {
       $.post('${createLink(controller:"event",
action:"featuredBooking")}',
              {'featured' : $(this).attr('checked'), 'id' : $(this).attr("eventId") });
            });
      });

      
  </script>
  <meta name="layout" content="main" />
</head>
<body>
  <div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
  </div>
  <div class="body">
    <h1><turfclub:formatDate date="${eventInstance.eventDate}" /></h1>
    <g:if test="${flash.message}">
      <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
      <table>
        <tbody>


        <tr class="prop">
           <td valign="top" class="name"><g:message code="event.booker.label" default="Booker" /></td>

        <td valign="top" class="value">${fieldValue(bean: eventInstance, field: "booker")}</td>

        </tr>

         <tr class="prop">
          <td valign="top" class="name">
            <label for="bookings"><g:message code="event.bookings.label" default="Bookings" /></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: eventInstance, field: 'bookings', 'errors')}">

            <ul>
              <g:each in="${eventInstance?.bookings?.sort{it.appearanceTime}.reverse()}" var="bookingInstance">
                <li>
                <g:link controller="booking" action="edit" id="${bookingInstance.id}">${bookingInstance?.encodeAsHTML()}</g:link>&nbsp;|
                <turfclub:formatTime date="${bookingInstance.appearanceTime}" />&nbsp;|
                <label for="confirmed"><g:message code="booking.confirmed.label" default="Confirmed" /></label>
                <g:checkBox class="confirmedSwitcher" bookingId="${bookingInstance.id}" name="confirmed" value="${bookingInstance.confirmed}" />
                </li>
              </g:each>
            </ul>
          </td>
        </tr>

         <tr class="prop">
          <td valign="top" class="name"><g:message code="event.doorTime.label" default="Door Time" /></td>

        <td valign="top" class="value"><turfclub:formatTime date="${eventInstance.eventDate}" /></td>

        </tr>
        <tr class="prop">
          <td valign="top" class="name">Cover</td>

        <td valign="top" class="value"><g:formatNumber number="${eventInstance.cover}" 
            type="currency" currencyCode="USD" locale="en_US" minFractionDigits="2" maxFractionDigits="2" /></td>

        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="sponsorships"><g:message code="event.sponsorships.label" default="Sponsorships" /></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: eventInstance, field: 'sponsorships', 'errors')}">

            <ul>
              <g:each in="${eventInstance?.sponsorships?}" var="s">
                <li><g:link controller="sponsorship" action="edit" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
              </g:each>
            </ul>
        <g:link controller="sponsorship" action="create" params="['event.id': eventInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'sponsorship.label', default: 'Sponsorship')])}</g:link>

        </td>
        </tr>
         <tr class="prop">
            <td valign="top" class="name"><g:message code="event.featured.label" default="Featured" /></td>

            <td> <g:checkBox class="featuredSwitcher" eventId="${eventInstance.id}" name="featured" value="${eventInstance.featured}" /></td>

        </tr>
        <tr class="prop">
          <td valign="top" class="name"><g:message code="event.eventTitle.label" default="Event Title" /></td>

        <td valign="top" class="value">${fieldValue(bean: eventInstance, field: "eventTitle")}</td>

        </tr>

        <tr class="prop">
          <td valign="top" class="name"><g:message code="event.imageLinke.label" default="Image Link" /></td>

        <td valign="top" class="value">${fieldValue(bean: eventInstance, field: "imageLink")}</td>

        </tr>

        <tr class="prop">
          <td valign="top" class="name"><g:message code="event.description.label" default="Description" /></td>

        <td valign="top" class="value">${fieldValue(bean: eventInstance, field: "description")}</td>

        </tr>

         <tr class="prop">
            <td valign="top" class="name"><g:message code="event.finalized.label" default="Finalized" /></td>

            <td> <g:checkBox class="finalizedSwitcher" eventId="${eventInstance.id}" name="finalized" value="${eventInstance.finalized}" /></td>

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
  <div class="dialog1">
    <g:render template='createBooking' model="[eventInstance:eventInstance]" />
    <br />
    <ul type="none">
      <h1>Notes</h1>
      <li>Confirmed bookings appear on the public facing website, while unconfirmed bookings do not.</li>
      <li>Appearance Time affects the order in which bands appear on the website (latest to earliest).</li>
      <li>The headliner will appear styled differently from the other bands.</li>
      <li>The finalized checkbox is just an internal flag to indicate that the booking of a show is complete.<br /> A finalized event will turn from orange to green on the calendar view.</li>
    </ul>
  </div>

</body>
</html>
