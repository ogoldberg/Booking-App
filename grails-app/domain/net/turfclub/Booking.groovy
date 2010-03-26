package net.turfclub

class Booking {
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
    static constraints = {
        event(nullable:false)
        band(nullable:false)
        stage(nullable:false)
        appearanceTime()
        confirmed()
        headliner()
        


        }
}
