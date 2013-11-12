package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User

class SurveyResult {

    String name
    User user
    Date dateCreated
    Survey survey

    static hasMany = [answers: Answer]

    static belongsTo = [survey: Survey]

    static constraints = {
        name blank: false
    }
}
