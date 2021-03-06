
<%@ page import="net.turfclub.Event" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
     <link rel="stylesheet" href="${resource(dir:'css',file:'fullcalendar.css')}" />
   <link rel="stylesheet" href="${resource(dir:'css',file:'jquery-ui-1.8rc3.custom.css')}" />
     <g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
  <title>Calendar</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<g:javascript src="jquery.js" />
<g:javascript src="ui.core.js" />
<g:javascript src="ui.draggable.js" />
<g:javascript src="ui.resizable.js" />
<g:javascript src="fullcalendar.js" />
<script type="text/javascript">
    $(document).ready(function() {

    // page is now ready, initialize the calendar...

    $('#calendar').fullCalendar({
            aspectRatio: 2,

        // put your options and callbacks here    
        events: "${createLink(controller:'event', action:'eventfeed')}",
        
        dayClick: function(date, allDay, jsEvent, view) {

        var clicked_date = date.getDate();

        var clicked_month = date.getMonth();

        var clicked_year = date.getFullYear();


        var formattedDate = clicked_month + 1 + "-" +
                            clicked_date + "-" +
                            clicked_year
                          
        window.location="/turf/event/create?d=" +
          formattedDate;

    }
    });
                <g:if test="${eventInstance}">
                   $('#calendar').fullCalendar('gotoDate', ${eventInstance.eventDate.year + 1900}, ${eventInstance.eventDate.month});
               </g:if>
    });

</script>
<style type='text/css'>

	body {
		text-align: center;
		font-size: 14px;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		}

	#calendar {
		width: 1000px;
		margin: 0 auto;
		}
		

</style>

</head>
<body>
  <div class="body">
       <g:if test="${flash.message}">
      <div class="message">${flash.message}</div>
    </g:if>
    <div id="calendar"></div>
  </div>
</body>
</html>
