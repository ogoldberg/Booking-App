* Access event data using jQuery

You can create a simple HTML page using something like the below example to
obtain event information from turfclub app.

<html>
    <head>
        <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                    $.ajax({
                        url: 'http://localhost:8080/turf/event/todaysEvent',
                        dataType: 'jsonp',
                        success: function(data) {
                            $("#todaysEvent").html(data.result.todaysEvent)
                        }
                        });
                    });
        </script>
    </head>
    <body>
            Event: <div id="todaysEvent"><b>should not see this</b></div>
    </body>
</html>
