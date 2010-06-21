package net.turfclub
import org.grails.comments.*

class Event implements Commentable {
    ShiroUser booker
    Date eventDate
    Boolean finalized = false
    String eventTitle = " "
    Double cover
    String description
    Boolean featured = false
    String imageLink
    Integer holdPriority
    Boolean multiDateEvent = false
    Date endDate
    
    
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
        endDate(nullable:true, blank:true)
        imageLink(url:true, nullable:true, blank:true)
        featured()
        finalized(nullable:true)
        eventTitle(blank:true, nullable:true)
        description(nullable:true, maxSize:2000)
        cover(nullable:true, blank:true)
        holdPriority(nullable:true, blank:true)
    }
}
