<!--

[  
  event : nate event,
  headliner : rusty booking,
  bookings : [ the plugs booking, baz booking ]
],

-->
<div class="tonight">
<div class="title">TONIGHT</div>
    <div class="todaysEvent">
        <g:each var="thingy" in="${todaysEventsAndBookings}">
        ${thingy.event.eventTitle}
        <turfclub:formatDate date="${thingy.event.eventDate}" />
        <turfclub:formatTime date="${thingy.event.eventDate}" />
        <br />
            <g:each var="booking" in="${thingy.bookings}">
            ${booking.band.bandName}
            </g:each>
        </g:each>
    </div>&nbsp;|
    <br />
</div>
<br />
