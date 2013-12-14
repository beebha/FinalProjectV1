package edu.harvard.cscie56.finalproject

/**
 * Domain object for Answer
 */

class Answer {
    Question question
    List allAnswers
    String additonalComments

    static belongsTo = [surveyResult: SurveyResult]

    static hasMany = [allAnswers: String]

    static constraints = {
        additonalComments nullable: true
    }
}
