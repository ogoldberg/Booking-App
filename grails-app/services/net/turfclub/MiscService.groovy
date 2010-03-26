package net.turfclub

class MiscService {

    boolean transactional = true

    // app-wide standard format for date input
    def pmFormat = 'MM/dd/yyyy h:mm a'

    def parseDate(date, hour_min, ampm) {
        def d = Date.parse(pmFormat, date + ' ' + hour_min + ' ' + ampm)
        return d
    }
}
