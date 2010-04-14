package net.turfclub

class EventService {

    boolean transactional = true

    def todaysEvents() {
        def today = new Date()
        println "Finding events for today " + today

        def todaysEvents = Event.findAllByEventDateBetween(today, (today + 1))
        return todaysEvents

    }
}
