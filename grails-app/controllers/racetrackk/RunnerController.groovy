package racetrackk



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RunnerController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Runner.list(params), model:[runnerInstanceCount: Runner.count()]
    }

    def show(Runner runnerInstance) {
        respond runnerInstance
    }

    def create() {
        respond new Runner(params)
    }

    @Transactional
    def save(Runner runnerInstance) {
        if (runnerInstance == null) {
            notFound()
            return
        }

        if (runnerInstance.hasErrors()) {
            respond runnerInstance.errors, view:'create'
            return
        }

        runnerInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'runner.label', default: 'Runner'), runnerInstance.id])
                redirect runnerInstance
            }
            '*' { respond runnerInstance, [status: CREATED] }
        }
    }

    def edit(Runner runnerInstance) {
        respond runnerInstance
    }

    @Transactional
    def update(Runner runnerInstance) {
        if (runnerInstance == null) {
            notFound()
            return
        }

        if (runnerInstance.hasErrors()) {
            respond runnerInstance.errors, view:'edit'
            return
        }

        runnerInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Runner.label', default: 'Runner'), runnerInstance.id])
                redirect runnerInstance
            }
            '*'{ respond runnerInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Runner runnerInstance) {

        if (runnerInstance == null) {
            notFound()
            return
        }

        runnerInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Runner.label', default: 'Runner'), runnerInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'runner.label', default: 'Runner'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
