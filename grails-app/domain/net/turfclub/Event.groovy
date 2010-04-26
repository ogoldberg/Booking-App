package net.turfclub
import org.grails.comments.*

class Event implements Commentable {
    ShiroUser booker
    Date eventDate
    Boolean finalized
    String eventTitle
    double cover
    String description
    Boolean featured = false
    Stage stage
      

    
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
        description(nullable:true, maxSize:2000)
        cover(nullable:true)
    }
}
