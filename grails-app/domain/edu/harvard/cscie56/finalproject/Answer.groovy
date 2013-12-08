package edu.harvard.cscie56.finalproject

class Answer {
    Question question
    List answers
    String additonalComments
    static belongsTo = [surveyResult: SurveyResult]
}
