package net.turfclub
import org.grails.comments.*

class Booking implements Commentable {
    Event event
    Band band
    Date appearanceTime
    Boolean confirmed = false
    Boolean headliner = false
    
    //ShiroUser booker

     String toString() {
       this.band
    }
    static constraints = {
        event(nullable:false)
        band(nullable:false)
        appearanceTime()
        confirmed()
        headliner()
        


        }
}
