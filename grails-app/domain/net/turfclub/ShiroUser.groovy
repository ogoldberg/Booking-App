package net.turfclub
class ShiroUser {
    String username
    String passwordHash
    
    static hasMany = [ roles: ShiroRole, permissions: String, events: net.turfclub.Event ]

    static constraints = {
        username(nullable: false, blank: false)
    }


    String toString() {
        this.username
    }
}
