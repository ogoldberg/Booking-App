package net.turfclub

class DashboardController {

    def index = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [eventInstanceList: Event.findAllByEventDateGreaterThanEquals(new Date()),
            eventInstanceTotal: Event.count(),

        bookingInstanceList: Booking.list(params), bookingInstanceTotal: Booking.count()]
    }
}

