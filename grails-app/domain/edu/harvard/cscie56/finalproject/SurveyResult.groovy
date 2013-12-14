package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User

/**
 * Domain object for SurveyResult
 */

class SurveyResult {

    User surveyTaker
    User surveyCreator
    Date dateCreated
    String category
    Survey survey

    static hasMany = [answers: Answer]

    static constraints = {
        category inList: ["Customer", "Education", "Entertainment", "Employment", "Marketing", "Medical", "Technology", "Other"]
    }

    static mapping = {
        answers sort: 'id', order: 'asc'
    }
}
