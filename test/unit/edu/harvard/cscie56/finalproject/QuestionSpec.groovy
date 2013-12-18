package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User
import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Question)
@Mock([User, SpringSecurityService, Survey])
class QuestionSpec extends Specification
{
    private User user
    private Survey survey

    void setup() {
        user = new User(username: 'test@test.com', password: 'password')
        user.springSecurityService = [encodePassword: { String p -> "encrypted" }]
        user.save()

        survey = new Survey(name: 'test', category: 'Customer', active: false, complete: false, user: user).save()
    }

    void "Test null constraints"()
    {
        when: 'the startLabel, endLabel and options is null'
        def qn = new Question(
                questionText: "text",
                type: "Comment",
                scale: 10,
                startLabel: null,
                endLabel: null,
                options: null,
                overallComment: false,
                survey: survey)

        then: 'validation should pass'
        qn.validate()
        !qn.hasErrors()
        qn.errors.errorCount == 0

        when: 'the startLabel, endLabel and options is not null'
        qn = new Question(
                questionText: "text",
                type: "Comment",
                scale: 10,
                startLabel: "start",
                endLabel: "end",
                options: [],
                overallComment: false,
                survey: survey)

        then: 'validation should pass'
        qn.validate()
        !qn.hasErrors()
        qn.errors.errorCount == 0
    }

    void "Test questionText blank constraint"()
    {
        when: 'the questionText is blank'
        def qn = new Question(
                questionText: "",
                type: "Comment",
                scale: 10,
                startLabel: "start",
                endLabel: "end",
                options: [],
                overallComment: false,
                survey: survey)

        then: 'validation should fail'
        !qn.validate()
        qn.hasErrors()
        qn.errors.errorCount == 1

        when: 'the questionText is not blank'
        qn = new Question(
                questionText: "text",
                type: "Comment",
                scale: 10,
                startLabel: "start",
                endLabel: "end",
                options: [],
                overallComment: false,
                survey: survey)

        then: 'validation should pass'
        qn.validate()
        !qn.hasErrors()
        qn.errors.errorCount == 0
    }

    void "Test type blank constraint"()
    {
        when: 'the type is blank'
        def qn = new Question(
                questionText: "text",
                type: "",
                scale: 10,
                startLabel: "start",
                endLabel: "end",
                options: [],
                overallComment: false,
                survey: survey)

        then: 'validation should fail'
        !qn.validate()
        qn.hasErrors()
        qn.errors.errorCount == 1

        when: 'the type is not blank'
        qn = new Question(
                questionText: "text",
                type: "Comment",
                scale: 10,
                startLabel: "start",
                endLabel: "end",
                options: [],
                overallComment: false,
                survey: survey)

        then: 'validation should pass'
        qn.validate()
        !qn.hasErrors()
        qn.errors.errorCount == 0
    }

    void "Test type in list constraint"()
    {
        when: 'the type is not in list'
        def qn = new Question(
                questionText: "text",
                type: "Wassup",
                scale: 10,
                startLabel: "start",
                endLabel: "end",
                options: [],
                overallComment: false,
                survey: survey)

        then: 'validation should fail'
        !qn.validate()
        qn.hasErrors()
        qn.errors.errorCount == 1

        when: 'the type is in list'
        qn = new Question(
                questionText: "text",
                type: "Comment",
                scale: 10,
                startLabel: "start",
                endLabel: "end",
                options: [],
                overallComment: false,
                survey: survey)

        then: 'validation should pass'
        qn.validate()
        !qn.hasErrors()
        qn.errors.errorCount == 0
    }
}