package net.turfclub

class Band {
    String bandName
    String email
    String homePage
    String phone

    static hasMany = [bookings : Booking]

    static constraints = {
        bandName(nullable:false)
        homePage(url:true)
        email(email:true)
        phone()

    }
}
