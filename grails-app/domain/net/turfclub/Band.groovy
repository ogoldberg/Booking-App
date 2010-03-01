package net.turfclub

class Band {
    String bandName
    String email
    String homePage
    String phone
    String contact
    String notes

    String toString() {
       this.bandName
    }

    static hasMany = [bookings : Booking]

    static constraints = {
        bandName(nullable:false, blank:false)
        homePage(url:true, nullable:true, blank:true)
        email(email:true, blank:true, nullable:true)
        phone(blank:true, nullable:true)
        contact(blank:true, nullable:true)
        notes(blank:true, nullable:true, maxSize:2000)

    }
}
