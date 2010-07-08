package net.turfclub
import net.turfclub.ShiroUser
import org.apache.shiro.SecurityUtils

class UserService {

    boolean transactional = true

    def activeBookers() {
        return ShiroUser.findAllByActiveUser(true)
    }

     ShiroUser loggedInUser() {
        def principal = SecurityUtils.subject.getPrincipal()
        if (principal) {
            return ShiroUser.findByUsername(principal)
        }
        return null
    }
}
