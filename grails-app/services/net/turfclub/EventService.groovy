package net.turfclub

class EventService {

    boolean transactional = true

    def todaysEvents() {
        def cal = Calendar.instance
        def year = cal.get(Calendar.YEAR) - 1900
        def month = cal.get(Calendar.MONTH)
        def day = cal.get(Calendar.DAY_OF_MONTH)

        // println "Year = " + Y
        // println "Month" + M
        // println "Day" + D
        def today = new Date(year, month, day)
        // println " the first thingy today is: " + today
        // println " the next date is : " + (today + 10)
        
        def todaysEvents = Event.findAllByEventDateBetween(today, (today + 1))
        return todaysEvents

    }
}
