<%@ page import="net.turfclub.*" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
    <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'jquery.autocomplete.css')}" />
    <g:javascript src="jquery.js" />
    <g:javascript src="jquery.autocomplete.js" />
    <script>
        $(document).ready(function(){
            var data = "/turf/band/bandNames";
            $("#bandName").autocomplete(data, { autoFill:false, minChars:1});

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
            $(".editNote").click(function() {
                var noteId = $( this ).attr( 'editNoteId' );
                var noteElement=$( "[noteId=" + noteId + "]");
                var noteText = noteElement.text().trim();
          
          noteElement.html(
              '<input noteTextId="' + noteId + '" ' +
              'value="' + noteText + '" />'
          );

          // register an event handler to trap ENTER key
          // which will send the updated note
          $('[noteTextId=' + noteId + ']').keydown(function(event) {
            if (event.keyCode == '13') {
                $("[[saveNoteId=" + noteId + "]").click();
                event.preventDefault();
             }
          });

          toggleNoteLinks(noteId);
          
          // Don't actually trigger the href
          return false;
          
      });

      $(".saveNote").click(function() {
          var noteId = $( this ).attr( 'saveNoteId' );
          var noteElement=$( "[noteTextId=" + noteId + "]");
          var noteText = noteElement.val().trim();
          toggleNoteLinks(noteId);

          $.ajax({
              url:'${createLink(controller:"band", action:"updateNote")}',
              data:  { 'noteText' : noteText, 'id' : noteId },
              success: function(msg) {
                  $("[noteDiv=" + noteId + "]").replaceWith(msg)
              }});
          // Don't actually trigger the href
          return false;
      });

      $(".cancelNote").click(function() {
          var noteId = $( this ).attr( 'cancelNoteId' );
          var noteText = $( "[origNoteText=" + noteId + "]").val().trim();
          
          $( "[noteId=" + noteId + "]").text(noteText);

          toggleNoteLinks(noteId);

          // Don't actually trigger the href
          return false;
      });

      function toggleNoteLinks(noteId) {
          $("[editNoteId=" + noteId + "]").toggle();
          $("[cancelNoteId=" + noteId + "]").toggle();
          $("[saveNoteId=" + noteId + "]").toggle();
      }

    $("foo").click(function(){
      var i=1;
      alert ($("#bandSearchBox").current);
      alert ($("#bandSearchBox").emptyList);
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
                <g:link controller="booking" action="show" id="${bookingInstance.id}">${bookingInstance?.encodeAsHTML()}</g:link>&nbsp;|
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
         <tr class="prop">
           <td valign="top" class="name"><g:message code="event.holdPriority.label" default="Hold Priority" /></td>

        <td valign="top" class="value">${fieldValue(bean: eventInstance, field: "holdPriority")}</td>

        </tr>

        </tr>
        <tr class="prop">
          <td valign="top" class="name">Cover</td>

        <td valign="top" class="value"><g:formatNumber number="${eventInstance.cover}" 
            type="currency" currencyCode="USD" locale="en_US" minFractionDigits="2" maxFractionDigits="2" /></td>

        </tr>

         <tr class="prop">
          <td valign="top" class="name">Advance Price</td>

        <td valign="top" class="value"><g:formatNumber number="${eventInstance.advancePrice}" 
            type="currency" currencyCode="USD" locale="en_US" minFractionDigits="2" maxFractionDigits="2" /></td>

        </tr>

         <tr class="prop">
           <td valign="top" class="name"><g:message code="event.ageRestriction.label" default="Age Restriction" /></td>

        <td valign="top" class="value">${fieldValue(bean: eventInstance, field: "ageRestriction")}</td>

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
        <turfclub:calendarLink event="${ eventInstance }" />
      </g:form>
    </div>
         <div class="infobox" width="250px">
          <h3 class="reallynow">Notes:</h3><g:form id="${eventInstance.id}" controller="event" action="addNote">
           <table>
                <tbody>
                  <comments:each bean="${eventInstance}">
                      <g:render template="/common/showNote"
                                model="[ noteInstance : comment ]"/>
                  </comments:each>
                  <tr>
                       <td>
                           <g:textField name="noteText" />
                       </td>
                       <td>
                           <input type="submit" value="Add Note" />
                        </td>
                 </tr>
               </tbody>
          </table></g:form>
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
