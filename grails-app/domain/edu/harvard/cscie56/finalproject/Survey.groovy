package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User

class Survey {

    String name
    String category
    boolean complete
    User user
    Date dateCreated

    static hasMany = [questions: Question, surveyResults: SurveyResult]

    static constraints = {
        name blank: false, unique: true
        category blank: false, inList: ["Customer", "Education", "Entertainment", "Employment", "Marketing", "Medical", "Technology", "Other"]
        complete nullable: false
    }
}
