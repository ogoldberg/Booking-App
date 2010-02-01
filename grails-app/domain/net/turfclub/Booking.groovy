package net.turfclub

class Booking {
    Event event
    Band band
    Date startTime
    Boolean confirmed
    Boolean headliner
    //ShiroUser booker

    
    static constraints = {
        event(nullable:false)
        band(nullable:false)
    }
}
