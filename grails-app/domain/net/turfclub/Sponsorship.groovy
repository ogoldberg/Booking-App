package net.turfclub

class Sponsorship {
    Event event
    Sponsor sponsor
    String contributionType
    String description
    String notes
    

    static constraints = {
        event(nullable:false)
        sponsor(nullable:false)
        contributionType(inList:["Marketing", "Drink Special", "Promotion", "Cash", "Other"])
        description(nullable:true, blank:true)
        notes(nullable:true, blank:true, maxSize:5000)
    }
}
