package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User
import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin

@TestMixin(DomainClassUnitTestMixin)
@Mock([Question, User, SpringSecurityService])
class SurveyUtilsTests
{
    private User user1
    private User user2

    void setUp()
    {
        user1 = new User(username: 'test@test.com', password: 'oldpassword')
        user1.springSecurityService = [encodePassword: { String p -> "encrypted" }]
        user1.save()

        user2 = new User(username: 'test@test.com', password: 'oldpassword')
        user2.springSecurityService = [encodePassword: { String p -> "encrypted" }]
        user2.save()

        mockDomain(Survey, [
            [name: "Survey 1", category: "Customer", complete: true, active: true, user: user1],
            [name: "Survey 2", category: "Medical", complete: true, active: true, user: user1],
            [name: "Survey 3", category: "Technology", complete: true, active: true, user: user1],
            [name: "Survey 4", category: "Technology", complete: true, active: false, user: user1],
            [name: "Survey 5", category: "Entertainment", complete: false, active: false, user: user1],
            [name: "Survey 6", category: "Entertainment", complete: false, active: false, user: user1],
            [name: "Survey 7", category: "Entertainment", complete: false, active: false, user: user1],
            [name: "Survey 8", category: "Employment", complete: true, active: false, user: user1],
            [name: "Survey 9", category: "Marketing", complete: true, active: false, user: user1],
            [name: "Survey 10", category: "Marketing", complete: true, active: false, user: user1],
            [name: "Survey 11", category: "Other", complete: true, active: true, user: user2],
            [name: "Survey 12", category: "Other", complete: true, active: true, user: user2],
            [name: "Survey 13", category: "Other", complete: false, active: false, user: user2],
            [name: "Survey 14", category: "Other", complete: true, active: true, user: user2],
            [name: "Survey 15", category: "Customer", complete: false, active: false, user: user2],
            [name: "Survey 16", category: "Customer", complete: false, active: false, user: user2]
        ])
    }

    void testSurveyCategories()
    {
        assert SurveyUtils.getAllSurveyCategories().size() ==  Survey.constraints.category.inList.size()
    }

    void testSurveyQuestions()
    {
        assert SurveyUtils.getAllQuestionTypes().size() ==  Question.constraints.type.inList.size()
    }

    void testActiveSurveys()
    {

        def activeSurveysUser1 = SurveyUtils.getMyActiveSurveyCategoriesAndCount(user1)
        def activeSurveysUser2 = SurveyUtils.getMyActiveSurveyCategoriesAndCount(user2)

        assert activeSurveysUser1.size() == Survey.constraints.category.inList.size()
        assert activeSurveysUser2.size() == Survey.constraints.category.inList.size()

        activeSurveysUser1.each
        {
            if(it.category == "Customer" || it.category == "Medical" || it.category == "Technology") {
                assert it.count == 1
            } else {
                assert it.count == 0
            }
        }
        activeSurveysUser2.each
        {
            if(it.category == "Other") {
                assert it.count == 3
            } else {
                assert it.count == 0
            }
        }
    }

    void testCompleteSurveys()
    {
        def completeSurveysUser1 = SurveyUtils.getMyCompletedSurveyCategoriesAndCount(user1)
        def completeSurveysUser2 = SurveyUtils.getMyCompletedSurveyCategoriesAndCount(user2)

        assert completeSurveysUser1.size() == Survey.constraints.category.inList.size()
        assert completeSurveysUser2.size() == Survey.constraints.category.inList.size()

        completeSurveysUser1.each
        {
            if(it.category == "Marketing") {
                assert it.count == 2
            } else if(it.category == "Employment" || it.category == "Technology") {
                assert it.count == 1
            } else {
                assert it.count == 0
            }
        }
        completeSurveysUser2.each
        {
            assert it.count == 0
        }
    }

    void testIncompleteSurveys()
    {
        def incompleteSurveysUser1 = SurveyUtils.getMyIncompleteSurveyCategoriesAndCount(user1)
        def incompleteSurveysUser2 = SurveyUtils.getMyIncompleteSurveyCategoriesAndCount(user2)

        assert incompleteSurveysUser1.size() == Survey.constraints.category.inList.size()
        assert incompleteSurveysUser2.size() == Survey.constraints.category.inList.size()

        incompleteSurveysUser1.each
        {
            if(it.category == "Entertainment") {
                assert it.count == 3
            } else {
                assert it.count == 0
            }
        }
        incompleteSurveysUser2.each
        {
            if(it.category == "Customer") {
                assert it.count == 2
            } else if(it.category == "Other") {
                assert it.count == 1
            } else {
                assert it.count == 0
            }
        }
    }

    void testAllActiveSurveys()
    {
        def allActiveSurveys = SurveyUtils.getAllSurveyCategoriesAndCount()

        assert allActiveSurveys.size() == Survey.constraints.category.inList.size()

        allActiveSurveys.each
        {
            if(it.category == "Other") {
                assert it.count == 3
            } else if(it.category == "Customer" || it.category == "Medical" || it.category == "Technology") {
                assert it.count == 1
            } else {
                assert it.count == 0
            }
        }
    }

    void testSurveyResults()
    {
        mockDomain(SurveyResult)

        def surveyResultsUser1 = SurveyUtils.getAllSurveyResultsCategoriesAndCountByCreator(user1)

        assert surveyResultsUser1.size() == Survey.constraints.category.inList.size()

        surveyResultsUser1.each
        {
            assert it.count == 0
        }
    }
}
