package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User
import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor

@TestFor(AnswerService)
@Mock([User, SpringSecurityService, Survey, SurveyResult, Question, Answer])
class AnswerServiceTests
{
    private User user1
    private User user2
    private Survey survey
    private SurveyResult surveyResult
    private Question question

    void setUp()
    {
        user1 = new User(username: 'test1@test1.com', password: 'password')
        user1.springSecurityService = [encodePassword: { String p -> "encrypted" }]
        user1.save()

        user2 = new User(username: 'test2@test2.com', password: 'password')
        user2.springSecurityService = [encodePassword: { String p -> "encrypted" }]
        user2.save()

        survey = new Survey(name: 'test1', category: 'Customer', active: false, complete: false, user: user1).save()

        surveyResult = new SurveyResult(
                surveyTaker: user2,
                surveyCreator: user1,
                category: "Customer",
                survey: survey).save()

        question = new Question(
                questionText: "text",
                type: "Comment",
                scale: 10,
                startLabel: "start",
                endLabel: "end",
                options: [],
                overallComment: false,
                survey: survey).save()
    }

    void testSaveAnswer()
    {
        for(i in 1..10) {
            service.saveAnswer(['test'+i], 'comments'+i, question, surveyResult)
        }
        assert Answer.count() == 10
    }
}