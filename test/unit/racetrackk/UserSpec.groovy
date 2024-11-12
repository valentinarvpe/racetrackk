package racetrackk

import grails.test.mixin.*
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    void "test role constraint validation"() {
        given: "An instance of User with an invalid role"
        def user = new User(login: "someone", password: "blah", role: "SuperUser")

        when: "The user instance is validated"
        boolean isValid = user.validate()

        then: "The validation should fail"
        !isValid

        and: "The error should be due to an invalid value in the 'role' field"
        user.errors["role"].codes.any { it.contains("inList") || it.contains("not.inList") }
    }

    void testUniqueConstraint(){
        def jdoe = new User(login:"jdoe")
        def admin = new User(login:"admin")
        mockDomain(User, [jdoe, admin])
        def badUser = new User(login:"jdoe")
        badUser.save()
        assertEquals 2, User.count()
        assertEquals "unique", badUser.errors["login"]
        def goodUser = new User(login:"good",
                password:"password",
                role:"user")
        goodUser.save()
        assertEquals 3, User.count()
        assertNotNull User.findByLoginAndPassword(
                "good", "password")
    }

}
