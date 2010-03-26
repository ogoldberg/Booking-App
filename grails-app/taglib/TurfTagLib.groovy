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

}
