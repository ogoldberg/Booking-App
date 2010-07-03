package net.turfclub
class ShiroRole {
    String name
    
    String toString() {
       this.name
    }   
    static hasMany = [ users: ShiroUser, permissions: String ]
    static belongsTo = ShiroUser

    static constraints = {
        name(nullable: false, blank: false, unique: true)
    }
}
