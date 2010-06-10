package net.turfclub
import org.grails.comments.*

class Band implements Commentable {
    String bandName
    String email
    String homePage
    String phone
    String contact

  

    String toString() {
       this.bandName
    }    
    
    static hasMany = [bookings:Booking]

    static constraints = {
        bandName(nullable:false, blank:false, unique:true)
        homePage(url:true, nullable:true, blank:true)
        email(email:true, blank:true, nullable:true)
        phone(blank:true, nullable:true)
        contact(blank:true, nullable:true)

    }

}
