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

        // remove hour/min from today, so if we search for
        // todays events, we're not limited to events that are after today.hour
        def todayAt12am = Date.parse('yyyy-MM-dd', today.format('yyyy-MM-dd'))
        def todaysEvents = Event.findAllByEventDateBetween(todayAt12am, (todayAt12am + 1)).sort {
            it.eventDate
        }

        return todaysEvents

    }
}
