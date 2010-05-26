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
      <g:each var="thisEvent" in="${todaysEventsAndBookings}">
        <g:set var="stages" 
        value="${thisEvent.bookings.collect { booking -> booking.stage }.unique().join(', ')}" />

         <div class="sponsor">
             <turfclub:sponsorshipListWebsite 
             sponsorshipList="${thisEvent.event.sponsorships}" />
        </div>

        <span class="eventtitle">
            ${thisEvent.event.eventTitle}
        </span>
       
        <!--<div class="date"><turfclub:formatFeedDate date="${thisEvent.event.eventDate}" /></div> -->
       
        <g:each var="h" in="${thisEvent.headliners}">
        <div class="headliner">
            <turfclub:bandHomePage band="${h.band}" />
        </div>
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
          </g:each>        
    </div>
    <br />
  </div>
</div>
