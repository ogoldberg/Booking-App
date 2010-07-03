package net.turfclub

class UserService {

    boolean transactional = true

    def activeBookers() {
        return ShiroUser.findAllByActiveUser(true)
    }
}
