<!--

[  
  event : nate event,
  headliner : rusty booking,
  bookings : [ the plugs booking, baz booking ]
],

-->
  <div class="panelcontent">
      <g:each var="thisEvent" in="${futureEventsAndBookings}">
        <g:set var="stages" 
        value="${thisEvent.bookings.collect { booking -> booking.stage }.unique().join(', ')}" />
 <span class="date"><turfclub:formatFeedDate date="${thisEvent.event.eventDate}" /></span>
 <div class="sponsorAndTitleLine">
 <span class="sponsor">
             <turfclub:sponsorshipListWebsite 
             sponsorshipList="${thisEvent.event.sponsorships}" />
        </span>

        <span class="eventtitle">
            ${thisEvent.event.eventTitle}
        </span>
        </div>
        <g:each var="h" in="${thisEvent.headliners}">
        <span class="headliner">
            <turfclub:bandHomePage band="${h.band}" />
        </span>
        </g:each>

        <span class=”band”>
            <turfclub:bandListHomePage bookingList="${thisEvent.bookings}" />
        </span>

        <div class="doors">
            Doors <turfclub:formatTime date="${thisEvent.event.eventDate}" /> |  $${thisEvent.event.cover} | ${stages} 
        </div>

        <div class="description">
            ${thisEvent.event.description}
        </div>
        <br />
        <hr />
          </g:each>        
    </div>
    <br />
  </div>
</div>
