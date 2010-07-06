package net.turfclub
import org.grails.comments.*

class Booking implements Commentable {
    Event event
    Band band
    Date appearanceTime
    Boolean confirmed = false
    Boolean headliner = false
    Stage stage
    //ShiroUser booker

     String toString() {
       this.band
    }
    
    static mapping = {
               bookings sort:'appearanceTime', order:'desc'
       }
    static constraints = {
        event(nullable:false)
        band(nullable:false)
        stage(nullable:false)
        appearanceTime()
        confirmed()
        headliner()
        


        }
}
