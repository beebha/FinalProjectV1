package finalproject

import edu.harvard.cscie56.finalproject.Answer
import edu.harvard.cscie56.finalproject.Option
import edu.harvard.cscie56.finalproject.SurveyResult
import org.springframework.transaction.annotation.Transactional

@Transactional
class AnswerService {

    Answer saveAnswer(Long optionId, Long surveyResultId) {
        def option = Option.get(optionId)
        def surveyResult = SurveyResult.get(surveyResultId)
        def answerInstance = new Answer([optionSelected:option, surveyResultId:surveyResultId, surveyResult:surveyResult])
        answerInstance.save(flush: true)
        return answerInstance
    }

    def updateAnswer(Answer answerInstance, Answer cmd) {
        answerInstance.properties['optionSelected', 'surveyResultId', 'surveyResult'] = cmd.properties
        answerInstance.save(flush: true)
    }

    def deleteAnswer(Answer answerInstance) {
        answerInstance.delete(flush: true)
    }
}
