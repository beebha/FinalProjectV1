package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User
import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor

@TestFor(SurveyResultService)
@Mock([User, SpringSecurityService, Survey, SurveyResult])
class SurveyResultServiceTests
{
    private User user
    private Survey survey

    void setUp()
    {
        user = new User(username: 'test@test.com', password: 'password')
        user.springSecurityService = [encodePassword: { String p -> "encrypted" }]
        user.save()

        survey = new Survey(name: 'test', category: 'Customer', active: false, complete: false, user: user).save()
    }

    void testSaveSurveyResult()
    {
        for(i in 1..8) {
            service.saveSurveyResult(user, survey.id)
        }

        assert SurveyResult.count() == 8
    }
}
