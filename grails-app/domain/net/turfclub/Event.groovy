package net.turfclub
import org.grails.comments.*

class Event implements Commentable {
    ShiroUser booker
    Date eventDate
    Boolean finalized
    String eventTitle = " "
    Double cover
    Double advancePrice
    String description
    Boolean featured = false
    String imageLink
    Integer holdPriority = 0
    AgeRestriction ageRestriction
    
    
    String toString() {
        return eventDate.format('MMMM d, yyyy')
    }        
   static hasMany = [ bookings:Booking,
        sponsorships:Sponsorship ]

    static mapping = {
        bookings sort: "appearanceTime"
        bookings cascade:"all,delete-orphan"
        sponsorships cascade:"all,delete-orphan"
    }
    

    static constraints = {
        booker(nullable:false)
        eventDate(blank:false)
        imageLink(url:true, nullable:true, blank:true)
        featured()
        finalized(nullable:true)
        eventTitle(blank:true, nullable:true)
        description(nullable:true, maxSize:2000)
        cover(nullable:true, blank:true)
        holdPriority(nullable:true, blank:true)
        advancePrice(nullable:true, blank:true)
        ageRestriction(nullable:true, blank:true)
    }
}
