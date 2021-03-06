import net.turfclub.*

class TurfTagLib {
    static namespace = "turfclub"

    def formatDate = { attrs ->
        def d = attrs['date']
        try {
            def output = d ? d.format('MMMM d, yyyy') : ''
            out << output
        } catch (Exception e) {
            out << ''
        }
    }

    def formatFeedDate = { attrs ->
        def d = attrs['date']
        try {
            def output = d ? d.format('EE MMM d') : ''
            out << output
        } catch (Exception e) {
            out << ''
        }
    }

     def eventTimeSelectors = { attrs ->
        def eventHours = []
        (1 .. 12).each {
            eventHours.add(it + ":00")
            eventHours.add(it + ":30")
        }

        def existingTime = attrs['date']?.format('h:mm')
        def fieldName = attrs['prefix'] ?: "event"
        out << g.select(name:fieldName+"Time", from:eventHours, value: existingTime ?: '9:00')

        def existingAmPm = attrs['date']?.format('a')
        out << g.select(name:fieldName + "AmPm", from:['PM', 'AM'], value: existingAmPm ?: 'PM')
    }

    def formatTime = {attrs ->
        def d = attrs['date']
        try {
            def output = d ? d.format('h:mm a') : ''
            out << output
           } catch (Exception e) {
               out << ''
           }

    }

    def headliners = { attrs ->
        def thisEvent = attrs['thisEvent']
        def headlinerLinks = thisEvent.headliners.collect { headliner ->
            bandHomePage(band:headliner.band)
        }.join(' | ')
       out << '<span class="headliner">'
       out << headlinerLinks
       out << '</span>'
    }

    def bandHomePage = {attrs ->
        def band = attrs['band']
        if (band.homePage) {
            out << "<a href=\"${band.homePage}\" >${band.bandName}</a>"
        }
        else {
        out << band.bandName
        }
    }

    def bandListHomePage = { attrs ->
        def bookingList = attrs['bookingList']
        def output = bookingList.collect { booking ->
            bandHomePage(band:booking.band)
        }.join(', ')
        out << output
    }
    
      def sponsorWebsite = {attrs ->
        def sponsor = attrs['sponsor']
        if (sponsor?.website) {
            out << "<a href=\"${sponsor.website}\">${sponsor}</a> Presents..."
        }
        else {
        out << sponsor?.name
        }
    }
    def sponsorshipListWebsite = { attrs ->
        def sponsorshipList = attrs['sponsorshipList']
        if (sponsorshipList) {
            def output = sponsorshipList.collect { sponsorship ->
                sponsorWebsite(sponsor:sponsorship.sponsor)
            }.join(', ')
        
            out << output 
        }
    }

    def calendarLink = { attrs ->
        def s = attrs['event']
        if (s) {
            out << g.link(controller:'event', action:'calendar', params: [eventId:s.id]) { ' Show in Calendar ' }
        }
    }

    def eventStages = { attrs ->
        def s = attrs['eventData']

        // combine bookings and headliner arrays
        def bookingsAndHeadliners = s.bookings + s.headliners

        // gather their stages
        def stages = bookingsAndHeadliners.collect { 
            it.stage
        }

        // get the unique stages
        def uniqueStages = stages.unique()

        // join them with commas 
        def stageList = uniqueStages.join(", ")

        // output
        out << stageList
        
    }
}
