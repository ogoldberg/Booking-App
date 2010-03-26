<%@ page import="net.turfclub.Event;" %>
<head>
  <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
  <link rel="stylesheet" href="http://dev.jquery.com/view/trunk/plugins/autocomplete/jquery.autocomplete.css" type="text/css" />
  <script type="text/javascript" src="http://dev.jquery.com/view/trunk/plugins/autocomplete/jquery.autocomplete.js"></script>
  <script>
  $(document).ready(function(){
    var data = "http://localhost:8080/turf/band/bandNames";
    $("#bandName").autocomplete(data, { autoFill:true, minChars:1});

    $("foo").click(function(){
      var i=1;
      alert ($("#bandName").current);
      alert ($("#bandName").emptyList);
  });

 });
  </script>

</head>
<div class="halfwidth">
  <h1 id="adduser">New Booking</h1>
  <g:hasErrors bean="${bookingInstance}">
    <div class="errors">
      <g:renderErrors bean="${bookingInstance}" as="list" />
    </div>
  </g:hasErrors>
  <g:form action="save" controller="booking" method="POST" name="newBookingForm">
    <input type="hidden" id="event.id" name="event.id"
           value="${eventInstance?.id}" />

    <table>
      <thead>
        <tr>
          <th>Band</th>
          <th>Appearance Time</th>
          <th>Confirmed</th>
          <th>Headliner</th>
          <th>Stage</th>
          <th>Action</th>

        </tr>
      </thead>
      <tbody>

        <tr class="dataEntry">

          <td><input id="bandName" name="bandName"  />
          </td>
          <td valign="top" class="value ${hasErrors(bean: bookingInstance, field: 'appearanceTime', 'errors')}">
      <turfclub:eventTimeSelectors prefix="booking" date="${bookingInstance?.appearanceTime}" />
      </td>

      <td valign="top" class="value ${hasErrors(bean: bookingInstance, field: 'confirmed', 'errors')}">
      <g:checkBox name="confirmed" value="${bookingInstance?.confirmed}" />
      </td>
      <td valign="top" class="value ${hasErrors(bean: bookingInstance, field: 'headliner', 'errors')}">
      <g:checkBox name="headliner" value="${bookingInstance?.headliner}" />
      </td>
      <td>
      <g:select name="stage.id" from="${net.turfclub.Stage.list()}" optionKey="id" value="${bookingInstance?.stage?.id}"  />
      </td>
      <td>
      <g:submitButton style="float:right" class="save" name="saveButton" value="Save" />
      </td>
      </tr>


      </tbody>
    </table>
  </g:form>
</div>
