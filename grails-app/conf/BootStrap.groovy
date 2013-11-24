import edu.harvard.cscie56.finalproject.Question
import edu.harvard.cscie56.finalproject.Survey
import edu.harvard.cscie56.finalproject.auth.Role
import edu.harvard.cscie56.finalproject.auth.User
import edu.harvard.cscie56.finalproject.auth.UserRole

class BootStrap {

    def init = { servletContext ->
        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

        def testAdmin = new User(username: 'admin@admin.com', password: 'password')
        testAdmin.save(flush: true)

        def testUser = new User(username: 'user@user.com', password: 'password')
        testUser.save(flush: true)

        UserRole.create testAdmin, adminRole, true
        UserRole.create testUser, userRole, true

        assert User.count() == 2
        assert Role.count() == 2
        assert UserRole.count() == 2

        // sample surveys
        for(i in 1..3) {
            def survey1;
            if(i == 1) {
                survey1 = new Survey(name: 'Incomplete Customer Survey', category: 'Customer', complete: false, active: false, user: testUser)
            } else if (i == 2) {
                survey1 = new Survey(name: 'Complete Customer Survey', category: 'Customer', complete: true, active: false, user: testUser)
            } else if (i == 3) {
                survey1 = new Survey(name: 'Active Customer Survey', category: 'Customer', complete: true, active: true, user: testUser)
            }
            survey1.save(flush: true)
            def surveyID = survey1.id

            // Comment Qn
            def question1 = new Question(
                    questionText: 'Describe your overall experience?',
                    type: 'Comment',
                    scale: 0,
                    startLabel: '',
                    endLabel: '',
                    options: [],
                    overallComment: false,
                    surveyId: surveyID,
                    survey: survey1
            )
            // Discrete Rating Scale Qn
            def question2 = new Question(
                    questionText: 'How would you rate the experience',
                    type: 'Discrete Rating Scale',
                    scale: 5,
                    startLabel: 'Bad',
                    endLabel: 'Good',
                    options: [],
                    overallComment: true,
                    surveyId: surveyID,
                    survey: survey1
            )
            // Multiple Choice (One Answer) Qn
            def question3 = new Question(
                    questionText: 'Which store was your favorite',
                    type: 'Multiple Choice (One Answer)',
                    scale: 0,
                    startLabel: '',
                    endLabel: '',
                    options: ['Macys','Target','Walmart','Stop & Shop','Kohls'],
                    overallComment: false,
                    surveyId: surveyID,
                    survey: survey1
            )
            // Multiple Choice (Multiple Answers) Qn
            def question4 = new Question(
                    questionText: 'What do you normally purchase',
                    type: 'Multiple Choice (Multiple Answers)',
                    scale: 0,
                    startLabel: '',
                    endLabel: '',
                    options: ['Electronics','Groceries','Clothes','Cookware'],
                    overallComment: true,
                    surveyId: surveyID,
                    survey: survey1
            )
            // Numerical Slider Scale Qn
            def question5 = new Question(
                    questionText: 'How satisfied are you with service?',
                    type: 'Numerical Slider Scale',
                    scale: 10,
                    startLabel: 'Poor',
                    endLabel: 'Great',
                    options: [],
                    overallComment: false,
                    surveyId: surveyID,
                    survey: survey1
            )
            // Ranking Qn
            def question6 = new Question(
                    questionText: 'Rank the candy in order with your favorite the first',
                    type: 'Ranking',
                    scale: 0,
                    startLabel: '',
                    endLabel: '',
                    options: ['Mars', 'Snickers', 'Twirl', 'MilkyWay', 'KitKat'],
                    overallComment: false,
                    surveyId: surveyID,
                    survey: survey1
            )

            question1.save(flush: true)
            question2.save(flush: true)
            question3.save(flush: true)
            question4.save(flush: true)
            question5.save(flush: true)
            question6.save(flush: true)
        }

        assert Survey.count() == 3
        assert Question.count() == 18
    }
    def destroy = {
    }
}
