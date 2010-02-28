package net.turfclub

class Event {
    Date date
    String eventTitle
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
        description(nullable:true, maxSize:5000)
        cover(blank:false)
    }
}
