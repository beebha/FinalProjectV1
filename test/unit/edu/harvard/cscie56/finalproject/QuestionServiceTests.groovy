package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User
import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor


@TestFor(QuestionService)
@Mock([User, SpringSecurityService, Survey, Question])
class QuestionServiceTests
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

    void testSaveQuestion()
    {
        for(i in 1..15) {
            service.saveQuestion("qntext"+i, "Comment", 0, "", "", [], false, survey.id)
        }

        assert Question.count() == 15
    }

    void testUpdateQuestion()
    {
        def questions = []

        for(i in 1..10) {
            questions.add(service.saveQuestion("qntext", "Comment", 0, "oldstart", "oldend", [], false, survey.id))
        }

        assert questions.size() == 10

        questions.each {
            assert it.questionText == "qntext"
            assert it.scale == 0
            assert it.startLabel == "oldstart"
            assert it.endLabel == "oldend"
            assert it.overallComment == false
        }

        questions.each {
            service.updateQuestion((Question)it, "updatedQntext", "Comment", 7, "newstart", "newend", [], true)
        }

        questions.each {
            assert it.questionText == "updatedQntext"
            assert it.scale == 7
            assert it.startLabel == "newstart"
            assert it.endLabel == "newend"
            assert it.overallComment == true
        }
    }

    void testDeleteQuestion()
    {
        def questions = []

        for(i in 1..7) {
            questions.add(service.saveQuestion("qntext", "Comment", 0, "oldstart", "oldend", [], false, survey.id))
        }

        assert Question.count() == 7

        questions.each {
            service.deleteQuestion((Question)it)
        }

        assert Question.count() == 0
    }
}
