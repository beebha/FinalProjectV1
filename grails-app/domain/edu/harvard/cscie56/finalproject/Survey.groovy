package edu.harvard.cscie56.finalproject

import edu.harvard.cscie56.finalproject.auth.User

/**
 * Domain object for Survey
 */

class Survey {

    String name
    String category
    Boolean complete
    Boolean active
    User user
    Date dateCreated

    static hasMany = [questions: Question]

    static constraints = {
        name blank: false, unique: true
        category blank: false, inList: ["Customer", "Education", "Entertainment", "Employment", "Marketing", "Medical", "Technology", "Other"]
    }

    static mapping = {
        questions sort: 'id', order: 'asc'
    }
}
