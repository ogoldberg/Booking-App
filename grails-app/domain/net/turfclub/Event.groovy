package net.turfclub

class Event {
    Date date
    String eventTitle
    String cover
    String sponsor
    
    static hasMany = [ bookings:Booking ]

    static constraints = {
        date(blank:false)
        eventTitle()
        sponsor()
        cover(blank:false)
    }
}
