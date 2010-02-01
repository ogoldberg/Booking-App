package net.turfclub

class Event {
    Date date
    String eventTitle
    Booking booking
    String cover
    String description
    

    
    String toString() {
        return eventTitle
        }
        
    static hasMany = [ bookings:Booking,
                       sponsorships:Sponsorship ]

    static constraints = {
        date(blank:false)
        eventTitle()
        sponsor()
        description(nullable:true, maxSize:5000)
        cover(blank:false)
    }
}
