package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User
import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(SurveyResult)
@Mock([User, SpringSecurityService, Survey])
class SurveyResultSpec extends Specification
{
    private User user1
    private User user2
    private Survey survey

    void setup() {
        user1 = new User(username: 'test1@test1.com', password: 'password')
        user1.springSecurityService = [encodePassword: { String p -> "encrypted" }]
        user1.save()

        user2 = new User(username: 'test2@test2.com', password: 'password')
        user2.springSecurityService = [encodePassword: { String p -> "encrypted" }]
        user2.save()

        survey = new Survey(name: 'test1', category: 'Customer', active: false, complete: false, user: user1).save()
    }

    void "Test category inList constraints"()
    {
        when: 'the category is not in list'
        def surveyresult = new SurveyResult(
                surveyTaker: user2,
                surveyCreator: user1,
                category: "wassup",
                survey: survey)

        then: 'validation should fail'
        !surveyresult.validate()
        surveyresult.hasErrors()
        surveyresult.errors.errorCount == 1

        when: 'the category is in list'
        surveyresult = new SurveyResult(
                surveyTaker: user2,
                surveyCreator: user1,
                category: "Education",
                survey: survey)

        then: 'validation should pass'
        surveyresult.validate()
        !surveyresult.hasErrors()
        surveyresult.errors.errorCount == 0
    }
}