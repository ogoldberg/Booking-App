import net.turfclub.*
import org.apache.shiro.crypto.hash.Sha1Hash
class BootStrap {

    def init = { servletContext ->
        def user = new ShiroUser(username: "admin", passwordHash: new Sha1Hash("admin").toHex())
        user.addToPermissions("*:*")
        user.save()

        if (grails.util.GrailsUtil.environment == 'development') {
            if (!Event.get(1)) {
                // TODO if we haven't loaded the data yet

                createDummyBands()
                createDummyEvents()
            }
        }

    }
    def destroy = {
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
        def b2 = new Band(bandName: 'Jazz Implosion',
            homePage: 'http://jazzimplosion.com',
            email: 'info@jazzimplosion.com'
        ).save()
        def b3 = new Band(bandName: 'Synchocyclotron',
            homePage: 'http://Synchocyclotron.com',
            email: 'info@Synchocyclotron.com'
        ).save()



    }

    def createDummyEvents() {
        def e1 = new Event(eventDate: '4/20/2010',
            cover: '$7'
        )
        if (!e1.validate()) {
            println "User didn't validate!"
            println e1.errors.allErrors
        }
        e1.save()
    }
} 