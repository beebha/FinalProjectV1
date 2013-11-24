package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User

class Survey {

    String name
    String category
    Boolean complete
    Boolean active
    User user
    Date dateCreated

    static hasMany = [questions: Question, surveyResults: SurveyResult]

    static constraints = {
        name blank: false, unique: true
        category blank: false, inList: ["Customer", "Education", "Entertainment", "Employment", "Marketing", "Medical", "Technology", "Other"]
        complete nullable: false
        active nullable: false
    }

    static mapping = {
        questions sort: 'id', order: 'asc'
    }
}
