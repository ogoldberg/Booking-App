<!--

[  
  event : nate event,
  headliner : rusty booking,
  bookings : [ the plugs booking, baz booking ]
],

-->
<div class="tonight">
  <div class="content">
<div class="title">TONIGHT</div>
    <div class="todaysEvent">
        <g:each var="thingy" in="${todaysEventsAndBookings}">
          
          <div class="sponsor">Sponsor presents:</div><span class="eventtitle">${thingy.event.eventTitle}</span>
          <!--<div class="date"><turfclub:formatFeedDate date="${thingy.event.eventDate}" /></div>-->
  <!--      <div class="headliner"><g:each var="booking" in="${thingy.confbookings}">
            ${confbooking.headliner.bandName}
          </g:each></div> -->
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
