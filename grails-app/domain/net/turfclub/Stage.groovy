package net.turfclub
import org.grails.comments.*

class Stage implements Commentable {
    String stage

    String toString() {
        this.stage
    }


    static constraints = {
        
    }
}
