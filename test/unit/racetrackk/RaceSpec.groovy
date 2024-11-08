package racetrackk

import grails.test.mixin.*
import spock.lang.Specification

@TestFor(Race)
class RaceSpec extends Specification {

    void "test inMiles method"() {
        given: "a race with a distance in kilometers"
        def race = new Race(distance: 5.0)

        expect: "the inMiles method should return the distance in miles with a tolerance"
        Math.abs(race.inMiles() - 3.107) < 0.001
    }
}