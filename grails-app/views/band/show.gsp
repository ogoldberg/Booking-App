
<%@ page import="net.turfclub.Band" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'band.label', default: 'Band')}" />
  <title><g:message code="default.show.label" args="[entityName]" /></title>
   <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
  <script type="text/javascript" src="http://dev.jquery.com/view/trunk/plugins/autocomplete/jquery.autocomplete.js"></script>
          <script>
  $(document).ready(function(){
    var data = "/turf/band/bandNames";
    $("#bandSearchBox").autocomplete(data, { autoFill:false, minChars:1});

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
          <td valign="top" class="name"><g:message code="band.notes.label" default="Notes" /></td>

       <td><comments:render bean="${bandInstance}" /><br />
                <comments:each bean="${bandInstance}">
                      ${comment.body} - Posted by ${comment.poster}
          </comments:each>
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
      <g:form>
        <g:hiddenField name="id" value="${bandInstance?.id}" />
        <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
      </g:form>
    </div>
  </div>
</body>
</html>
