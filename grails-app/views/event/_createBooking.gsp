<%@ page import="net.turfclub.Event;" %>

<div class="halfwidth">
  <h1 id="adduser">Book a Band</h1>
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
          <th></th>

        </tr>
      </thead>
      <tbody>

        <tr class="dataEntry">

          <td><input id="bandName" name="bandName"  />
          </td>
          <td valign="top" >
      <turfclub:eventTimeSelectors prefix="booking" />
      </td>

      <td valign="top" >
      <g:checkBox name="confirmed"/>
      </td>
      <td valign="top" >
      <g:checkBox name="headliner" />
      </td>
      <td>
      <g:select name="stage.id" from="${net.turfclub.Stage.list()}" optionKey="id" />
      </td>
      <td>
      <g:submitButton style="float:right" class="save" name="saveButton" value="Save" />
      </td>
      </tr>


      </tbody>
    </table>
  </g:form>
</div>
