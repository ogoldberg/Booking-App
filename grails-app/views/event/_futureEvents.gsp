<!--

[  
  event : nate event,
  headliner : rusty booking,
  bookings : [ the plugs booking, baz booking ]
],

-->
  <div class="panelcontent">
      <g:each var="thisEvent" in="${futureEventsAndBookings}">
       <div xmlns:v="http://rdf.data-vocabulary.org/#" typeof="v:Event"> 
        <span property="v:startDate" content="${thisEvent.event.eventDate}"></span>
         <span rel="v:geo">
        <span typeof="v:Geo">
          <span property="v:latitude" content="44.9673" ></span>
          <span property="v:longitude" content="-93.161716" ></span>
        </span>
      </span>
        <g:set var="stages" 
        value="${thisEvent.bookings.collect { booking -> booking.stage }.unique().join(', ')}" />
 <span class="date"><turfclub:formatFeedDate date="${thisEvent.event.eventDate}" /></span>
 <div class="sponsorAndTitleLine">
      <div class="sponsor">
             <turfclub:sponsorshipListWebsite 
             sponsorshipList="${thisEvent.event.sponsorships}" />
        <div class="eventtitle">
            ${thisEvent.event.eventTitle}
        </div>
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
            Doors <turfclub:formatTime date="${thisEvent.event.eventDate}" /> | <g:formatNumber number="${thisEvent.event.cover}" 
            type="currency" currencyCode="USD" locale="en_US" minFractionDigits="2" maxFractionDigits="2" /> | ${stages} 
            
    </div>
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
