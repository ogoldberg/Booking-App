package net.turfclub

class Band {
    String bandName
    Booking booking
    String website

    static hasMany = [bookings : Booking]

    static constraints = {
    }
}
