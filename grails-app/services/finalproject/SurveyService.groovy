package finalproject

import edu.harvard.cscie56.finalproject.Survey
import edu.harvard.cscie56.finalproject.auth.User
import org.springframework.transaction.annotation.Transactional

@Transactional
class SurveyService {

    Survey saveSurvey(String surveyName, String surveyCategory, Boolean complete, Boolean active, User user) {
        def surveyInstance = new Survey([name:surveyName, category:surveyCategory, user:user, complete:complete, active:active])
        surveyInstance.save(flush: true)
        return surveyInstance
    }

    def updateSurvey(Survey surveyInstance, Survey cmd) {
        surveyInstance.properties['name', 'category', 'user'] = cmd.properties
        surveyInstance.save(flush: true)
    }

    def deleteSurvey(Survey surveyInstance) {
        surveyInstance.delete(flush: true)
    }
}
