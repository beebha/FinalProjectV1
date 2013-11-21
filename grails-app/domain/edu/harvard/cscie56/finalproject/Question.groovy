package edu.harvard.cscie56.finalproject

class Question {

    String questionText
    String type
    boolean comment
    String commentText

    static belongsTo = [survey: Survey]
    static hasMany = [options: Option]

    static constraints = {
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
