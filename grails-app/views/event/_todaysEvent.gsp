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
      <div xmlns:v="http://rdf.data-vocabulary.org/#" typeof="v:Event"> 
        <span property="v:startDate" content="${thisEvent.event.eventDate}"></span>
         <span rel="v:geo">
        <span typeof="v:Geo">
          <span property="v:latitude" content="44.9673" ></span>
          <span property="v:longitude" content="-93.161716" ></span>
        </span>
      </span>
     <div class="content">
     <div class="sponsor">
             <turfclub:sponsorshipListWebsite 
             sponsorshipList="${thisEvent.event.sponsorships}" />
        </div>
        <span class="eventtitle">
            ${thisEvent.event.eventTitle}
        </span>
       <span rel="v:url" property="v:summary">
        <turfclub:headliners thisEvent="${thisEvent}" />

        <div class=”band”>
            <turfclub:bandListHomePage bookingList="${thisEvent.bookings}" />
        </div></span>

         <div class="doors">
            Doors <turfclub:formatTime date="${thisEvent.event.eventDate}" /> 
            <g:if test="${thisEvent.event.cover}">
                | <g:formatNumber number="${thisEvent.event.cover}" 
                                type="currency" currencyCode="USD" locale="en_US" 
                                minFractionDigits="2" maxFractionDigits="2" /> 
            </g:if>
            | <turfclub:eventStages eventData="${thisEvent}" />        
        </div>
    </div>
    <hr />
          </g:each>        
    </div>
    <br />
  </div>
</div>
