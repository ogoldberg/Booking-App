package net.turfclub

class Event {
    Date date
    String eventTitle
    String cover
    String sponsor
    Booking booking

    static hasMany = [ bookings:Booking ]

    static constraints = {
        date(blank:false)
        eventTitle()
        sponsor()
        cover(blank:false)
    }
}
