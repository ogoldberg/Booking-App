
<%@ page import="net.turfclub.Band" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'band.label', default: 'Band')}" />
  <title><g:message code="default.show.label" args="[entityName]" /></title>
  <g:javascript src="jquery-1.4.2.js" /></script>
  <g:javascript src="jquery.autocomplete.js" /></script>
  <script type="text/javascript">
  $(document).ready(function(){
    var data = "/turf/band/bandNames";
    $("#bandSearchBox").autocomplete(data, { autoFill:false, minChars:1});
    
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
        <g:render template="bandSearchForm" />
      <table>
        <tbody>

          <tr class="prop">
            <td valign="top" class="name"><g:message code="band.id.label" default="Id" /></td>

        <td valign="top" class="value">${fieldValue(bean: bandInstance, field: "id")}</td>

        </tr>

        <tr class="prop">
          <td valign="top" class="name"><g:message code="band.bandName.label" default="Band Name" /></td>

        <td valign="top" class="value">${fieldValue(bean: bandInstance, field: "bandName")}</td>

        </tr>

        <tr class="prop">
          <td valign="top" class="name"><g:message code="band.homePage.label" default="Home Page" /></td>

        <td valign="top" class="value">${fieldValue(bean: bandInstance, field: "homePage")}</td>

        </tr>

        <tr class="prop">
          <td valign="top" class="name"><g:message code="band.email.label" default="Email" /></td>

        <td valign="top" class="value">${fieldValue(bean: bandInstance, field: "email")}</td>

        </tr>

        <tr class="prop">
          <td valign="top" class="name"><g:message code="band.phone.label" default="Phone" /></td>

        <td valign="top" class="value">${fieldValue(bean: bandInstance, field: "phone")}</td>

        </tr>

        <tr class="prop">
          <td valign="top" class="name"><g:message code="band.contact.label" default="Contact" /></td>

        <td valign="top" class="value">${fieldValue(bean: bandInstance, field: "contact")}</td>

        </tr>

        



        <tr class="prop">
          <td valign="top" class="name">
            <label for="bookings"><g:message code="band.bookings.label" default="Bookings" /></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: bandInstance, field: 'bookings', 'errors')}">

            <ul>
              <g:each in="${bandInstance?.bookings?}" var="b">
                <li><g:link controller="booking" action="show" id="${b.id}">${b?.event.encodeAsHTML()}</g:link></li>
              </g:each>
            </ul>
        <g:link controller="booking" action="create" params="['band.id': bandInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'booking.label', default: 'Booking')])}</g:link>

        </td>
        </tr>


        </tbody>
      </table>
    </div>
    <div class="buttons">
      <g:form>
        <g:hiddenField name="id" value="${bandInstance?.id}" />
        <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
        <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
      </g:form>
    </div>
     <div class="infobox">
          <h3 class="reallynow">Notes:</h3><g:form id="${bandInstance.id}" controller="band" action="addNote">
           <table>
                <tbody>
                  <comments:each bean="${bandInstance}">
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
  </div>
</body>
</html>
