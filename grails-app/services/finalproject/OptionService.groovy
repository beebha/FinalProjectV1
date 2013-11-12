package finalproject

import edu.harvard.cscie56.finalproject.Option
import edu.harvard.cscie56.finalproject.Question
import org.springframework.transaction.annotation.Transactional

@Transactional
class OptionService {

    Option saveOption(String optionText, Long questionId) {
        def question = Question.get(questionId);
        def optionInstance = new Option([optionText:optionText, questionId:questionId, question:question])
        optionInstance.save(flush: true)
        return optionInstance
    }

    def updateOption(Option optionInstance, Option cmd) {
        optionInstance.properties['optionText', 'questionId', 'question'] = cmd.properties
        optionInstance.save(flush: true)
    }

    def deleteOption(Option optionInstance) {
        optionInstance.delete(flush: true)
    }
}
