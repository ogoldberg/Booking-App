<html>
    <head>

        <title>Turf Club</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link REL=StyleSheet HREF="turf.css" TYPE="text/css" MEDIA="screen">
        <link REL=StyleSheet HREF="mobile.css" TYPE="text/css" media="handheld,all">
        <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                    alert("I'm going to get some data")
                    $.get('http://localhost:8080/turf/event/futureEvents', function(data) {
                        $('#futureEvents').html(data);
                    });
                });
        </script>
    </head>

    <body>
        <div id="futureEvents">This shouldn't be here. </div> Here's future event!
    </body>
</html>

