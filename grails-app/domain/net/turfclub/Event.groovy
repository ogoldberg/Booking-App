package net.turfclub

class Event {
    Date date
    String eventTitle
    String headliner
    String opener
    String cover
    String sponsor



    static constraints = {
        date(blank:false)
        eventTitle()
        headliner(blank:false)
        opener()
        sponsor()
        cover(blank:false)
    }
}
