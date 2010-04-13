package net.turfclub

class MiscService {

    boolean transactional = true

    // app-wide standard format for date input
    def pmFormat = 'MM/dd/yyyy h:mm a'

    // Expects three things:
    //     date in MM/dd/yyyy format
    //     time in h:mm format (Ex: 9:40)
    //     ampm in either "AM" or "PM" 
    // Returns a Groovy date made up of the three parameters above.
    def parseDate(date, hour_min, ampm) {
        def d = Date.parse(pmFormat, date + ' ' + hour_min + ' ' + ampm)
        return d
    }
}
