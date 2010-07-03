import net.turfclub.*
import org.apache.shiro.crypto.hash.Sha1Hash

class BootStrap {

    def init = { servletContext ->
        if (ShiroUser.count() == 0) {
            loadUserRoles()
            createAdminUser()
        }

        if (grails.util.GrailsUtil.environment =~ "development") {
            createDummyData()
        }
        
        if (grails.util.GrailsUtil.environment =~ "test") {
            createDummyUsers()
        }

    }

    def destroy = {
    }

    def createDummyData() {
        createDummyUsers()
        createDummyBands()
        createDummyStages()
        createDummySponsors()
        createDummyEvents()
    }

    def createAdminUser() {
        def adminRole = ShiroRole.findByName("Administrator")
        def adminUser = new ShiroUser(username: "admin", 
            passwordHash: new Sha1Hash("admin0").toHex(),
            password : 'admin0',
            passwordConfirm : 'admin0')

        if (!adminUser.validate()) {
            println "User didn't validate!"
            println adminUser.errors.allErrors
        }
        else {
            adminUser.save()
            adminUser.addToRoles(ShiroRole.findByName("Administrator")).save()
        }
        
    }

    def createDummyUsers() {
        def userRole = ShiroRole.findByName("User")
        def bobUser = new ShiroUser(username: "bob", 
        password : 'admin0',
        passwordConfirm : 'admin0',
        passwordHash: new Sha1Hash("admin0").toHex())
        if (!bobUser.validate()) {
            println "User didn't validate!"
            println bobUser.errors.allErrors
        }
        else {
            bobUser.save()
            bobUser.addToRoles(ShiroRole.findByName("User")).save()
        }
        
    }

    def createDummyBands() {
        def b1 = new Band(bandName: 'Dillinger 4',
            homePage: 'http://dillinger4.com',
            email: 'info@dillinger4.com'

        )
        if (!b1.validate()) {
            println "User didn't validate!"
            println b1.errors.allErrors
        }
        b1.save()
        new Band(bandName: 'Confirmed Band',
            homePage: 'http://jazzimplosion.com',
            email: 'info@jazzimplosion.com'
        ).save()
        new Band(bandName: 'Synchocyclotron',
            homePage: 'http://Synchocyclotron.com',
            email: 'info@Synchocyclotron.com'
        ).save()
        new Band(bandName: 'Headliner Band',
            homePage: 'http://Synchocyclotron.com',
            email: 'info@Synchocyclotron.com'
        ).save()



    }

    def createDummyEvents() {
        def e1 = new Event(
            booker: ShiroUser.findByUsername("bob"),
            eventDate: '4/25/2010',
            cover: 7
        )
        if (!e1.validate()) {
            println "Event didn't validate!"
            println e1.errors.allErrors
        }
        e1.save()

        e1.addToBookings(new Booking(
           appearanceTime:e1.eventDate, 
           band:Band.list(max:1)[0],
           stage:Stage.list(max:1)[0]
        ))
        

        def e2 = new Event(
            eventTitle:'Event with conf. booking',
            booker: ShiroUser.findByUsername("admin"),
            eventDate: '5/30/2010',
            cover: 12
        )
        if (!e2.validate()) {
            println "Event didn't validate!"
            println e2.errors.allErrors
        }
        e2.save()
        e2.addToBookings(new Booking(
           confirmed : true,
           appearanceTime:e2.eventDate, 
           band:Band.findByBandName('Confirmed Band'),
           stage:Stage.list(max:1)[0]
        )
        )
        e2.addToBookings(new Booking(
           headliner : true,
           confirmed : true,
           appearanceTime:e2.eventDate, 
           band:Band.findByBandName('Headliner Band'),
           stage:Stage.list(max:1)[0]
          )
          )
    }

    def createDummySponsors() {
        def s1 = new Sponsor(name: 'City Pages',
                             website: 'http://citypages.com',
                             contact: 'Mary Jane',
                             phone: '612.555.5555',
                             email: 'mjane@citypages.com'
    )
    if (!s1.validate()) {
            println "Event didn't validate!"
            println s1.errors.allErrors
        }
        s1.save()
}
    def createDummyStages() {
        def s1 = new Stage(stage: "Main Stage")
         if (!s1.validate()) {
            println "Event didn't validate!"
            println s1.errors.allErrors
        }
        s1.save()
        def s2 = new Stage(stage: "Old Stage")
        if (!s2.validate()) {
            println "Event didn't validate!"
            println s2.errors.allErrors
        }
        s2.save()
        def s3 = new Stage(stage: "Clown Lounge")
        if (!s3.validate()) {
            println "Event didn't validate!"
            println s3.errors.allErrors
        }
        s3.save()

    }

    def loadUserRoles() {
		// Initialize User role
		def userRole = new ShiroRole(name: "User").save() 
		def adminRole = new ShiroRole(name: "Administrator").save() 
	}
} 
