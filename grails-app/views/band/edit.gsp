
<%@ page import="net.turfclub.Band" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'band.label', default: 'Band')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
        <g:javascript src="jquery-1/4/2/js" />
        <script>
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
                          url:'${createLink(controller:"contact", action:"updateNote")}',
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
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${bandInstance}">
            <div class="errors">
                <g:renderErrors bean="${bandInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${bandInstance?.id}" />
                <g:hiddenField name="version" value="${bandInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="bandName"><g:message code="band.bandName.label" default="Band Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: bandInstance, field: 'bandName', 'errors')}">
                                    <g:textField name="bandName" value="${bandInstance?.bandName}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="homePage"><g:message code="band.homePage.label" default="Home Page" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: bandInstance, field: 'homePage', 'errors')}">
                                    <g:textField name="homePage" value="${bandInstance?.homePage}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="email"><g:message code="band.email.label" default="Email" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: bandInstance, field: 'email', 'errors')}">
                                    <g:textField name="email" value="${bandInstance?.email}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="phone"><g:message code="band.phone.label" default="Phone" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: bandInstance, field: 'phone', 'errors')}">
                                    <g:textField name="phone" value="${bandInstance?.phone}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="contact"><g:message code="band.contact.label" default="Contact" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: bandInstance, field: 'contact', 'errors')}">
                                    <g:textField name="contact" value="${bandInstance?.contact}" />
                                </td>
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
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" 
                        value="${message(code: 'default.button.delete.label', default: 'Delete')}" 
                        onclick="return confirm('${message(code: 'default.button.delete.confirm.message', 
                        default: 'Are you sure?')}');" /></span>
                </div>
                </g:form>
                <g:javascript src="notes.js" />
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
