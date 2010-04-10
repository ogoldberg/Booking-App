package net.turfclub
import org.grails.comments.*

class Sponsorship implements Commentable {
    Event event
    Sponsor sponsor
    String contributionType
    String description
    
    String toString(){
        return sponsor
    }
    

    static constraints = {
        event(nullable:false)
        sponsor(nullable:false)
        contributionType(inList:["Marketing", "Drink Special", "Promotion", "Cash", "Other"])
        description(nullable:true, blank:true)
    }
}
