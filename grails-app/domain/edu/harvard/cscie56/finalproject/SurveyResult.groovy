package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User

class SurveyResult {

    User resultsUser
    Date dateCreated
    Survey survey

    static hasMany = [answers: Answer]

    static belongsTo = [survey: Survey]
}
