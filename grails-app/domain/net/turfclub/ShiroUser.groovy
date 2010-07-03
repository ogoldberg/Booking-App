package net.turfclub
class ShiroUser {
    String username
    String passwordHash

    // passwordConfirm and password are transient - like hobos in the grails unisphere
    String password
    String passwordConfirm
    
    static hasMany = [ roles: ShiroRole, permissions: String, events: net.turfclub.Event ]

    static transients = [ 'password', 'passwordConfirm']

    static constraints = {
        username(nullable: false, blank: false, unique:true)
        password(nullable:false, blank:false, validator: 
            { val, obj ->
                def result = 'user.password.strength.confirmation'

                if (val != obj.passwordConfirm) {
                    result = 'user.password.mustmatch.confirmation'       
                } else if (val.length() >= 5 && (val =~ /\d/)) {
                    result = true
                }

                result
            }
        )
    }


    String toString() {
        this.username
    }
}
