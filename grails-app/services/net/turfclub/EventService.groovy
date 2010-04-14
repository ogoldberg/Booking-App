package net.turfclub

class EventService {

    boolean transactional = true

    def todaysEvents() {
        def today = new Date()
        // Unfortunately, we have to use the lame Calendar to figure
        // out what the hour of the day is :-(
        def cal = Calendar.instance
        cal.setTime(today)
        
        // If it's earlier than 2 a.m. right now, then back up one day
        if (cal.get(Calendar.HOUR_OF_DAY) < 2) {
            today = today - 1
        }

        def todaysEvents = Event.findAllByEventDateBetween(today, (today + 1)).sort {
            it.eventDate
        }
        return todaysEvents

    }
}
