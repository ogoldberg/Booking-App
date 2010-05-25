<%@ page import="net.turfclub.Sponsor;" %>
<div class="dialog" id="sponsorshipListDiv">
<h3>Sponsorships</h3>
<g:if test="${flash.sponsorshipMessage}">
  <div class="message">${flash.sponsorshipMessage}</div>
</g:if>

<table>
<tbody>
        <g:each in="${eventInstance.sponsorships}" var="sponsorship" status="idx">
            <g:render template="/sponsorship/sponsorshipDetails" model="[sponsorship:sponsorship, idx:idx]" />
        </g:each>
</tbody>
</table>
</div>



