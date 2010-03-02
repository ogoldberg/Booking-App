package net.turfclub

class Booking {
    Event event
    Band band
    Integer appearanceOrder = 0
    Boolean confirmed
    Boolean headliner
    Stage stage
    //ShiroUser booker

     String toString() {
       this.band
    }
    static constraints = {
        event(nullable:false)
        band(nullable:false)
        stage(nullable:false)
        appearanceOrder(nullable:true)
        confirmed(nullable:true)
        headliner(nullable:true)
        


        }
}
