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
            def output = d ? d.format('h:mm') : ''
            out << output
           } catch (Exception e) {
               out << ''
           }

    }

    def bandHomePage = {attrs ->
        def band = attrs['band']
        if (band.homePage) {
            out << "<a href=\"${band.homePage}\">${band.bandName}</a>"
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
        if (sponsor.website) {
            out << "<a href=\"${sponsor.website}\">${sponsor}</a> Presents..."
        }
        else {
        out << sponsor.name
        }
    }
    def sponsorshipListWebsite = { attrs ->
        def sponsorshipList = attrs['sponsorshipList']
        def output = sponsorshipList.collect { sponsorship ->
            sponsorWebsite(sponsor:sponsorship.sponsor)
        }.join(', ')
        out << output
    }

}
