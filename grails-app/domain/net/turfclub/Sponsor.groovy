package net.turfclub

class Sponsor {
    String name
    String website
    byte[] logo
    
    String toString(){
        return name
    }
    static hasMany=[sponsorships:Sponsorship]

    static constraints = {
        name(blank:false)
        website(blank:false)
        logo(nullable:true, maxSize:1000000)
        sponsorships(nullable:true)
    }
}