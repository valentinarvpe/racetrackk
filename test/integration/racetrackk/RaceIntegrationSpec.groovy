package racetrackk

import grails.test.spock.Integration
import grails.transaction.Rollback
import grails.transaction.Transactional
import spock.lang.Specification

@Transactional
class RaceIntegrationSpec extends Specification {

    def setup() {
        // Código de configuración de la prueba
    }

    def cleanup() {
        // Código de limpieza de la prueba
    }


    void "test race dates before today"() {
        given: "a date that is one week in the past and a race with that start date"
        def lastWeek = new Date() - 7
        def race = new Race(startDate: lastWeek)

        when: "we validate the race"
        def isValid = race.validate()

        then: "validation should not succed and errors should be present"
        !isValid
        race.hasErrors()

        and: "the errors should include a validation error for the startDate field"
        def badField = race.errors.getFieldError('startDate')
        badField != null
        badField.codes.contains('race.startDate.validator.invalid')

        and: "we can log details if necesary"
        println("\nErrors:")
        println race.errors ?: "no errors found"
        println "\nBadField:"
        println badField ?: "startDate wasn't a bad file"
        println "\nCode:"
        println badField?.codes.find({ it == 'race.startDate.validator.invalid'} ?: "the custom validator for startDate wasn't found" )
    }
}
