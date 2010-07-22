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
        <g:each var="h" in="${thisEvent.headliners}">
        <div class="headliner">
            <turfclub:bandHomePage band="${h.band}" />
        </div>
        </g:each>

        <div class=”band”>
            <turfclub:bandListHomePage bookingList="${thisEvent.bookings}" />
        </div></span>

        <div class="doors">
            <turfclub:formatTime date="${thisEvent.event.eventDate}" /> |  <g:formatNumber number="${thisEvent.event.cover}" 
            type="currency" currencyCode="USD" locale="en_US" minFractionDigits="2" maxFractionDigits="2" /> | 
             <turfclub:eventStages eventData="${thisEvent}" /> 
            </div> 
            <div class="description" property="v:description">
            ${thisEvent.event.description}
        </div>
    </div>
    <hr />
          </g:each>        
    </div>
    <br />
  </div>
</div>
