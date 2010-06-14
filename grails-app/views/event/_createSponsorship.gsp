<%@ page import="net.turfclub.Event;" %>
<head>
  <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
  <link rel="stylesheet" href="http://dev.jquery.com/view/trunk/plugins/autocomplete/jquery.autocomplete.css" type="text/css" />
  <script type="text/javascript" src="http://dev.jquery.com/view/trunk/plugins/autocomplete/jquery.autocomplete.js"></script>
  <script>
  $(document).ready(function(){
    var data = "/turf/sponsor/names";
    $("#sponsor").autocomplete(data, { autoFill:true, minChars:1});

    $("foo").click(function(){
      var i=1;
      alert ($("#sponsor").current);
      alert ($("#sponsor").emptyList);
  });

 });
  </script>

</head>
<div class="halfwidth1">
  <h1 id="adduser">New Sponsorship</h1>
  <g:hasErrors bean="${sponsorshipInstance}">
    <div class="errors">
      <g:renderErrors bean="${sponsorshipInstance}" as="list" />
    </div>
  </g:hasErrors>
  <g:form action="save" controller="sponsorship" method="POST" name="newSponsorshipForm">
    <input type="hidden" id="event.id" name="event.id"
           value="${eventInstance?.id}" />

    <table>
      <thead>
        <tr>
          <th>Sponsor</th>
          <th>Contribution Type</th>
          <th>Description</th>
          <th>Notes</th>
          <th>Action</th>

        </tr>
      </thead>
      <tbody>

        <tr class="dataEntry">

          <td><input id="sponsor" name="sponsor"  />
          </td>
          <td>
      <g:select name="contributionType" from='["Marketing", "Drink Special", "Promotion", "Cash", "Other"]'
                value="" valueMessagePrefix="sponsorship.contributionType"  />

      <td>
      <g:textField name="description" value="${sponsorshipInstance?.description}" />
      </td>
      <td>
      <g:textArea name="notes" cols="40" rows="5" value="${sponsorshipInstance?.notes}" />
      </td>
      <td>
      <g:submitButton style="float:right" class="save" name="saveButton" value="Save" />
      </td>
      </tr>


      </tbody>
    </table>
  </g:form>
</div>
