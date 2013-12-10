package edu.harvard.cscie56.finalproject

class Answer {
    List allAnswers
    String additonalComments

    static belongsTo = [surveyResult: SurveyResult, question: Question]

    static hasMany = [allAnswers: String]
}
