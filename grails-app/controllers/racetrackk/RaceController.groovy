package racetrackk



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RaceController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Race.list(params), model:[raceInstanceCount: Race.count()]
    }

    def show(Race raceInstance) {
        respond raceInstance
    }

    def create() {
        respond new Race(params)
    }

    @Transactional
    def save(Race raceInstance) {
        if (raceInstance == null) {
            notFound()
            return
        }

        if (raceInstance.hasErrors()) {
            respond raceInstance.errors, view:'create'
            return
        }

        raceInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'race.label', default: 'Race'), raceInstance.id])
                redirect raceInstance
            }
            '*' { respond raceInstance, [status: CREATED] }
        }
    }

    def edit(Race raceInstance) {
        respond raceInstance
    }

    @Transactional
    def update(Race raceInstance) {
        if (raceInstance == null) {
            notFound()
            return
        }

        if (raceInstance.hasErrors()) {
            respond raceInstance.errors, view:'edit'
            return
        }

        raceInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Race.label', default: 'Race'), raceInstance.id])
                redirect raceInstance
            }
            '*'{ respond raceInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Race raceInstance) {

        if (raceInstance == null) {
            notFound()
            return
        }

        raceInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Race.label', default: 'Race'), raceInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'race.label', default: 'Race'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
