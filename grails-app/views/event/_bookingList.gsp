<%@ page import="net.turfclub.Band;" %>
<div class="dialog" id="bookingListDiv">
<h3>Bookings</h3>
<g:if test="${flash.bookingMessage}">
  <div class="message">${flash.bookingMessage}</div>
</g:if>

<table>
<tbody>
        <g:each in="${eventInstance.bookings}" var="booking" status="idx">
            <g:render template="/booking/bookingDetails" model="[booking:booking, idx:idx]" />
        </g:each>
</tbody>
</table>
</div>


