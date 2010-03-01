package net.turfclub

class Sponsor {
    String name
    String website
    byte[] logo
    String contact
    String phone
    String email
    String notes
    
    String toString(){
        return name
    }
    static hasMany=[sponsorships:Sponsorship]

    static constraints = {
        name(blank:false)
        website(url:true, nullable:true)
        logo(nullable:true, maxSize:1000000)
        contact(nullable:true)
        phone(nullable:true)
        email(email:true, nullable:true)
        notes(nullable:true, maxSize:2000)
    }
}