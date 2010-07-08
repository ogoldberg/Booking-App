$(document).ready(function(){  
$('.confirmedSwitcher').click(function() {
       $.post('${createLink(controller:"event",
action:"confirmBooking")}',
              {'confirmed' : $(this).attr('checked'), 'id' : $(this).attr("bookingId") });
            });

  $('.finalizedSwitcher').click(function() {
       $.post('${createLink(controller:"event",
action:"finalizeBooking")}',
              {'finalized' : $(this).attr('checked'), 'id' : $(this).attr("eventId") });
              });

 $('.featuredSwitcher').click(function() {
       $.post('${createLink(controller:"event",
action:"featuredBooking")}',
              {'featured' : $(this).attr('checked'), 'id' : $(this).attr("eventId") });
            });
