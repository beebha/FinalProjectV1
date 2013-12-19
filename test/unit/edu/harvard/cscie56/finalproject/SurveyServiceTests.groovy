package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User
import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor

@TestFor(SurveyService)
@Mock([User, SpringSecurityService, Survey])
class SurveyServiceTests
{
    private User user

    void setUp()
    {
        user = new User(username: 'test@test.com', password: 'password')
        user.springSecurityService = [encodePassword: { String p -> "encrypted" }]
        user.save()
    }

    void testSaveSurvey()
    {
        for(i in 1..12) {
            service.saveSurvey('Survey'+i, 'Customer', false, false, user)
        }

        assert Survey.count() == 12
    }

    void testDeleteSurvey()
    {
        def surveys = []

        for(i in 1..9) {
            surveys.add(service.saveSurvey('Survey'+i, 'Customer', false, false, user))
        }

        assert Survey.count() == 9

        surveys.each {
            service.deleteSurvey((Survey)it)
        }

        assert Survey.count() == 0
    }
}
