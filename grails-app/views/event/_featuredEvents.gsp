<!--

thisEvent [  
  event : nate event,
  headliner : rusty booking,
  bookings : [ the plugs booking, baz booking ]
],

-->
<div class="featured">
    <div class="title">FEATURED EVENTS</div>
    
    <div class="featuredcontent">
        <hr />
      <g:each var="thisEvent" in="${featuredEventsAndBookings}">
       <div xmlns:v="http://rdf.data-vocabulary.org/#" typeof="v:Event"> 
        <span property="v:startDate" content="${thisEvent.event.eventDate}"></span>
         <span rel="v:geo">
        <span typeof="v:Geo">
          <span property="v:latitude" content="44.9673" ></span>
          <span property="v:longitude" content="-93.161716" ></span>
        </span>
      </span>
 <span class="date"><turfclub:formatFeedDate date="${thisEvent.event.eventDate}" /></span>
        <div class="sponsor">
             <turfclub:sponsorshipListWebsite 
             sponsorshipList="${thisEvent.event.sponsorships}" /> 
        </div> 

        <span class="eventtitle">
            ${thisEvent.event.eventTitle}
        </span>
        <br /> 
        <!--<div class="date"><turfclub:formatFeedDate date="${thisEvent.event.eventDate}" /></div> -->
       <span rel="v:url" property="v:summary"> 
        <turfclub:headliners thisEvent="${thisEvent}" />
        <span class=”band”>
            <turfclub:bandListHomePage bookingList="${thisEvent.bookings}" />
        </span>
        </span>
         <div class="doors">
            Doors <turfclub:formatTime date="${thisEvent.event.eventDate}" /> 
            <g:if test="${thisEvent.event.cover}">
                | <g:formatNumber number="${thisEvent.event.cover}" 
                                type="currency" currencyCode="USD" locale="en_US" 
                                minFractionDigits="2" maxFractionDigits="2" /> 
            </g:if>
            | <turfclub:eventStages eventData="${thisEvent}" />        
        </div>
        <div class="description">
            ${thisEvent.event.description}
        </div>
       <hr />
          </g:each>        
    </div>
    <br />
  </div>
</div>
