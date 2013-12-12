import edu.harvard.cscie56.finalproject.Question
import edu.harvard.cscie56.finalproject.Survey
import edu.harvard.cscie56.finalproject.auth.Role
import edu.harvard.cscie56.finalproject.auth.User
import edu.harvard.cscie56.finalproject.auth.UserRole

class BootStrap {

    def init = { servletContext ->
        def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

        def testUser = new User(username: 'user@user.com', password: 'password')
        testUser.save(flush: true)

        UserRole.create testUser, userRole, true

        assert User.count() == 1
        assert Role.count() == 1
        assert UserRole.count() == 1

        // sample Customer surveys
        for(i in 1..3) {
            def survey;
            if(i == 1) {
                survey = new Survey(name: 'Incomplete Customer Survey', category: 'Customer', complete: false, active: false, user: testUser)
            } else if (i == 2) {
                survey = new Survey(name: 'Complete Customer Survey', category: 'Customer', complete: true, active: false, user: testUser)
            } else if (i == 3) {
                survey = new Survey(name: 'Published Customer Survey', category: 'Customer', complete: true, active: true, user: testUser)
            }
            survey.save(flush: true)
            def surveyID = survey.id

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
                    survey: survey
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
                    survey: survey
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
                    survey: survey
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
                    survey: survey
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
                    survey: survey
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
                    survey: survey
            )

            question1.save(flush: true)
            question2.save(flush: true)
            question3.save(flush: true)
            question4.save(flush: true)
            question5.save(flush: true)
            question6.save(flush: true)
        }

        // sample Education surveys
        for(i in 1..3) {
            def survey;
            if(i == 1) {
                survey = new Survey(name: 'Incomplete Education Survey', category: 'Education', complete: false, active: false, user: testUser)
            } else if (i == 2) {
                survey = new Survey(name: 'Complete Education Survey', category: 'Education', complete: true, active: false, user: testUser)
            } else if (i == 3) {
                survey = new Survey(name: 'Published Education Survey', category: 'Education', complete: true, active: true, user: testUser)
            }
            survey.save(flush: true)
            def surveyID = survey.id

            // Comment Qn
            def question1 = new Question(
                    questionText: 'Do you think having a basic degree is of any importance in the current economy?',
                    type: 'Comment',
                    scale: 0,
                    startLabel: '',
                    endLabel: '',
                    options: [],
                    overallComment: false,
                    surveyId: surveyID,
                    survey: survey
            )
            // Discrete Rating Scale Qn
            def question2 = new Question(
                    questionText: 'How would you rate the importance of having a graduate degree?',
                    type: 'Discrete Rating Scale',
                    scale: 5,
                    startLabel: 'Not Important',
                    endLabel: 'Very Important',
                    options: [],
                    overallComment: true,
                    surveyId: surveyID,
                    survey: survey
            )
            // Multiple Choice (One Answer) Qn
            def question3 = new Question(
                    questionText: 'Which is your highest level of education attained',
                    type: 'Multiple Choice (One Answer)',
                    scale: 0,
                    startLabel: '',
                    endLabel: '',
                    options: ['High School','College','Associate\'s Degree','Bachelor\'s Degree','Master\'s Degree', 'Doctorate'],
                    overallComment: false,
                    surveyId: surveyID,
                    survey: survey
            )
            // Multiple Choice (Multiple Answers) Qn
            def question4 = new Question(
                    questionText: 'What degree/s would you like to pursue?',
                    type: 'Multiple Choice (Multiple Answers)',
                    scale: 0,
                    startLabel: '',
                    endLabel: '',
                    options: ['High School','College','Associate\'s Degree','Bachelor\'s Degree','Master\'s Degree', 'Doctorate'],
                    overallComment: true,
                    surveyId: surveyID,
                    survey: survey
            )
            // Numerical Slider Scale Qn
            def question5 = new Question(
                    questionText: 'How satisfied are you with your degree?',
                    type: 'Numerical Slider Scale',
                    scale: 10,
                    startLabel: 'Not Satisfied',
                    endLabel: 'Very Satisfied',
                    options: [],
                    overallComment: false,
                    surveyId: surveyID,
                    survey: survey
            )
            // Ranking Qn
            def question6 = new Question(
                    questionText: 'Rank the following drinks that help in studying for exams',
                    type: 'Ranking',
                    scale: 0,
                    startLabel: '',
                    endLabel: '',
                    options: ['Redbull', 'Coke', 'Sprite', 'Tea', 'Coffee', 'Water'],
                    overallComment: false,
                    surveyId: surveyID,
                    survey: survey
            )

            question1.save(flush: true)
            question2.save(flush: true)
            question3.save(flush: true)
            question4.save(flush: true)
            question5.save(flush: true)
            question6.save(flush: true)
        }

        assert Survey.count() == 6
        assert Question.count() == 36
    }
}
