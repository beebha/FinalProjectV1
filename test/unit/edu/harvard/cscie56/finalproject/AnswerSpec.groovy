package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User
import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Answer)
@Mock([User, SpringSecurityService, Survey, SurveyResult, Question])
class AnswerSpec extends Specification {

    private User user1
    private User user2
    private Survey survey
    private SurveyResult surveyResult
    private Question question

    void setup() {
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

    void "Test additonalComments null constraint"()
    {
        when: 'the additonalComments is null'
        def answer = new Answer(
                question: question,
                allAnswers: [],
                additonalComments: null,
                surveyResult: surveyResult)

        then: 'validation should pass'
        answer.validate()
        !answer.hasErrors()
        answer.errors.errorCount == 0

        when: 'the additonalComments is not null'
        answer = new Answer(
                question: question,
                allAnswers: [],
                additonalComments: "wassup",
                surveyResult: surveyResult)

        then: 'validation should pass'
        answer.validate()
        !answer.hasErrors()
        answer.errors.errorCount == 0
    }
}