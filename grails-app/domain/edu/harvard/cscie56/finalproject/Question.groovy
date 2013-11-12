package edu.harvard.cscie56.finalproject

class Question {

    String questionText
    String type

    static belongsTo = [survey: Survey]
    static hasMany = [options: Option]

    static constraints = {
        questionText blank: false
        type blank: false, inList: ["True/False", "Yes/No", "Multiple Choice", "Rating Scale", "Rank Order"]
    }
}
