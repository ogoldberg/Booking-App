package net.turfclub

class Event {
    ShiroUser booker
    Date eventDate
    Boolean finalized
    String eventTitle
    String cover
    String description

    

    
    String toString() {
        return eventDate.format('MMMM d, yyyy')
    }
        
    static hasMany = [ bookings:Booking,
                       sponsorships:Sponsorship ]

    static constraints = {
        booker(nullable:false)
        eventDate(blank:false)
        finalized(nullable:true)
        eventTitle(blank:true, nullable:true)
        description(nullable:true, maxSize:5000)
        cover(nullable:true)
    }
}
