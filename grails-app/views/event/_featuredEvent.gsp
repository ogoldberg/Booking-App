<!--

[
  event : nate event,
  headliner : rusty booking,
  bookings : [ the plugs booking, baz booking ]
],

-->
<div class="featured">
   <div class="title">Featured Events</div>
   <div class="featuredcontent">
    <div class="featuredEvent">
      <g:each var="thingy" in="${featuredEventsAndBookings}">

        <div class="sponsor">Sponsor presents:</div><span class="eventtitle">${thingy.event.eventTitle}</span>
        <!--<div class="date"><turfclub:formatFeedDate date="${thingy.event.eventDate}" /></div>-->

        <div class="band"><g:each var="booking" in="${thingy.bookings}">
${booking.band.bandName},


          </g:each></div>
        <div class="doors">Doors |  $${thingy.event.cover}0 | </div>
        <div class="description">${thingy.event.description}</div>
      </g:each>

    </div>
    <br />
  </div>
</div>
<br />
