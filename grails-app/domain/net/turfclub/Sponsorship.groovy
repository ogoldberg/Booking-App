package net.turfclub

class Sponsorship {
    Event event
    Sponsor sponsor
    String contributionType
    String description
    String notes

    String toString(){
        return sponsor
    }
    

    static constraints = {
        eventDate(nullable:false)
        sponsor(nullable:false)
        contributionType(inList:["Marketing", "Drink Special", "Promotion", "Cash", "Other"])
        description(nullable:true, blank:true)
        notes(nullable:true, blank:true, maxSize:5000)
    }
}
