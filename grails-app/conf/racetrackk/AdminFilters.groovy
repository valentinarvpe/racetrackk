package racetrackk

class AdminFilters {

    def filters = {
        adminOnly(controller:'*',
                action:"(index|create|edit|update|delete|save)") {
            before = {
                //println("admin ${session?.user.admin}")
                //println("user ${session?.user}")
                //println("Role ${session?.user?.role}")
                if(!session?.user?.admin){
                    //println("controller ${controllerName}")
                    if (session?.user?.role == 'user' &&
                            (controllerName in ['registration', 'race'])) {
                        return true // Permitir acceso a estos controladores para el rol user
                    } else {
                        // Bloquear el acceso y redirigir con un mensaje
                        flash.message = "Sorry, admin only"
                        redirect(controller: "race", action: "index")
                        return false
                    }
                }
            }
        }
    }
}
