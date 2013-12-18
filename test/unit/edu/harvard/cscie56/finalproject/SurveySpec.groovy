package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User
import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Survey)
@Mock([User, SpringSecurityService])
class SurveySpec extends Specification {

    private User user

    void setup() {
        user = new User(username: 'test@test.com', password: 'oldpassword')
        user.springSecurityService = [encodePassword: { String p -> "encrypted" }]
        user.save()
    }

    void "Test name blank constraint"() {
        when: 'the name is blank'
        def survey = new Survey(name: '', category: 'Customer', active: false, complete: false, user: user)

        then: 'validation should fail'
        !survey.validate()
        survey.hasErrors()
        survey.errors.errorCount == 1

        when: 'the name is not blank'
        survey = new Survey(name: 'test', category: 'Customer', active: false, complete: false, user: user)

        then: 'validation should pass'
        survey.validate()
        !survey.hasErrors()
        survey.errors.errorCount == 0
    }

    void "Test name unique constraint"() {
        when: 'the name is not unique'
        def survey1 = new Survey(name: 'test', category: 'Customer', active: false, complete: false, user: user).save()
        def survey2 = new Survey(name: 'test', category: 'Education', active: false, complete: false, user: user)

        then: 'validation should fail'
        !survey2.validate()
        survey2.hasErrors()
        survey2.errors.errorCount == 1

        when: 'the name is unique'
        survey2 = new Survey(name: 'testUnique', category: 'Education', active: false, complete: false, user: user)

        then: 'validation should pass'
        survey2.validate()
        !survey2.hasErrors()
        survey2.errors.errorCount == 0
    }

    void "Test category blank constraint"() {
        when: 'the category is blank'
        def survey = new Survey(name: 'test', category: '', active: false, complete: false, user: user)

        then: 'validation should fail'
        !survey.validate()
        survey.hasErrors()
        survey.errors.errorCount == 1

        when: 'the category is not blank'
        survey = new Survey(name: 'test', category: 'Customer', active: false, complete: false, user: user)

        then: 'validation should pass'
        survey.validate()
        !survey.hasErrors()
        survey.errors.errorCount == 0
    }

    void "Test category inList constraint"() {
        when: 'the category not in list'
        def survey = new Survey(name: 'test', category: 'Wassup', active: false, complete: false, user: user)

        then: 'validation should fail'
        !survey.validate()
        survey.hasErrors()
        survey.errors.errorCount == 1

        when: 'the category in list'
        survey = new Survey(name: 'test', category: 'Customer', active: false, complete: false, user: user)

        then: 'validation should pass'
        survey.validate()
        !survey.hasErrors()
        survey.errors.errorCount == 0
    }
}