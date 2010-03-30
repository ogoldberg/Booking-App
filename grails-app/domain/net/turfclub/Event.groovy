package net.turfclub

class Event {
    ShiroUser booker
    Date eventDate
    Boolean finalized
    String eventTitle
    double cover
    String description
    String internalNotes
    Boolean featured = false
      

    
    String toString() {
        return eventDate.format('MMMM d, yyyy')
    }        
    static hasMany = [ bookings:Booking,
        sponsorships:Sponsorship ]

    static mapping = {
        bookings sort: "appearanceTime"
    }

    static constraints = {
        booker(nullable:false)
        eventDate(blank:false)
        finalized(nullable:true)
        eventTitle(blank:true, nullable:true)
        description(nullable:true, maxSize:5000)
        internalNotes(nullable:true, maxSize:5000)
        cover(nullable:true)
    }
}
