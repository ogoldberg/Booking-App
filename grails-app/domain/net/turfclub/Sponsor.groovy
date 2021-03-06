package net.turfclub
import org.grails.comments.*

class Sponsor implements Commentable {
    String name
    String website
    byte[] logo
    String contact
    String phone
    String email
    
    String toString(){
        this.name
    }
    static hasMany=[sponsorships:Sponsorship]

    static constraints = {
        name(blank:false, unique:true)
        website(url:true, nullable:true)
        logo(nullable:true, maxSize:1000000)
        contact(nullable:true)
        phone(nullable:true)
        email(email:true, nullable:true)
    }
}
