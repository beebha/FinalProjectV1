package edu.harvard.cscie56.finalproject

class Option {

    String optionText

    static belongsTo = [question: Question]

    static constraints = {
        optionText blank: false
    }
}
