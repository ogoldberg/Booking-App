import net.turfclub.*
import org.apache.shiro.SecurityUtils
/**
 * Generated by the Shiro plugin. This filters class protects all URLs
 * via access control by convention.
 */
class SecurityFilters {
    def filters = {
        // Ensure that all controllers and actions require an authenticated user,
        auth(controller: "*", action: "*") {
            before = {
                if (controllerName == "event"
                        && (actionName == "todaysEvent"
                            || actionName == "futureEvents"
                            || actionName == "featuredEvents")) {
                    return true
                }
                accessControl { true } 
            } 
        } 

        adminOnlyForUserManagement(controller:"shiroUser", action:"changePassword") {
            before = {
                accessControl {
                     // Only admins
                     role("Administrator") || isCurrentUser(params.id)

                }
            }
        }
       
        
    }

    boolean isCurrentUser(userId) {
        def user = ShiroUser.get(userId)
        return user?.username == SecurityUtils.subject.getPrincipal()
    }
}
