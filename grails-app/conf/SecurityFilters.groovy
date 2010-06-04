import net.turfclub.*
/**
 * Generated by the Shiro plugin. This filters class protects all URLs
 * via access control by convention.
 */
class SecurityFilters {
    def filters = {
        // Ensure that all controllers and actions require an authenticated user,
        auth(controller: "*", action: "*") {
            before = {
                // except for the "help" controller and the "error" controller
                if (controllerName == "help" || controllerName == "error") {
                    return true
                }
                // This just means that the user must be authenticated. He does 
                // not need any particular role or permission. 
                accessControl { true } 
            } 
        } 
                
        auth(controller: "*", action: "*") {
            before = {
                // Ignore direct views (e.g. the default main index page).
                if (controllerName == "event"
                        && (actionName == "todaysEvent"
                            || actionName == "futureEvents"
                            || actionName == "featuredEvents")) {
                    return true
                }

                // Access control by convention.
                accessControl()
            }
        }
        
    }
}
