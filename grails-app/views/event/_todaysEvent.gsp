<div class="tonight">
<div class="title">TONIGHT</div>
    <div class="todaysEvent">
        <g:each var="event" in="${events}">
        ${event.eventTitle}
        <turfclub:formatDate date="${event.eventDate}" />
        <turfclub:formatTime date="${event.eventDate}" />
        <br />
        </g:each>
    </div>&nbsp;|
    <br />
</div>
<br />
