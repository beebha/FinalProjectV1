package edu.harvard.cscie56.finalproject

class Question {

    String questionText
    String type

    Integer scale
    String startLabel
    String endLabel

    List options

    Boolean overallComment

    static belongsTo = [survey: Survey]

    static hasMany = [options: String]

    static constraints = {
        startLabel nullable: true
        endLabel nullable: true
        options nullable:true
        questionText blank: false
        type blank: false, inList: [
                "Comment",
                "Discrete Rating Scale",
                "Multiple Choice (One Answer)",
                "Multiple Choice (Multiple Answers)",
                "Numerical Slider Scale",
                "Ranking"]
    }
}
