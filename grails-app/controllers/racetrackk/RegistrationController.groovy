package racetrackk
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RegistrationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Registration.list(params), model:[registrationInstanceCount: Registration.count()]
    }

    def show(Registration registrationInstance) {
        respond registrationInstance
    }

    def create() {
        respond new Registration(params)
    }

    @Transactional
    def save(Registration registrationInstance) {
        registrationInstance.adminRole = session?.user.admin
        //registrationInstance.properties = permittedParams()

        if (registrationInstance == null) {
            notFound()
            return
        }

        if (registrationInstance.hasErrors()) {
            respond registrationInstance.errors, view:'create'
            return
        }

        try {
            //println params
            //params.paid = params.paid?.toBoolean()
            if(!session?.user.admin) {
                registrationInstance.properties = params.findAll { key, value ->
                    key != 'paid'
                }
                //println "Antes de bindData: ${registrationInstance.properties}"
                //bindData(registrationInstance, params, [exclude: ['paid', '_paid']])
                //println "Después de bindData: ${registrationInstance.properties}"
            }
            println(registrationInstance.properties )
            registrationInstance.save(flush: true)
        } catch (Exception ex) {
            respond "Error to saved", view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'registration.label', default: 'Registration'), registrationInstance.id])
                redirect registrationInstance
            }
            '*' { respond registrationInstance, [status: CREATED] }
        }
    }

    def edit(Registration registrationInstance) {
        respond registrationInstance
    }

    @Transactional
    def update(Registration registrationInstance) {
        if (registrationInstance == null) {
            notFound()
            return
        }

        if (registrationInstance.hasErrors()) {
            respond registrationInstance.errors, view:'edit'
            return
        }

        try {
            if(!session?.user.admin) {
                //bindData(registrationInstance, params, [exclude: ['paid']])
                registrationInstance.properties = params.findAll { key, value ->
                    key != 'paid'
                }
            }
            registrationInstance.save()
        } catch (Exception ex) {
            respond "Error to saved", view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Registration.label', default: 'Registration'), registrationInstance.id])
                redirect registrationInstance
            }
            '*'{ respond registrationInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Registration registrationInstance) {

        if (registrationInstance == null) {
            notFound()
            return
        }

        registrationInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Registration.label', default: 'Registration'), registrationInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'registration.label', default: 'Registration'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
