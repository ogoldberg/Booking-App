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
        value="${thisEvent.bookings.collect 
        { booking -> booking.stage }.unique().join(', ')}" />
        <div class="content">

        <span class="eventtitle">
            ${thisEvent.event.eventTitle}
        </span>
       
        <g:each var="h" in="${thisEvent.headliners}">
        <div class="headliner">
            <turfclub:bandHomePage band="${h.band}" />
        </div>
        </g:each>

        <div class=”band”>
            <turfclub:bandListHomePage bookingList="${thisEvent.bookings}" />
        </div>

        <div class="doors">
            <turfclub:formatTime date="${thisEvent.event.eventDate}" /> |  <g:formatNumber number="${thisEvent.event.cover}" 
            type="currency" currencyCode="USD" locale="en_US" minFractionDigits="2" maxFractionDigits="2" /> | ${stages} 
        </div>
        
         <div class="sponsor">
             <turfclub:sponsorshipListWebsite 
             sponsorshipList="${thisEvent.event.sponsorships}" />
        </div>


        <div class="description">
            ${thisEvent.event.description}
        </div>
    </div>
          </g:each>        
    </div>
    <br />
  </div>
</div>
