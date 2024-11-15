package racetrackk

class Registration {
    Boolean paid
    Date dateCreated
    Boolean adminRole = false // Campo transitorio para verificar el rol
    static constraints = {
        race()
        paid(nullable: true)
        runner()
        dateCreated()
    }

    static belongsTo = [race:Race, runner:Runner]

    static transients = ['adminRole']

/*
    def beforeInsert() {
        validRoleToPaid()
    }

    def beforeUpdate() {
        validRoleToPaid()
    }

    def validRoleToPaid() {
        if (!adminRole) {
            this.paid = true
        }
    }

 */
}
