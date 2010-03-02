package net.turfclub

class Event {
    Date eventDate
    String eventTitle
    String cover
    String description
    

    
    String toString() {
        return eventDate.format('MMMM d, yyyy')
        }
        
    static hasMany = [ bookings:Booking,
                       sponsorships:Sponsorship ]

    static constraints = {
        eventDate(blank:false)
        eventTitle(blank:true, nullable:true)
        description(nullable:true, maxSize:5000)
        cover(blank:false)
    }
}
